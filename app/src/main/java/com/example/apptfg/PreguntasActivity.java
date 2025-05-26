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
    private TextView tvPuntos, tvPregunta;
    private Button btnOpcion1, btnOpcion2, btnOpcion3, btnOpcion4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        libroId = getIntent().getIntExtra("libroId", -1);
        if (libroId == -1) {
            Log.e("PreguntasAct","libroId faltante"); finish(); return;
        }
        setContentView(R.layout.activity_preguntas);
        GestorDePuntos.init(this);

        // Vincular vistas
        tvPuntos   = findViewById(R.id.tvPuntos);
        tvPregunta = findViewById(R.id.tvPregunta);
        btnOpcion1 = findViewById(R.id.btnOpcion1);
        btnOpcion2 = findViewById(R.id.btnOpcion2);
        btnOpcion3 = findViewById(R.id.btnOpcion3);
        btnOpcion4 = findViewById(R.id.btnOpcion4);

        // Cargar preguntas desde repo
        preguntas = PreguntaRepository.obtenerPreguntas(libroId);
        if (preguntas.isEmpty()) {
            Log.e("PreguntasAct","sin preguntas para libro " + libroId);
            finish(); return;
        }

        actualizarPuntos();
        mostrarPregunta();

        View.OnClickListener l = v -> {
            int sel;
            if (v == btnOpcion1) sel = 0;
            else if (v == btnOpcion2) sel = 1;
            else if (v == btnOpcion3) sel = 2;
            else sel = 3;

            Pregunta actual = preguntas.get(idx);
            int pts = (sel == actual.getCorrecta()) ? 5 : 0;
            GestorDePuntos.sumarPuntos(pts);

            Toast.makeText(this,
                    pts>0 ? "¡Correcto! +"+pts+" puntos" : "Incorrecto",
                    Toast.LENGTH_SHORT).show();

            actualizarPuntos();
            idx++;
            if (idx < preguntas.size()) mostrarPregunta();
            else finish();
        };

        btnOpcion1.setOnClickListener(l);
        btnOpcion2.setOnClickListener(l);
        btnOpcion3.setOnClickListener(l);
        btnOpcion4.setOnClickListener(l);
    }

    private void actualizarPuntos() {
        tvPuntos.setText("Puntos: " + GestorDePuntos.obtenerPuntos());
    }

    private void mostrarPregunta() {
        Pregunta p = preguntas.get(idx);
        tvPregunta.setText(p.getPregunta());
        btnOpcion1.setText(p.getRespuestas().get(0));
        btnOpcion2.setText(p.getRespuestas().get(1));
        btnOpcion3.setText(p.getRespuestas().get(2));
        btnOpcion4.setText(p.getRespuestas().get(3));
    }
}