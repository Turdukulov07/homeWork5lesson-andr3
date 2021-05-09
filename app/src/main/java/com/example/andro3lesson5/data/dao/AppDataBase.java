package com.example.andro3lesson5.data.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.andro3lesson5.data.models.FilmModel;

@Database(entities = {FilmModel.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract FilmDao filmDao();
}
