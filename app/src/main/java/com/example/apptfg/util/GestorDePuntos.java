package com.example.apptfg.util;

import android.content.Context;
import androidx.room.Room;
import com.example.apptfg.modelos.AppDatabase;
import com.example.apptfg.modelos.Puntos;
import com.example.apptfg.modelos.PuntosDao;

/**
 * Gestor centralizado de puntos usando Room para persistencia.
 */
public class GestorDePuntos {
    private static AppDatabase db;
    private static PuntosDao puntosDao;

    /**
     * Inicializa Room y configura el DAO.
     * Debe invocarse antes de cualquier operación de puntos.
     */
    public static void init(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "mi_base_de_datos"
                    )
                    .allowMainThreadQueries() // Solo para pruebas
                    .build();
            puntosDao = db.puntosDao();
            if (puntosDao.obtenerPuntos() == null) {
                puntosDao.guardarPuntos(new Puntos(0));
            }
        }
    }

    /** Suma una cantidad de puntos al total almacenado. */
    public static void sumarPuntos(int valor) {
        Puntos registro = puntosDao.obtenerPuntos();
        int nuevos = (registro != null ? registro.getCantidad() : 0) + valor;
        puntosDao.guardarPuntos(new Puntos(nuevos));
    }

    /** Devuelve el número de puntos actualmente almacenados. */
    public static int obtenerPuntos() {
        Puntos registro = puntosDao.obtenerPuntos();
        return registro != null ? registro.getCantidad() : 0;
    }

    /** Reinicia los puntos a cero. */
    public static void reset() {
        puntosDao.guardarPuntos(new Puntos(0));
    }
}
