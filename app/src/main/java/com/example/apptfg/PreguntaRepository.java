package com.example.apptfg;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.apptfg.modelos.*;

/**
 * Provee preguntas para cada libro.
 */
public class PreguntaRepository {
    public static List<Pregunta> obtenerPreguntas(int libroId) {
        List<Pregunta> lista = new ArrayList<>();
        if (libroId == 1) {
            lista.add(new Pregunta(
                    "¿Quién despertó al león?",
                    Arrays.asList("El ratón", "El tigre", "El mono", "El oso"),
                    0
            ));
            lista.add(new Pregunta(
                    "¿Qué prometió el ratón?",
                    Arrays.asList("Regalar comida", "Ayudar al león", "Correr más rápido", "Robar queso"),
                    1
            ));
            lista.add(new Pregunta(
                    "¿Cómo liberó el ratón al león?",
                    Arrays.asList("Midiendo la red", "Mordiendo las cuerdas", "Haciendo palanca", "Llamando a otros ratones"),
                    1
            ));
        } else if (libroId == 2) {
            lista.add(new Pregunta(
                    "¿Quién ganó la carrera?",
                    Arrays.asList("La tortuga", "La liebre", "El caballo", "El avestruz"),
                    0
            ));
            lista.add(new Pregunta(
                    "¿Por qué la liebre perdió?",
                    Arrays.asList(
                            "Se detuvo a descansar",
                            "Se perdió en el bosque",
                            "Tropezó con una piedra",
                            "Se distrajo con comida"
                    ),
                    0
            ));
            lista.add(new Pregunta(
                    "¿Qué moraleja enseña la fábula?",
                    Arrays.asList(
                            "La creatividad es clave",
                            "La humildad evita errores",
                            "La paciencia y la constancia triunfan",
                            "La fuerza es lo más importante"
                    ),
                    2
            ));
        }
        return lista;
    }
}