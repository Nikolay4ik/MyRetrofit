package com.example.myretrofit.screens.Employee;

import com.example.myretrofit.api.ApiFactory;
import com.example.myretrofit.api.ApiService;
import com.example.myretrofit.pojo.EmployeeResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EmployeeListPresenter {
    private CompositeDisposable compositeDisposable;
    private MainActivity activity;
    private EmployeeVive vive;

    public EmployeeListPresenter(EmployeeVive vive) {
        this.vive = vive;
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
                        vive.showDate(employeeResponse.getEmployees());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        vive.showError();
                    }
                });
        compositeDisposable.add(disposable);

    }

    public void disposeDisposable() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
}