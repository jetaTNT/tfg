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

public class MainActivity extends AppCompatActivity implements LibrosAdapter.OnLibroClickListener {

    private TextView tvPuntosMain;
    private GestorDePuntos gestor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPuntosMain = findViewById(R.id.tvPuntosMain);
        RecyclerView rvLibros = findViewById(R.id.rvLibros);
        Button btnStats = findViewById(R.id.btnStats);

        rvLibros.setLayoutManager(new LinearLayoutManager(this));

        List<Libro> libros = LibroRepository.obtenerLibros();
        LibrosAdapter adapter = new LibrosAdapter(libros, this);
        rvLibros.setAdapter(adapter);

        gestor = new GestorDePuntos(this);
        actualizarPuntos();

        btnStats.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, StatsActivity.class);
            startActivity(intent);
        });
    }

    private void actualizarPuntos() {
        int puntos = gestor.puntosActuales();
        tvPuntosMain.setText("\u2B50 Puntos: " + puntos);
    }

    @Override
    public void onLibroClick(Libro libro) {
        Intent intent = new Intent(this, LectorActivity.class);
        intent.putExtra("libroId", libro.getId());
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        actualizarPuntos();
    }
}
