package com.example.apptfg.modelos;

import java.util.List;

public class Pagina {
    private int id;
    private String texto;
    private List<Opcion> opciones; // NUEVO

    public Pagina(int id, String texto, List<Opcion> opciones) {
        this.id = id;
        this.texto = texto;
        this.opciones = opciones;
    }

    public int getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public List<Opcion> getOpciones() {
        return opciones;
    }
}
