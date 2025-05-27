package com.example.apptfg;

import com.example.apptfg.modelos.*;
import com.example.apptfg.util.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Provee libros y sus páginas. En real, cargarías JSON o BD.
 */
public class LibroRepository {
    public static List<Libro> obtenerLibros() {
        List<Libro> lista = new ArrayList<>();
        // Libro 1: "El león y el ratón"
        lista.add(new Libro(
                1,
                "El león y el ratón",
                generarPaginasLeonYRaton()
        ));
        // Libro 2: "La liebre y la tortuga"
        lista.add(new Libro(
                2,
                "La liebre y la tortuga",
                generarPaginasLiebreYTortuga()
        ));
        return lista;
    }

    private static List<Pagina> generarPaginasLeonYRaton() {
        List<Pagina> p = new ArrayList<>();
        p.add(new Pagina(1,
                "Un día de verano, un león dormía plácidamente en la selva. Un pequeño ratón pasó por encima de su cuerpo sin darse cuenta de su presencia.",
                0));
        p.add(new Pagina(2,
                "El ratón, al despertarle un leve cosquilleo, corrió asustado y despertó al león, quien lo atrapó con su garra.",
                0));
        p.add(new Pagina(3,
                "El ratón rogó por su vida y prometió ayudar al león algún día. El león, divertido, lo dejó libre. Más tarde, el león cayó en una red de cazadores y fue liberado por el ratón.",
                0));
        return p;
    }

    private static List<Pagina> generarPaginasLiebreYTortuga() {
        List<Pagina> p = new ArrayList<>();
        p.add(new Pagina(1,
                "La liebre alardeaba de su velocidad, burlándose de la tortuga lenta. Cansada de burlas, la tortuga la desafió a una carrera.",
                0));
        p.add(new Pagina(2,
                "La liebre salió disparada y, confiada en su victoria, decidió descansar a mitad de camino. Mientras tanto, la tortuga avanzaba despacio pero sin detenerse.",
                0));
        p.add(new Pagina(3,
                "Al despertar, la liebre vio que la tortuga estaba a punto de cruzar la meta. Corrió pero no alcanzó. La tortuga ganó la carrera.",
                0));
        return p;
    }

    public static Libro obtenerPorId(int id) {
        for (Libro l : obtenerLibros()) {
            if (l.getId() == id) return l;
        }
        return null;
    }
}