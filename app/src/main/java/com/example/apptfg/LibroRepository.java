package com.example.apptfg;

import com.example.apptfg.modelos.Libro;
import com.example.apptfg.modelos.Pagina;
import com.example.apptfg.modelos.Opcion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LibroRepository {

    public static List<Libro> obtenerLibros() {
        List<Libro> libros = new ArrayList<>();
        List<Pagina> paginasBosque = new ArrayList<>();

        paginasBosque.add(new Pagina(0, "Leo caminaba por el bosque cuando vio una luz misteriosa entre los árboles.",
                Arrays.asList(
                        new Opcion("Seguir la luz", 1),
                        new Opcion("Ignorarla y seguir el sendero", 2)
                )
        ));

        paginasBosque.add(new Pagina(1, "La luz lo llevó a un claro con una fuente brillante en el centro.",
                Arrays.asList(
                        new Opcion("Beber agua de la fuente", 3),
                        new Opcion("Tocar el agua con el dedo", 4)
                )
        ));

        paginasBosque.add(new Pagina(2, "Leo encontró un árbol muy antiguo con símbolos tallados.",
                Arrays.asList(
                        new Opcion("Leer los símbolos", 5),
                        new Opcion("Escalar el árbol", 6)
                )
        ));

        paginasBosque.add(new Pagina(3, "Al beber, Leo sintió una energía mágica recorrer su cuerpo. Una criatura apareció.",
                Arrays.asList(
                        new Opcion("Hablar con la criatura", 7),
                        new Opcion("Salir corriendo", 6)
                )
        ));

        paginasBosque.add(new Pagina(4, "El agua mostró imágenes del futuro. Leo vio un castillo escondido.",
                Arrays.asList(
                        new Opcion("Buscar el castillo", 7),
                        new Opcion("Regresar al claro", 2)
                )
        ));

        paginasBosque.add(new Pagina(5, "Los símbolos contaban la historia de un guardián del bosque que protege secretos.",
                Arrays.asList(
                        new Opcion("Seguir el mapa grabado", 7),
                        new Opcion("Esconderse bajo el árbol", 6)
                )
        ));

        paginasBosque.add(new Pagina(6, "Una rama se rompió y Leo cayó. Un búho lo ayudó a levantarse y le dio un consejo.",
                Arrays.asList(
                        new Opcion("Seguir el consejo del búho", 9),
                        new Opcion("Ignorar al búho", 2)
                )
        ));

        paginasBosque.add(new Pagina(7, "Leo llegó al castillo, pero unos guardianes lo detuvieron. 'No estás listo', dijeron. Leo volvió a casa pensativo.",
                Arrays.asList(
                        new Opcion("Regresar al inicio", 0)
                )
        ));

        paginasBosque.add(new Pagina(8, "Leo regresó al bosque y reflexionó sobre su aventura.",
                Arrays.asList(
                        new Opcion("Fin", -1)
                )
        ));

        paginasBosque.add(new Pagina(9, "Gracias al consejo del búho, Leo halló el verdadero camino al castillo encantado.",
                Arrays.asList(
                        new Opcion("Entrar al castillo", 10)
                )
        ));

        paginasBosque.add(new Pagina(10, "Al cruzar la puerta, Leo descubrió una sala de libros flotantes. Una voz mágica dijo: 'Has demostrado coraje y sabiduría. Ahora eres guardián del Bosque Encantado.'",
                Arrays.asList(
                        new Opcion("Aceptar el honor y terminar la aventura", -1)
                )
        ));

        libros.add(new Libro(1, "La Aventura de Leo – El Bosque Encantado", paginasBosque));
        return libros;
    }

    public static Libro obtenerPorId(int id) {
        for (Libro libro : obtenerLibros()) {
            if (libro.getId() == id) return libro;
        }
        return null;
    }
}
