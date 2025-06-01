package com.example.apptfg.util;

import android.content.Context;

import com.example.apptfg.data.model.Libro;
import com.example.apptfg.data.model.Pregunta;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

/**
 * Utilidad para cargar archivos JSON desde assets/books/.
 * Contiene métodos para:
 *   1) cargar un objeto Libro completo,
 *   2) extraer el bloque "preguntas" dentro del mismo JSON de libro,
 *   3) un método genérico loadFromAsset para cualquier clase T.
 */
public class JsonUtil {

    /**
     * Carga un objeto Libro completo desde assets/books/<nombreArchivo>.
     * Por ejemplo: cargarLibroDesdeAssets(context, "libro1.json");
     */
    public static Libro cargarLibroDesdeAssets(Context context, String nombreArchivo) {
        try {
            InputStream is = context.getAssets().open("books/" + nombreArchivo);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String linea;
            while ((linea = reader.readLine()) != null) {
                sb.append(linea);
            }
            reader.close();
            is.close();

            return new Gson().fromJson(sb.toString(), Libro.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Carga el bloque "preguntas" que esté dentro del JSON del mismo libro.
     * El JSON debe tener, en la raíz, un array llamado "preguntas".
     * Ejemplo en assets/books/libro1.json:
     * {
     *   "id": 1,
     *   "titulo": "Mi libro...",
     *   "paginas": [ ... ],
     *   "preguntas": [
     *     {
     *       "id": 1,
     *       "texto": "...",
     *       "opciones": ["A","B","C"],
     *       "respuestaCorrectaIndex": 0
     *     },
     *     ...
     *   ]
     * }
     */
    public static List<Pregunta> cargarPreguntasDesdeLibroJson(Context context, String nombreArchivo) {
        try {
            InputStream is = context.getAssets().open("books/" + nombreArchivo);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String linea;
            while ((linea = reader.readLine()) != null) {
                sb.append(linea);
            }
            reader.close();
            is.close();

            // Parsear texto JSON completo a JsonObject
            JsonObject jsonObject = JsonParser.parseString(sb.toString()).getAsJsonObject();
            // Extraer el array "preguntas"
            if (!jsonObject.has("preguntas") || jsonObject.get("preguntas").isJsonNull()) {
                return Collections.emptyList();
            }
            JsonArray preguntasArray = jsonObject.getAsJsonArray("preguntas");

            // Convertir JsonArray a List<Pregunta>
            Type tipoLista = new TypeToken<List<Pregunta>>() {}.getType();
            return new Gson().fromJson(preguntasArray, tipoLista);
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Alias genérico para cargar cualquier objeto T desde assets/books/<nombreArchivo>.
     * Ejemplo de uso:
     *   Libro libro1 = JsonUtil.loadFromAsset(context, "libro1.json", Libro.class);
     *   MiClase obj = JsonUtil.loadFromAsset(context, "miarchivo.json", MiClase.class);
     */
    public static <T> T loadFromAsset(Context context, String nombreArchivo, Class<T> clase) {
        try {
            InputStream is = context.getAssets().open("books/" + nombreArchivo);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String linea;
            while ((linea = reader.readLine()) != null) {
                sb.append(linea);
            }
            reader.close();
            is.close();

            return new Gson().fromJson(sb.toString(), clase);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
