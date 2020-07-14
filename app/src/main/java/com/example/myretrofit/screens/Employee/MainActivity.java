package com.example.myretrofit.screens.Employee;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.dingmouren.layoutmanagergroup.echelon.EchelonLayoutManager;
import com.example.myretrofit.Adapters.AdapterEmployee;
import com.example.myretrofit.R;
import com.example.myretrofit.pojo.Employee;

import java.util.ArrayList;
import java.util.List;

//AppCompatActivity
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewEmployees;
    private AdapterEmployee adapterEmployee;
    private EmployeeViewModel viewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewEmployees = findViewById(R.id.recyclerViewEmployees);
        adapterEmployee = new AdapterEmployee();
        adapterEmployee.setEmployees(new ArrayList<Employee>());
        recyclerViewEmployees.setLayoutManager(new EchelonLayoutManager(this));
        recyclerViewEmployees.setAdapter(adapterEmployee);
viewModel= ViewModelProviders.of(this).get(EmployeeViewModel.class);
viewModel.getEmployees().observe(this, new Observer<List<Employee>>() {
    @Override
    public void onChanged(List<Employee> employees) {
        adapterEmployee.setEmployees(employees);
    }
});
viewModel.loadData();



    }

}
