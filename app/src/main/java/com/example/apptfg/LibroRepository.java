package com.example.apptfg;


import com.example.apptfg.modelos.*;

import java.util.Arrays;
import java.util.List;

public class LibroRepository {

    public static List<Libro> obtenerLibros() {
        Pagina p0 = new Pagina(
                "Érase una vez un bosque misterioso... ¿Quieres entrar?",
                Arrays.asList(
                        new Opcion("Sí, entro al bosque", 1),
                        new Opcion("No, me doy la vuelta", 2)
                ));
        Pagina p1 = new Pagina(
                "Dentro del bosque ves una cabaña. ¿Qué haces?",
                Arrays.asList(
                        new Opcion("Tocar la puerta", 2),
                        new Opcion("Entrar sigilosamente", 2)
                ));
        Pagina p2 = new Pagina(
                "Fin del cuento. ¡Gracias por jugar!",
                Arrays.asList()
        );

        Libro cuento = new Libro("cuento1", "El bosque misterioso", "Autor Desconocido",
                Arrays.asList(p0, p1, p2));

        return Arrays.asList(cuento);
    }

    public static Pregunta obtenerPreguntaFinal(String libroId) {
        return new Pregunta(
                "¿Te gustó el final del cuento?",
                Arrays.asList("Sí", "No", "Quizá"),
                0
        );
    }
}