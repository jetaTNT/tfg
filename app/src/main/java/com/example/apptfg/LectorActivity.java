package com.example.apptfg;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.apptfg.modelos.*;
import com.example.apptfg.util.GestorDePuntos;
import java.util.List;

public class LectorActivity extends AppCompatActivity {
    private int libroId;
    private List<Pagina> paginas;
    private int paginaIndex = 0;
    private TextView tvPuntos, tvPagina;
    private Button btnSiguiente;

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        libroId = getIntent().getIntExtra("libroId", -1);
        if (libroId==-1) { Log.e("LectorAct","libroId faltante"); finish(); return; }
        setContentView(R.layout.activity_lector);

        GestorDePuntos.init(this);
        tvPuntos = findViewById(R.id.tvPuntos);
        tvPagina  = findViewById(R.id.tvPagina);
        btnSiguiente = findViewById(R.id.btnSiguiente);

        Libro libro = LibroRepository.obtenerPorId(libroId);
        paginas = (libro!=null? libro.getPaginas(): null);
        if (paginas==null||paginas.isEmpty()) { Log.e("LectorAct","sin páginas"); finish(); return; }

        mostrarPagina();
        btnSiguiente.setOnClickListener(v -> {
            paginaIndex++;
            if (paginaIndex < paginas.size()) {
                mostrarPagina();
            } else {
                // Al terminar, ir a PreguntasActivity
                Intent i = new Intent(this, PreguntasActivity.class);
                i.putExtra("libroId", libroId);
                startActivity(i);
                finish();
            }
        });
    }

    private void mostrarPagina() {
        Pagina p = paginas.get(paginaIndex);
        tvPagina.setText(p.getTexto());
        tvPuntos.setText("⭐ Puntos: " + GestorDePuntos.obtenerPuntos());
    }
}
