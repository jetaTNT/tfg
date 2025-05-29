package com.example.apptfg.modelos;

public class Opcion {
    private String texto;
    private int siguientePaginaId;

    public Opcion(String texto, int siguientePaginaId) {
        this.texto = texto;
        this.siguientePaginaId = siguientePaginaId;
    }

    public String getTexto() {
        return texto;
    }

    public int getSiguientePaginaId() {
        return siguientePaginaId;
    }
}
