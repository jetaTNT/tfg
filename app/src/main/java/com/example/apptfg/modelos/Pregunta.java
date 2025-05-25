package com.example.apptfg.modelos;

import java.util.List;

public class Pregunta {
    private String texto;
    private List<String> respuestas;
    private int correcta;

    public Pregunta(String texto, List<String> respuestas, int correcta) {
        this.texto = texto;
        this.respuestas = respuestas;
        this.correcta = correcta;
    }

    public String getTexto() { return texto; }
    public List<String> getRespuestas() { return respuestas; }
    public int getCorrecta() { return correcta; }
}