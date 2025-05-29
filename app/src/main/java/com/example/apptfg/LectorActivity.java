package com.example.apptfg;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apptfg.modelos.Libro;
import com.example.apptfg.modelos.Opcion;
import com.example.apptfg.modelos.Pagina;
import com.example.apptfg.util.GestorDePuntos;

import java.util.List;

public class LectorActivity extends AppCompatActivity {

    private int libroId;
    private List<Pagina> paginas;
    private int paginaIndex = 0;
    private TextView tvPuntos, tvPagina;
    private Button btnSiguiente;
    private LinearLayout layoutOpciones;
    private GestorDePuntos gestor;

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_lector);

        libroId = getIntent().getIntExtra("libroId", -1);
        if (libroId == -1) {
            Log.e("LectorAct", "libroId faltante");
            finish();
            return;
        }

        gestor = new GestorDePuntos(this);

        tvPuntos = findViewById(R.id.tvPuntos);
        tvPagina = findViewById(R.id.tvPagina);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        layoutOpciones = findViewById(R.id.layoutOpciones);

        Libro libro = LibroRepository.obtenerPorId(libroId);
        paginas = (libro != null ? libro.getPaginas() : null);
        if (paginas == null || paginas.isEmpty()) {
            Log.e("LectorAct", "sin páginas");
            finish();
            return;
        }

        btnSiguiente.setOnClickListener(v -> avanzarPagina());
        mostrarPagina();
    }

    private void mostrarPagina() {
        Pagina p = paginas.get(paginaIndex);
        tvPagina.setText(p.getTexto());
        tvPuntos.setText("⭐ Puntos: " + gestor.puntosActuales());

        layoutOpciones.removeAllViews();

        List<Opcion> opciones = p.getOpciones();
        if (opciones != null && !opciones.isEmpty()) {
            btnSiguiente.setVisibility(View.GONE);
            for (Opcion opcion : opciones) {
                Button btn = new Button(this);
                btn.setText(opcion.getTexto());
                btn.setOnClickListener(v -> {
                    int destinoId = opcion.getSiguientePaginaId();

                    if (destinoId == -1) {
                        avanzarPagina(); // Final real: iniciar preguntas
                        return;
                    }

                    for (int i = 0; i < paginas.size(); i++) {
                        if (paginas.get(i).getId() == destinoId) {
                            paginaIndex = i;
                            mostrarPagina();
                            return;
                        }
                    }
                });
                layoutOpciones.addView(btn);
            }
        } else {
            btnSiguiente.setVisibility(View.VISIBLE);
        }
    }

    private void avanzarPagina() {
        paginaIndex++;
        if (paginaIndex < paginas.size()) {
            mostrarPagina();
        } else {
            tvPagina.setText("🌟 ¡Fin de la historia! Preparando tus preguntas...");
            btnSiguiente.setVisibility(View.GONE);

            new Handler().postDelayed(() -> {
                Intent i = new Intent(this, PreguntasActivity.class);
                i.putExtra("libroId", libroId);
                i.putExtra("paginaFinalId", paginas.get(paginas.size() - 1).getId());
                startActivity(i);
                finish();
            }, 2000);
        }
    }
}
