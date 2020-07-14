package com.example.myretrofit.Date;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.myretrofit.pojo.Employee;

@Database(entities = {Employee.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
private static AppDatabase database;
private static final String DB_NAME="employees.db";
private static final Object LOCK= new Object();
public static AppDatabase getInstance(Context context){
    synchronized (LOCK){
    if (database==null) {
    database= Room.databaseBuilder(context,AppDatabase.class,DB_NAME).build();
    }
    }
    return database;
}

public abstract EmployeeDao employeeDao();




}
