package com.example.apptfg;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apptfg.modelos.*;
import com.example.apptfg.util.*;

public class PreguntasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        String id = getIntent().getStringExtra("libroId");
        Pregunta pregunta = LibroRepository.obtenerPreguntaFinal(id);

        TextView tv = findViewById(R.id.tvPregunta);
        RadioGroup rg = findViewById(R.id.rgRespuestas);
        Button btn = findViewById(R.id.btnEnviar);

        tv.setText(pregunta.getTexto());
        for (String resp : pregunta.getRespuestas()) {
            RadioButton rb = new RadioButton(this);
            rb.setText(resp);
            rg.addView(rb);
        }

        btn.setOnClickListener(v -> {
            int sel = rg.getCheckedRadioButtonId();
            if (sel == -1) {
                Toast.makeText(this, "Selecciona una opción", Toast.LENGTH_SHORT).show();
                return;
            }
            int index = rg.indexOfChild(findViewById(sel));
            if (index == pregunta.getCorrecta()) {
                GestorDePuntos.sumar(5);
                Toast.makeText(this, "¡Respuesta correcta! +5 puntos", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Respuesta incorrecta", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, "Puntuación final: " + GestorDePuntos.getPuntos(), Toast.LENGTH_LONG).show();
            finish();
        });
    }
}