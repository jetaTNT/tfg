package com.example.apptfg.modelos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface PuntosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void guardarPuntos(Puntos puntos);

    @Query("SELECT * FROM Puntos WHERE id = 1")
    Puntos obtenerPuntos();

    @Query("SELECT cantidad FROM Puntos WHERE id = 1")
    int obtenerTotalPuntos();

    @Query("SELECT preguntasRespondidas FROM Puntos WHERE id = 1")
    int totalPreguntas();

    @Query("SELECT preguntasCorrectas FROM Puntos WHERE id = 1")
    int preguntasCorrectas();
}
