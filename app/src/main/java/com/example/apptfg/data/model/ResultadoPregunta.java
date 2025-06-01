package com.example.apptfg.data.model;
public class ResultadoPregunta {
    private final int preguntaId; private final boolean acertada;
    public ResultadoPregunta(int preguntaId, boolean acertada) { this.preguntaId=preguntaId; this.acertada=acertada; }
    public int getPreguntaId() { return preguntaId; } public boolean isAcertada() { return acertada; }
}