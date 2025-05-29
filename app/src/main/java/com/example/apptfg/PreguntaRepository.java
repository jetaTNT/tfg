package com.example.apptfg;

import com.example.apptfg.modelos.Pregunta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PreguntaRepository {

    public static List<Pregunta> obtenerPreguntasPorPaginaFinal(int paginaFinalId) {
        List<Pregunta> lista = new ArrayList<>();

        lista.add(new Pregunta("¿Qué vio Leo entre los árboles?",
                Arrays.asList("Una cabaña", "Una luz misteriosa", "Un dragón", "Un río"), 1));

        lista.add(new Pregunta("¿Qué hizo Leo con el agua de la fuente?",
                Arrays.asList("La ignoró", "Bebió", "Saltó dentro", "Tomó una foto"), 1));

        lista.add(new Pregunta("¿Qué animal ayudó a Leo?",
                Arrays.asList("Un zorro", "Un ciervo", "Un búho", "Un gato"), 2));

        return lista;
    }
}
