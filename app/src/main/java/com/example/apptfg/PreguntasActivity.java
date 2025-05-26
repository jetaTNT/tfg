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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1) Recuperar el ID del libro pasado desde MainActivity
        libroId = getIntent().getIntExtra("libroId", -1);
        if (libroId == -1) {
            Log.e("PreguntasActivity", "libroId no recibido");
            finish();
            return;
        }

        setContentView(R.layout.activity_preguntas);

        // 2) Inicializar Room para puntos
        GestorDePuntos.init(this);

        // 3) Vincular vistas
        tvPuntos   = findViewById(R.id.tvPuntos);
        tvPregunta = findViewById(R.id.tvPregunta);
        btnOpcion1 = findViewById(R.id.btnOpcion1);
        btnOpcion2 = findViewById(R.id.btnOpcion2);
        btnOpcion3 = findViewById(R.id.btnOpcion3);
        btnOpcion4 = findViewById(R.id.btnOpcion4);

        // 4) Cargar las preguntas para este libro
        preguntas = PreguntaRepository.obtenerPreguntas(libroId);
        if (preguntas.isEmpty()) {
            Log.e("PreguntasActivity", "No hay preguntas para libro " + libroId);
            finish();
            return;
        }

        // 5) Mostrar puntos actuales en pantalla
        tvPuntos.setText("Puntos: " + GestorDePuntos.obtenerPuntos());

        // 6) Mostrar la primera pregunta
        mostrarPregunta();

        // 7) Crear listener común para las cuatro opciones
        View.OnClickListener listener = v -> {
            int seleccion;
            if (v == btnOpcion1) seleccion = 0;
            else if (v == btnOpcion2) seleccion = 1;
            else if (v == btnOpcion3) seleccion = 2;
            else seleccion = 3;

            Pregunta actual = preguntas.get(idx);
            int puntosGanados = (seleccion == actual.getCorrecta()) ? 5 : 0;

            // 8) Sumar y guardar los puntos en Room
            GestorDePuntos.sumarPuntos(puntosGanados);

            // 9) Mostrar toast según resultado
            Toast.makeText(
                    this,
                    puntosGanados > 0
                            ? "¡Correcto! +" + puntosGanados + " puntos"
                            : "Incorrecto",
                    Toast.LENGTH_SHORT
            ).show();

            // 10) Actualizar el contador en pantalla
            tvPuntos.setText("Puntos: " + GestorDePuntos.obtenerPuntos());

            // 11) Avanzar a la siguiente pregunta o terminar
            idx++;
            if (idx < preguntas.size()) {
                mostrarPregunta();
            } else {
                finish();
            }
        };

        // 12) Asignar el listener a cada botón
        btnOpcion1.setOnClickListener(listener);
        btnOpcion2.setOnClickListener(listener);
        btnOpcion3.setOnClickListener(listener);
        btnOpcion4.setOnClickListener(listener);
    }

    /**
     * Carga en pantalla la pregunta actual y sus opciones.
     */
    private void mostrarPregunta() {
        Pregunta p = preguntas.get(idx);
        tvPregunta.setText(p.getPregunta());
        btnOpcion1.setText(p.getRespuestas().get(0));
        btnOpcion2.setText(p.getRespuestas().get(1));
        btnOpcion3.setText(p.getRespuestas().get(2));
        btnOpcion4.setText(p.getRespuestas().get(3));
    }
}
