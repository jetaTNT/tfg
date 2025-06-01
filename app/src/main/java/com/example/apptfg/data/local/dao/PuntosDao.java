package com.example.apptfg.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.apptfg.data.local.entities.Puntos;

@Dao
public interface PuntosDao {

    /**
     * Inserta (o reemplaza) un registro de Puntos.
     * Con OnConflictStrategy.REPLACE, si ya existe un registro con el mismo id,
     * se sustituye; de lo contrario, se genera uno nuevo.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarPuntos(Puntos puntos);

    /**
     * Devuelve un LiveData con el registro más reciente de la tabla puntos,
     * ordenado por id descendente, limitando a 1 fila.
     */
    @Query("SELECT * FROM puntos ORDER BY id DESC LIMIT 1")
    LiveData<Puntos> getUltimosPuntos();

    /**
     * Versión síncrona de la consulta anterior. Útil si necesitas leer el último
     * valor de forma directa desde un hilo de fondo (no en el hilo UI).
     */
    @Query("SELECT * FROM puntos ORDER BY id DESC LIMIT 1")
    Puntos getUltimosPuntosSync();
}
