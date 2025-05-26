package com.example.apptfg.util;

import android.content.Context;
import androidx.room.Room;
import com.example.apptfg.modelos.AppDatabase;
import com.example.apptfg.modelos.Puntos;
import com.example.apptfg.modelos.PuntosDao;

/**
 * Gestor centralizado de puntos usando Room para persistencia.
 * Reemplaza el gestor en memoria por una implementación basada en base de datos local.
 */
public class GestorDePuntos {
    // Instancia única de la base de datos
    private static AppDatabase db;
    // DAO para acceso a operaciones de puntos
    private static PuntosDao puntosDao;

    /**
     * Inicializa Room y configura el DAO.
     * Debe invocarse antes de cualquier operación de puntos, típicamente en la Activity principal.
     */
    public static void init(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "mi_base_de_datos"
                    )
                    .allowMainThreadQueries() // Solo para pruebas; en producción, usar hilos de fondo
                    .build();

            puntosDao = db.puntosDao();

            // Inicializar registro de puntos si no existe
            if (puntosDao.obtenerPuntos() == null) {
                puntosDao.guardarPuntos(new Puntos(0));
            }
        }
    }

    /**
     * Suma una cantidad de puntos al total almacenado.
     * @param valor cantidad de puntos a sumar
     */
    public static void sumarPuntos(int valor) {
        Puntos registro = puntosDao.obtenerPuntos();
        int nuevos = (registro != null ? registro.getCantidad() : 0) + valor;
        puntosDao.guardarPuntos(new Puntos(nuevos));
    }

    /**
     * Devuelve el número de puntos actualmente almacenados.
     * @return puntos actuales, o 0 si no hay registro
     */
    public static int obtenerPuntos() {
        Puntos registro = puntosDao.obtenerPuntos();
        return registro != null ? registro.getCantidad() : 0;
    }

    /**
     * Reinicia los puntos a cero.
     */
    public static void reset() {
        puntosDao.guardarPuntos(new Puntos(0));
    }

    public static PuntosDao getPuntosDao() {
        return puntosDao;
    }
}
