package com.example.andro3lesson5.data.dao;

import android.app.Application;

import androidx.room.Room;

public class App extends Application {
    private static AppDataBase appDataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        appDataBase = Room.databaseBuilder(
                this,
                AppDataBase.class,
                "database")
                .allowMainThreadQueries()
                .build();
    }

    public static AppDataBase getAppDataBase() {
        return appDataBase;
    }
}
