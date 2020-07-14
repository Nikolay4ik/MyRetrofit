package com.example.myretrofit.screens.Employee;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myretrofit.Date.AppDatabase;
import com.example.myretrofit.api.ApiFactory;
import com.example.myretrofit.api.ApiService;
import com.example.myretrofit.pojo.Employee;
import com.example.myretrofit.pojo.EmployeeResponse;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EmployeeViewModel extends AndroidViewModel {
    private static AppDatabase appDatabase;
    private LiveData<List<Employee>> employees;
    private CompositeDisposable compositeDisposable;

    public EmployeeViewModel(@NonNull Application application) {
        super(application);
        appDatabase = AppDatabase.getInstance(getApplication());
        employees = appDatabase.employeeDao().getAllEmployess();
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    public void setAppDatabase(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    public LiveData<List<Employee>> getEmployees() {
        return employees;
    }

    public void setEmployees(LiveData<List<Employee>> employees) {
        this.employees = employees;
    }

    @SuppressWarnings("unchecked")
    private void insertEmployee(List<Employee> employees) {
        new InsertTask().execute(employees);

    }

    private void deliteEmployee() {
        new DeliteAllTask().execute();
    }

    private static class InsertTask extends AsyncTask<List<Employee>, Void, Void> {

        @Override
        protected Void doInBackground(List<Employee>... lists) {
            if (lists != null && lists.length != 0) {
                appDatabase.employeeDao().isertEmployees(lists[0]);
            }
            return null;
        }
    }

    private static class DeliteAllTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            appDatabase.employeeDao().getAllEmployess();
            return null;
        }
    }


    public void loadData() {
        compositeDisposable = new CompositeDisposable();
        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();
        Disposable disposable = apiService.getEmployees()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<EmployeeResponse>() {
                    @Override
                    public void accept(EmployeeResponse employeeResponse) throws Exception {
deliteEmployee();
insertEmployee(employeeResponse.getEmployees());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
        compositeDisposable.add(disposable);

    }

    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }
}
