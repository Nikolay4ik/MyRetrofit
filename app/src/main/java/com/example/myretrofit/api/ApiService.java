package com.example.myretrofit.api;

import com.example.myretrofit.pojo.EmployeeResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
@GET("testTask.json" )
Observable<EmployeeResponse> getEmployees();

}
