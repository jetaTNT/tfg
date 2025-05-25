package com.example.apptfg.util;

public class GestorDePuntos {
    private static int puntos = 0;

    public static void reset() { puntos = 0; }
    public static void sumar(int valor) { puntos += valor; }
    public static int getPuntos() { return puntos; }
}