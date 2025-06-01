// Archivo: app/src/main/java/com/example/apptfg/ui/lector/ReaderViewModel.java
package com.example.apptfg.ui.lector;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.apptfg.data.model.Libro;
import com.example.apptfg.data.model.Pagina;
import com.example.apptfg.repository.LibroRepository;

import java.util.List;

/**
 * ViewModel que gestiona la lectura de un libro interactivo:
 * - Carga el Libro desde JSON en assets/books/libro{libroId}.json.
 * - Expone LiveData<Pagina> con la página actual.
 * - Proporciona irASiguientePagina(int) para avanzar según el ID de página.
 */
public class ReaderViewModel extends AndroidViewModel {
    private LibroRepository libroRepo;
    private LiveData<Libro> libroLiveData;
    private final MutableLiveData<Pagina> paginaActual = new MutableLiveData<>();

    public ReaderViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * Inicializa el ViewModel cargando el Libro:
     * @param libroId  El ID numérico del libro (coincide con nombre "libro{libroId}.json").
     * @param context  Contexto para acceder a assets.
     */
    public void init(int libroId, Context context) {
        libroRepo = new LibroRepository();
        libroLiveData = libroRepo.getLibro(context, libroId);
        libroLiveData.observeForever(libro -> {
            if (libro != null && libro.getPaginas() != null && !libro.getPaginas().isEmpty()) {
                // Publicar la primera página (índice 0)
                paginaActual.postValue(libro.getPaginas().get(0));
            }
        });
    }

    /**
     * LiveData para que la Activity observe la página actual.
     */
    public LiveData<Pagina> getPaginaActual() {
        return paginaActual;
    }

    /**
     * Avanza a la página cuyo ID coincide con siguientePaginaId.
     * Busca dentro de libro.getPaginas().
     * @param siguientePaginaId ID de la página destino.
     */
    public void irASiguientePagina(int siguientePaginaId) {
        Libro libro = libroLiveData.getValue();
        if (libro == null || libro.getPaginas() == null) {
            return;
        }
        List<Pagina> paginas = libro.getPaginas();
        for (Pagina p : paginas) {
            if (p.getId() == siguientePaginaId) {
                paginaActual.postValue(p);
                return;
            }
        }
    }
}
