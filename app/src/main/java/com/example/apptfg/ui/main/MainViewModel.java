// Archivo: app/src/main/java/com/example/apptfg/ui/main/MainViewModel.java
package com.example.apptfg.ui.main;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import android.app.Application;

import com.example.apptfg.data.local.AppDatabase;
import com.example.apptfg.data.local.entities.Puntos;
import com.example.apptfg.data.model.Libro;
import com.example.apptfg.repository.LibroRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewModel de la pantalla principal:
 * - Carga la lista de libros disponibles desde assets/books/.
 * - Expone LiveData<List<Libro>> con esos libros.
 * - Expone LiveData<Puntos> con el último registro de puntos.
 */
public class MainViewModel extends AndroidViewModel {
    private final LibroRepository libroRepo;
    private final MutableLiveData<List<Libro>> librosLiveData = new MutableLiveData<>();
    private final LiveData<Puntos> puntosLive;

    public MainViewModel(@NonNull Application application) {
        super(application);

        // Instanciar el repositorio de libros
        libroRepo = new LibroRepository();

        // Iniciar la carga de libros disponibles
        cargarTodosLosLibros(application.getApplicationContext());

        // Obtener LiveData de puntos desde la base de datos
        AppDatabase db = AppDatabase.getDatabase(application);
        puntosLive = db.puntosDao().getUltimosPuntos();
    }

    /**
     * Devuelve LiveData con la lista de todos los libros.
     */
    public LiveData<List<Libro>> getLibros() {
        return librosLiveData;
    }

    /**
     * Devuelve LiveData<Puntos> con el registro más reciente de puntos.
     */
    public LiveData<Puntos> getPuntosLive() {
        return puntosLive;
    }

    /**
     * Permite volver a cargar la lista de libros (por ejemplo, al hacer pull-to-refresh).
     */
    public void refrescarLibros(Context context) {
        cargarTodosLosLibros(context);
    }

    /**
     * Carga todos los libros disponibles desde assets/books/.
     * Supone que están nombrados como "libro1.json", "libro2.json", etc.
     * Ajusta totalLibros al número real de archivos en assets/books/.
     */
    private void cargarTodosLosLibros(Context context) {
        new Thread(() -> {
            int totalLibros = 3; // Cambia a la cantidad real de JSON que tengas
            List<Libro> listaActualizada = new ArrayList<>();
            for (int i = 1; i <= totalLibros; i++) {
                Libro libro = libroRepo.getLibroSync(context, i);
                if (libro != null) {
                    listaActualizada.add(libro);
                }
            }
            librosLiveData.postValue(listaActualizada);
        }).start();
    }
}
