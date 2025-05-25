package com.example.apptfg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apptfg.modelos.*;
import com.example.apptfg.util.GestorDePuntos;

import java.util.List;

public class LectorActivity extends AppCompatActivity {

    private List<Pagina> paginas;
    private int paginaActual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lector);

        String id = getIntent().getStringExtra("libroId");
        Libro libro = LibroRepository.obtenerLibros().stream()
                .filter(l -> l.getId().equals(id)).findFirst().orElse(null);
        paginas = libro.getPaginas();

        mostrarPagina();
    }

    private void mostrarPagina() {
        TextView tv = findViewById(R.id.tvContenido);
        LinearLayout ll = findViewById(R.id.llOpciones);
        ll.removeAllViews();

        Pagina p = paginas.get(paginaActual);
        tv.setText(p.getContenido());

        for (Opcion op : p.getOpciones()) {
            Button btn = new Button(this);
            btn.setText(op.getTexto());
            btn.setOnClickListener(v -> {
                GestorDePuntos.sumar(1);
                paginaActual = op.getSiguientePagina();
                if (paginaActual >= paginas.size()) {
                    lanzarPreguntas();
                } else {
                    mostrarPagina();
                }
            });
            ll.addView(btn);
        }

        if (p.getOpciones().isEmpty()) {
            Button btn = new Button(this);
            btn.setText("Continuar");
            btn.setOnClickListener(v -> lanzarPreguntas());
            ll.addView(btn);
        }
    }

    private void lanzarPreguntas() {
        Intent i = new Intent(this, PreguntasActivity.class);
        i.putExtra("libroId", getIntent().getStringArrayExtra("libroId"));
        startActivity(i);
        finish();
    }
}