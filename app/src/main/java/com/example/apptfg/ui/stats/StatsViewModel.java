package com.example.apptfg.ui.stats;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.apptfg.data.local.AppDatabase;
import com.example.apptfg.data.local.entities.Puntos;

/**
 * ViewModel para StatsActivity:
 * - Expone LiveData<Puntos> con el registro más reciente de puntos.
 */
public class StatsViewModel extends AndroidViewModel {
    private final LiveData<Puntos> puntosTotales;

    public StatsViewModel(@NonNull Application application) {
        super(application);
        AppDatabase db = AppDatabase.getDatabase(application);
        puntosTotales = db.puntosDao().getUltimosPuntos();
    }

    /**
     * Devuelve LiveData<Puntos> para que la Activity observe los puntos totales.
     */
    public LiveData<Puntos> getPuntosTotales() {
        return puntosTotales;
    }
}
