package com.example.apptfg.modelos;

import java.util.List;

public class Pregunta {

    private String texto;
    private List<String> opciones;
    private int respuestaCorrecta;

    public Pregunta(String texto, List<String> opciones, int respuestaCorrecta) {
        this.texto = texto;
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public String getTexto() {
        return texto;
    }

    public List<String> getOpciones() {
        return opciones;
    }

    public int getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setOpciones(List<String> opciones) {
        this.opciones = opciones;
    }

    public void setRespuestaCorrecta(int respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }
}
