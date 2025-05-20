package com.example.apptfg.modelos;
import java.util.List;
public class Libro {
    private String titulo, descripcion, archivo;
    private List<Pagina> paginas;
    private List<Pregunta> preguntas;
    public String getTitulo(){return titulo;} public String getDescripcion(){return descripcion;} public String getArchivo(){return archivo;}
    public void setDescripcion(String d){descripcion=d;} public void setArchivo(String a){archivo=a;}
    public List<Pagina> getPaginas(){return paginas;} public List<Pregunta> getPreguntas(){return preguntas;}
}