package com.example.apptfg;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apptfg.modelos.Pregunta;
import com.example.apptfg.util.GestorDePuntos;

import java.util.List;

public class PreguntasActivity extends AppCompatActivity {

    private int libroId;
    private int idx = 0;
    private List<Pregunta> preguntas;

    private TextView tvPuntos;
    private TextView tvPregunta;
    private Button btnOpcion1;
    private Button btnOpcion2;
    private Button btnOpcion3;
    private Button btnOpcion4;

    private GestorDePuntos gestorDePuntos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        libroId = getIntent().getIntExtra("libroId", -1);
        if (libroId == -1) {
            Log.e("PreguntasActivity", "ID de libro no recibido");
            finish();
            return;
        }

        gestorDePuntos = new GestorDePuntos(this);

        tvPuntos = findViewById(R.id.tvPuntos);
        tvPregunta = findViewById(R.id.tvPregunta);
        btnOpcion1 = findViewById(R.id.btnOpcion1);
        btnOpcion2 = findViewById(R.id.btnOpcion2);
        btnOpcion3 = findViewById(R.id.btnOpcion3);
        btnOpcion4 = findViewById(R.id.btnOpcion4);

        preguntas = PreguntaRepository.obtenerPreguntas(libroId);
        if (preguntas.isEmpty()) {
            Log.e("PreguntasActivity", "No hay preguntas para el libro " + libroId);
            finish();
            return;
        }

        actualizarVista();

        View.OnClickListener listener = v -> {
            String respuesta = ((Button) v).getText().toString();
            Pregunta preguntaActual = preguntas.get(idx);

            boolean esCorrecta = respuesta.equals(preguntaActual.getOpcionCorrecta());
            gestorDePuntos.registrarPreguntaRespondida(esCorrecta);

            Toast.makeText(this,
                    esCorrecta ? "¡Correcto!" : "Incorrecto. Era: " + preguntaActual.getOpcionCorrecta(),
                    Toast.LENGTH_SHORT).show();

            idx++;
            if (idx < preguntas.size()) {
                actualizarVista();
            } else {
                Toast.makeText(this, "Has respondido todas las preguntas", Toast.LENGTH_LONG).show();
                finish();
            }
        };

        btnOpcion1.setOnClickListener(listener);
        btnOpcion2.setOnClickListener(listener);
        btnOpcion3.setOnClickListener(listener);
        btnOpcion4.setOnClickListener(listener);
    }

    private void actualizarVista() {
        Pregunta pregunta = preguntas.get(idx);
        tvPregunta.setText(pregunta.getEnunciado());
        btnOpcion1.setText(pregunta.getOpcion1());
        btnOpcion2.setText(pregunta.getOpcion2());
        btnOpcion3.setText(pregunta.getOpcion3());
        btnOpcion4.setText(pregunta.getOpcion4());

        tvPuntos.setText("⭐ Puntos: " + gestorDePuntos.puntosActuales());
    }
}
