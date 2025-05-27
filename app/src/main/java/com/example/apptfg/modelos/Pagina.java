package com.example.apptfg.modelos;

/**
 * Representa una página.
 */
public class Pagina {
    private int numero;
    private String texto;
    // Opcional: recurso de imagen para la página
    private int imagenResId;

    public Pagina(int numero, String texto, int imagenResId) {
        this.numero = numero;
        this.texto = texto;
        this.imagenResId = imagenResId;
    }
    public int getNumero() { return numero; }
    public String getTexto() { return texto; }
    public int getImagenResId() { return imagenResId; }
}