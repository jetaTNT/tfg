package com.example.apptfg.modelos;

public class Opcion {
    private String texto;
    private int siguientePagina;

    public Opcion(String texto, int siguientePagina) {
        this.texto = texto;
        this.siguientePagina = siguientePagina;
    }

    public String getTexto() { return texto; }
    public int getSiguientePagina() { return siguientePagina; }
}