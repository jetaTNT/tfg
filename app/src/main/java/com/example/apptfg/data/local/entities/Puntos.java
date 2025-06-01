// Archivo: app/src/main/java/com/example/apptfg/data/local/entities/Puntos.java
package com.example.apptfg.data.local.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entidad Room que representa la tabla "puntos".
 * Ahora incluye un constructor vacío para que podamos instanciar sin parámetros.
 */
@Entity(tableName = "puntos")
public class Puntos {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int cantidad;

    // Constructor vacío requerido por Room/Gson
    public Puntos() {
    }

    // Constructor adicional (si lo necesitas en otros contextos)
    public Puntos(int id, int cantidad) {
        this.id = id;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
