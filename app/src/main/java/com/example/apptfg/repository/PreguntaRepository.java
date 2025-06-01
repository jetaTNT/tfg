package com.example.apptfg.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.apptfg.data.model.Pregunta;
import com.example.apptfg.util.JsonUtil;

import java.util.List;

public class PreguntaRepository {

    private final MutableLiveData<List<Pregunta>> preguntasLiveData = new MutableLiveData<>();

    /**
     * Devuelve un LiveData con la lista de Pregunta leída del JSON del libro.
     * Se asume que el JSON se llama "libro{libroId}.json" y está en assets/books/.
     */
    public LiveData<List<Pregunta>> getPreguntas(Context context, int libroId) {
        new Thread(() -> {
            String nombreArchivo = "libro" + libroId + ".json";
            List<Pregunta> lista = JsonUtil.cargarPreguntasDesdeLibroJson(context, nombreArchivo);
            preguntasLiveData.postValue(lista);
        }).start();
        return preguntasLiveData;
    }
}
