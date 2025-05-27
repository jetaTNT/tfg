package com.example.apptfg.modelos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


/**
 * Data acces object es
 */
@Dao
public interface PuntosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void guardarPuntos(Puntos puntos);

    @Query("SELECT * FROM Puntos WHERE id = 1")
    Puntos obtenerPuntos();
}
