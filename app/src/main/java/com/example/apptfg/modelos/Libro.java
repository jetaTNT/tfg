package com.example.apptfg.modelos;

import java.util.List;

/**
 * Representa un libro.
 */
public class Libro {
    private int id;
    private String titulo;
    private List<Pagina> paginas;

    public Libro(int id, String titulo, List<Pagina> paginas) {
        this.id = id;
        this.titulo = titulo;
        this.paginas = paginas;
    }
    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public List<Pagina> getPaginas() { return paginas; }
}