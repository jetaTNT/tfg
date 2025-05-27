package com.example.apptfg.modelos;


/** Por si acabo haciendo que en un libro puedas
*  cambiar la historia con opciones
 */
public class Opcion {
    private String texto;
    private int siguientePagina;

    public Opcion(String texto, int siguientePagina) {
        this.texto = texto;
        this.siguientePagina = siguientePagina;
    }

    public String getTexto() { return texto; }
    public int getSiguientePagina() { return siguientePagina; }
}