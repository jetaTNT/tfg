package com.example.apptfg.ui.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.apptfg.R;
import com.example.apptfg.data.model.Pregunta;
import com.example.apptfg.ui.main.MainActivity;

import java.util.List;

public class PreguntasActivity extends AppCompatActivity {

    public static final String EXTRA_LIBRO_ID = "libro_id";

    private QuizViewModel quizViewModel;
    private TextView tvPregunta;
    private RadioGroup rgOpciones;
    private Button btnSiguiente;
    private TextView tvPuntosActuales;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        // 1) Configurar Toolbar
        toolbar = findViewById(R.id.toolbar_preguntas);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        // 2) Obtener vistas
        tvPregunta = findViewById(R.id.tvPregunta);
        rgOpciones = findViewById(R.id.rgOpciones);
        btnSiguiente = findViewById(R.id.btnSiguientePregunta);
        tvPuntosActuales = findViewById(R.id.tvPuntosActuales);

        // 3) Inicializar ViewModel
        int libroId = getIntent().getIntExtra(EXTRA_LIBRO_ID, -1);
        quizViewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        quizViewModel.init(libroId, getApplicationContext());

        // 4) Observar la pregunta actual
        quizViewModel.getPreguntaActual().observe(this, pregunta -> {
            if (pregunta != null) {
                // Si hay una pregunta, muéstrala
                mostrarPreguntaEnPantalla(pregunta);
            } else {
                // Si pregunta == null → fin del quiz
                Integer puntosObtenidos = quizViewModel.getPuntosAcumulados().getValue();
                int total = (puntosObtenidos != null ? puntosObtenidos : 0);
                quizViewModel.guardarPuntos(total);

                Toast.makeText(this,
                        "¡Has terminado el cuestionario! Puntos obtenidos: " + total,
                        Toast.LENGTH_LONG).show();

                // Volver a MainActivity limpiando la pila
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        // 5) Configurar botón "Siguiente"
        btnSiguiente.setOnClickListener(v -> {
            int seleccionadoId = rgOpciones.getCheckedRadioButtonId();
            if (seleccionadoId != -1) {
                // Obtener índice de la opción elegida
                View vistaSeleccionada = rgOpciones.findViewById(seleccionadoId);
                int index = rgOpciones.indexOfChild(vistaSeleccionada);
                quizViewModel.procesarRespuesta(index);
            } else {
                Toast.makeText(this, "Selecciona una opción antes de continuar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Muestra en pantalla la pregunta y genera dinámicamente los RadioButton con cada opción.
     */
    private void mostrarPreguntaEnPantalla(Pregunta pregunta) {
        tvPregunta.setText(pregunta.getTexto());
        rgOpciones.removeAllViews();
        List<String> opciones = pregunta.getOpciones();
        for (int i = 0; i < opciones.size(); i++) {
            RadioButton rb = new RadioButton(this);
            rb.setText(opciones.get(i));
            rb.setTextSize(18f);
            // Puedes aplicar estilos adicionales (margen, color, etc.) si quieres
            rgOpciones.addView(rb);
        }
        // Ocultamos el TextView de puntos parciales para que no moleste
        tvPuntosActuales.setVisibility(View.GONE);
    }
}
