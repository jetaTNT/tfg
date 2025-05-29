package com.example.apptfg.modelos;

public class Pregunta {
    private String enunciado;
    private String opcion1;
    private String opcion2;
    private String opcion3;
    private String opcion4;
    private String opcionCorrecta;

    public Pregunta(String enunciado, String opcion1, String opcion2, String opcion3, String opcion4, String opcionCorrecta) {
        this.enunciado = enunciado;
        this.opcion1 = opcion1;
        this.opcion2 = opcion2;
        this.opcion3 = opcion3;
        this.opcion4 = opcion4;
        this.opcionCorrecta = opcionCorrecta;
    }

    public String getEnunciado() { return enunciado; }
    public String getOpcion1() { return opcion1; }
    public String getOpcion2() { return opcion2; }
    public String getOpcion3() { return opcion3; }
    public String getOpcion4() { return opcion4; }
    public String getOpcionCorrecta() { return opcionCorrecta; }
}
