package com.example.apptfg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptfg.modelos.Libro;
import com.example.apptfg.util.GestorDePuntos;

import java.util.List;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements LibrosAdapter.OnLibroClickListener {

    private TextView tvPuntosMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPuntosMain = findViewById(R.id.tvPuntosMain);

        // Lista de libros
        RecyclerView rv = findViewById(R.id.rvLibros);
        rv.setLayoutManager(new LinearLayoutManager(this));

        List<Libro> libros = LibroRepository.obtenerLibros();
        LibrosAdapter adapter = new LibrosAdapter(libros, this);
        rv.setAdapter(adapter);

        // Botón de estadísticas
        Button btnStats = findViewById(R.id.btnStats);
        btnStats.setOnClickListener(v -> {
            Intent intent = new Intent(this, StatsActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        GestorDePuntos gestor = new GestorDePuntos(this);

        Executors.newSingleThreadExecutor().execute(() -> {
            int puntos = gestor.puntosActuales();

            runOnUiThread(() -> {
                tvPuntosMain.setText("⭐ Puntos: " + puntos);
            });
        });
    }

    @Override
    public void onLibroClick(Libro libro) {
        Intent intent = new Intent(this, LectorActivity.class);
        intent.putExtra("libroId", libro.getId());
        startActivity(intent);
    }
}
