// Archivo: app/src/main/java/com/example/apptfg/ui/quiz/QuizViewModel.java
package com.example.apptfg.ui.quiz;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.apptfg.data.local.AppDatabase;
import com.example.apptfg.data.local.dao.PuntosDao;
import com.example.apptfg.data.local.entities.Puntos;
import com.example.apptfg.data.model.Pregunta;
import com.example.apptfg.repository.PreguntaRepository;

import java.util.List;

/**
 * ViewModel que gestiona el cuestionario (quiz) de un libro.
 * - Carga lista de preguntas desde JSON.
 * - Expone LiveData<Pregunta> con la pregunta actual.
 * - Mantiene y expone LiveData<Integer> con puntos acumulados.
 * - Guarda el total en la BD cuando el quiz termina.
 */
public class QuizViewModel extends AndroidViewModel {

    private final PreguntaRepository preguntaRepo;
    private List<Pregunta> listaPreguntas;
    private final MutableLiveData<Pregunta> preguntaActual = new MutableLiveData<>();
    private final MutableLiveData<Integer> puntosAcumulados = new MutableLiveData<>(0);
    private int indiceActual = 0;

    public QuizViewModel(@NonNull Application application) {
        super(application);
        preguntaRepo = new PreguntaRepository();
    }

    /**
     * Inicializa el ViewModel cargando las preguntas del libro.
     * Publica la primera pregunta si existe.
     *
     * @param libroId El ID del libro (para saber qué JSON leer).
     * @param context Contexto de la Activity.
     */
    public void init(int libroId, Context context) {
        preguntaRepo.getPreguntas(context, libroId).observeForever(preguntas -> {
            listaPreguntas = preguntas;
            indiceActual = 0;
            puntosAcumulados.postValue(0);

            if (listaPreguntas != null && !listaPreguntas.isEmpty()) {
                preguntaActual.postValue(listaPreguntas.get(0));
            } else {
                // No hay preguntas definidas
                preguntaActual.postValue(null);
            }
        });
    }

    /** LiveData que la Activity observa para mostrar la pregunta actual. */
    public LiveData<Pregunta> getPreguntaActual() {
        return preguntaActual;
    }

    /** LiveData que la Activity observa para mostrar los puntos acumulados. */
    public LiveData<Integer> getPuntosAcumulados() {
        return puntosAcumulados;
    }

    /**
     * Procesa la respuesta seleccionada:
     * - Si acierta, suma 10 puntos.
     * - Avanza a la siguiente pregunta o finaliza (postValue(null)).
     *
     * @param indexSeleccionado Índice de la opción elegida.
     */
    public void procesarRespuesta(int indexSeleccionado) {
        Pregunta actual = preguntaActual.getValue();
        if (actual == null) return;

        if (indexSeleccionado == actual.getRespuestaCorrectaIndex()) {
            Integer pts = puntosAcumulados.getValue();
            int nuevos = (pts != null ? pts : 0) + 10;
            puntosAcumulados.postValue(nuevos);
        }

        indiceActual++;
        if (indiceActual < listaPreguntas.size()) {
            preguntaActual.postValue(listaPreguntas.get(indiceActual));
        } else {
            // Fin del quiz
            preguntaActual.postValue(null);
        }
    }

    /**
     * Guarda el total de puntos obtenidos en la BD local.
     *
     * @param puntos Total de puntos a insertar.
     */
    public void guardarPuntos(int puntos) {
        new Thread(() -> {
            AppDatabase db = AppDatabase.getDatabase(getApplication());
            PuntosDao dao = db.puntosDao();
            Puntos entidad = new Puntos();      // Ahora existe constructor vacío
            entidad.setCantidad(puntos);
            dao.insertarPuntos(entidad);
        }).start();
    }
}
