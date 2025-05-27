package com.example.apptfg.modelos;

import java.util.List;

/**
 * Representa las preguntas de despues del libro
 */
public class Pregunta {
    private String pregunta;
    private List<String> respuestas;
    private int correcta; // índice 0-based

    public Pregunta(String pregunta, List<String> respuestas, int correcta) {
        this.pregunta = pregunta;
        this.respuestas = respuestas;
        this.correcta = correcta;
    }

    public String getPregunta() { return pregunta; }
    public List<String> getRespuestas() { return respuestas; }
    public int getCorrecta() { return correcta; }
}