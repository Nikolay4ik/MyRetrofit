package com.example.myretrofit.Date;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myretrofit.pojo.Employee;

import java.util.List;

@Dao
public interface EmployeeDao {
    @Query("Select * From employees")
    LiveData<List<Employee>> getAllEmployess() ;

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void isertEmployees(List <Employee> employees );

    @Query("DELETE FROM employees")
    void deliteAllEmployees();
}
