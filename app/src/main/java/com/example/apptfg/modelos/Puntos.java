package com.example.apptfg.modelos;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Representa los puntos al contestar bien las preguntas
 */
@Entity
public class Puntos {
    @PrimaryKey public int id = 1;
    public int cantidad;
    public Puntos(int cantidad) { this.cantidad = cantidad; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}
