package com.example.apptfg;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apptfg.modelos.AppDatabase;
import com.example.apptfg.modelos.Puntos;
import com.example.apptfg.modelos.PuntosDao;

import java.util.concurrent.Executors;

public class StatsActivity extends AppCompatActivity {

    private TextView tvTotalPuntos, tvPreguntasRespondidas, tvPreguntasCorrectas, tvPreguntasIncorrectas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        tvTotalPuntos = findViewById(R.id.tvTotalPuntos);
        tvPreguntasRespondidas = findViewById(R.id.tvPreguntasRespondidas);
        tvPreguntasCorrectas = findViewById(R.id.tvPreguntasCorrectas);
        tvPreguntasIncorrectas = findViewById(R.id.tvPreguntasIncorrectas);
        Button btnVolver = findViewById(R.id.btnVolver);

        PuntosDao puntosDao = AppDatabase.getInstance(this).puntosDao();

        Executors.newSingleThreadExecutor().execute(() -> {
            Puntos puntos = puntosDao.obtenerPuntos();
            int total = (puntos != null) ? puntos.getCantidad() : 0;
            int respondidas = (puntos != null) ? puntos.getPreguntasRespondidas() : 0;
            int correctas = (puntos != null) ? puntos.getPreguntasCorrectas() : 0;
            int incorrectas = respondidas - correctas;

            runOnUiThread(() -> {
                tvTotalPuntos.setText("Puntos: " + total);
                tvPreguntasRespondidas.setText("Preguntas respondidas: " + respondidas);
                tvPreguntasCorrectas.setText("Correctas: " + correctas);
                tvPreguntasIncorrectas.setText("Incorrectas: " + incorrectas);
            });
        });

        btnVolver.setOnClickListener(v -> {
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
    }
}
