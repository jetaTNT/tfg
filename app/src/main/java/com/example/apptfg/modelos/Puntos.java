package com.example.apptfg.modelos;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Puntos {
    @PrimaryKey public int id = 1;

    public int cantidad;
    public int preguntasRespondidas;
    public int preguntasCorrectas;

    public Puntos(int cantidad, int preguntasRespondidas, int preguntasCorrectas) {
        this.cantidad = cantidad;
        this.preguntasRespondidas = preguntasRespondidas;
        this.preguntasCorrectas = preguntasCorrectas;
    }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public int getPreguntasRespondidas() { return preguntasRespondidas; }
    public void setPreguntasRespondidas(int preguntasRespondidas) { this.preguntasRespondidas = preguntasRespondidas; }

    public int getPreguntasCorrectas() { return preguntasCorrectas; }
    public void setPreguntasCorrectas(int preguntasCorrectas) { this.preguntasCorrectas = preguntasCorrectas; }
}
