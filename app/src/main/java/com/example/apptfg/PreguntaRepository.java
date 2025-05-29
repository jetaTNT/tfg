package com.example.apptfg;

import java.util.ArrayList;
import java.util.List;

import com.example.apptfg.modelos.Pregunta;

/**
 * Provee preguntas para cada libro.
 */
public class PreguntaRepository {

    public static List<Pregunta> obtenerPreguntas(int libroId) {
        List<Pregunta> lista = new ArrayList<>();

        if (libroId == 1) {
            lista.add(new Pregunta(
                    "¿Quién despertó al león?",
                    "El ratón",
                    "El tigre",
                    "El mono",
                    "El oso",
                    "El ratón"
            ));
            lista.add(new Pregunta(
                    "¿Qué prometió el ratón?",
                    "Regalar comida",
                    "Ayudar al león",
                    "Correr más rápido",
                    "Robar queso",
                    "Ayudar al león"
            ));
            lista.add(new Pregunta(
                    "¿Cómo liberó el ratón al león?",
                    "Midiendo la red",
                    "Mordiendo las cuerdas",
                    "Haciendo palanca",
                    "Llamando a otros ratones",
                    "Mordiendo las cuerdas"
            ));
        } else if (libroId == 2) {
            lista.add(new Pregunta(
                    "¿Quién ganó la carrera?",
                    "La tortuga",
                    "La liebre",
                    "El caballo",
                    "El avestruz",
                    "La tortuga"
            ));
            lista.add(new Pregunta(
                    "¿Por qué la liebre perdió?",
                    "Se detuvo a descansar",
                    "Se perdió en el bosque",
                    "Tropezó con una piedra",
                    "Se distrajo con comida",
                    "Se detuvo a descansar"
            ));
            lista.add(new Pregunta(
                    "¿Qué moraleja enseña la fábula?",
                    "La creatividad es clave",
                    "La humildad evita errores",
                    "La paciencia y la constancia triunfan",
                    "La fuerza es lo más importante",
                    "La paciencia y la constancia triunfan"
            ));
        }

        return lista;
    }
}
