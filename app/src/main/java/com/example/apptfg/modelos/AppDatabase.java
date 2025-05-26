package com.example.apptfg.modelos;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Puntos.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PuntosDao puntosDao();
}