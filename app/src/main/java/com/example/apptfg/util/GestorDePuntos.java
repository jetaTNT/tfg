package com.example.apptfg.util;

import android.content.Context;

import com.example.apptfg.modelos.AppDatabase;
import com.example.apptfg.modelos.Puntos;
import com.example.apptfg.modelos.PuntosDao;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class GestorDePuntos {

    private final PuntosDao puntosDao;
    private final ExecutorService executor;

    public GestorDePuntos(Context context) {
        this.puntosDao = AppDatabase.getInstance(context).puntosDao();
        this.executor = Executors.newSingleThreadExecutor();
    }

    public void registrarPreguntaRespondida(boolean esCorrecta) {
        executor.execute(() -> {
            Puntos puntos = puntosDao.obtenerPuntos();

            if (puntos == null) {
                puntos = new Puntos(0, 0, 0);
            }

            puntos.setPreguntasRespondidas(puntos.getPreguntasRespondidas() + 1);

            if (esCorrecta) {
                puntos.setPreguntasCorrectas(puntos.getPreguntasCorrectas() + 1);
                puntos.setCantidad(puntos.getCantidad() + 10); // +10 por acierto
            }

            puntosDao.guardarPuntos(puntos);
        });
    }

    public int puntosActuales() {
        Callable<Integer> task = () -> {
            Puntos puntos = puntosDao.obtenerPuntos();
            return (puntos != null) ? puntos.getCantidad() : 0;
        };

        Future<Integer> future = executor.submit(task);

        try {
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
