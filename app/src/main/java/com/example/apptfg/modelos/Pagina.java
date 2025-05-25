package com.example.apptfg.modelos;

import java.util.List;

public class Pagina {
    private String contenido;
    private List<Opcion> opciones;

    public Pagina(String contenido, List<Opcion> opciones) {
        this.contenido = contenido;
        this.opciones = opciones;
    }

    public String getContenido() { return contenido; }
    public List<Opcion> getOpciones() { return opciones; }
}