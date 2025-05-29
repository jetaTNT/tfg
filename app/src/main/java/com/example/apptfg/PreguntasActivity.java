package com.example.apptfg;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apptfg.modelos.Pregunta;
import com.example.apptfg.util.GestorDePuntos;

import java.util.List;

public class PreguntasActivity extends AppCompatActivity {

    private List<Pregunta> preguntas;
    private int preguntaIndex = 0;

    private TextView tvPregunta, tvTemporizador;
    private LinearLayout opcionesContainer;
    private GestorDePuntos gestor;
    private CountDownTimer temporizador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        int paginaFinalId = getIntent().getIntExtra("paginaFinalId", -1);
        if (paginaFinalId == -1) {
            finish(); return;
        }

        preguntas = PreguntaRepository.obtenerPreguntasPorPaginaFinal(paginaFinalId);
        gestor = new GestorDePuntos(this);

        tvPregunta = findViewById(R.id.tvPregunta);
        tvTemporizador = findViewById(R.id.tvTemporizador);
        opcionesContainer = findViewById(R.id.opcionesContainer);

        mostrarPregunta();
    }

    private void mostrarPregunta() {
        if (preguntaIndex >= preguntas.size()) {
            finish(); return;
        }

        Pregunta p = preguntas.get(preguntaIndex);
        tvPregunta.setText(p.getTexto());
        opcionesContainer.removeAllViews(); // Limpia botones anteriores

        // Crea botones dinámicamente
        for (int i = 0; i < p.getOpciones().size(); i++) {
            Button btn = new Button(this);
            btn.setText(p.getOpciones().get(i));
            btn.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            int finalI = i;
            btn.setOnClickListener(v -> {
                detenerTemporizador();
                evaluarRespuesta(finalI == p.getRespuestaCorrecta());
            });
            opcionesContainer.addView(btn);
        }

        iniciarTemporizador();
    }

    private void evaluarRespuesta(boolean esCorrecta) {
        gestor.registrarPreguntaRespondida(esCorrecta);
        preguntaIndex++;
        mostrarPregunta();
    }

    private void iniciarTemporizador() {
        tvTemporizador.setText("Tiempo: 10");
        temporizador = new CountDownTimer(10000, 1000) {
            int tiempo = 10;

            @Override
            public void onTick(long millisUntilFinished) {
                tiempo--;
                tvTemporizador.setText("Tiempo: " + tiempo);
            }

            @Override
            public void onFinish() {
                // Tiempo agotado: cuenta como incorrecto
                deshabilitarOpciones();
                evaluarRespuesta(false);
            }
        }.start();
    }

    private void detenerTemporizador() {
        if (temporizador != null) {
            temporizador.cancel();
        }
    }

    private void deshabilitarOpciones() {
        for (int i = 0; i < opcionesContainer.getChildCount(); i++) {
            View child = opcionesContainer.getChildAt(i);
            child.setEnabled(false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detenerTemporizador();
    }
}
