package com.example.apptfg.data.model;

import java.util.List;

public class Pregunta {
    private int id;
    private String texto;
    private List<String> opciones;
    private int respuestaCorrectaIndex;

    // Constructor vacío necesario para que GSON funcione
    public Pregunta() { }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public List<String> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<String> opciones) {
        this.opciones = opciones;
    }

    public int getRespuestaCorrectaIndex() {
        return respuestaCorrectaIndex;
    }

    public void setRespuestaCorrectaIndex(int respuestaCorrectaIndex) {
        this.respuestaCorrectaIndex = respuestaCorrectaIndex;
    }
}
