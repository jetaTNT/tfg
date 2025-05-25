package com.example.apptfg.modelos;

import java.util.List;

public class Libro {
    private String id;
    private String titulo;
    private String autor;
    private List<Pagina> paginas;

    public Libro(String id, String titulo, String autor, List<Pagina> paginas) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.paginas = paginas;
    }

    // Getters
    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public List<Pagina> getPaginas() { return paginas; }
}