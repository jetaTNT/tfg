package com.example.apptfg.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.apptfg.data.local.dao.PuntosDao;
import com.example.apptfg.data.local.entities.Puntos;

/**
 * Base de datos Room que contiene la entidad Puntos.
 */
@Database(entities = {Puntos.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PuntosDao puntosDao();

    private static volatile AppDatabase INSTANCE;

    /**
     * Devuelve la instancia singleton de la base de datos.
     */
    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "app_database"
                            )
                            // .fallbackToDestructiveMigration()  // opcional, si necesitas migraciones
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
