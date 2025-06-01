// Archivo: app/src/main/java/com/example/apptfg/repository/LibroRepository.java
package com.example.apptfg.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.apptfg.data.model.Libro;
import com.example.apptfg.util.JsonUtil;

/**
 * Repositorio encargado de cargar un Libro desde assets/books/libro{libroId}.json.
 */
public class LibroRepository {
    private final MutableLiveData<Libro> libroLiveData = new MutableLiveData<>();

    /**
     * Devuelve un LiveData<Libro> que se completa cuando se carga el JSON del libro de forma asíncrona.
     * @param context Contexto necesario para acceder a assets.
     * @param libroId El identificador numérico del libro (por ejemplo, 1 = "libro1.json").
     */
    public LiveData<Libro> getLibro(Context context, int libroId) {
        new Thread(() -> {
            String nombreArchivo = "libro" + libroId + ".json";
            Libro libro = JsonUtil.cargarLibroDesdeAssets(context, nombreArchivo);
            libroLiveData.postValue(libro);
        }).start();
        return libroLiveData;
    }

    /**
     * Carga de forma síncrona el Libro desde assets/books/libro{libroId}.json.
     * Útil para ser llamado desde un hilo de fondo en lugar de LiveData.
     * @param context Contexto necesario para acceder a assets.
     * @param libroId El identificador numérico del libro.
     * @return El objeto Libro o null si hubo error.
     */
    public Libro getLibroSync(Context context, int libroId) {
        String nombreArchivo = "libro" + libroId + ".json";
        return JsonUtil.cargarLibroDesdeAssets(context, nombreArchivo);
    }
}
