package com.example.apptfg.modelos;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

    @Entity
    public class Puntos {
        @PrimaryKey
        public int id = 1;      // siempre usaremos id = 1 para sobrescribir

        public int cantidad;    // número de puntos

        // Constructor (Room necesita al menos uno público)
        public Puntos(int cantidad) {
            this.cantidad = cantidad;
        }

        // Getters y setters si los usas
        public int getCantidad() {
            return cantidad;
        }
        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }
    }

