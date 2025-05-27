package com.example.apptfg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptfg.modelos.Libro;
import com.example.apptfg.util.GestorDePuntos;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView tvPuntosMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1) Inicializa Room para puntos
        GestorDePuntos.init(this);

        // 2) Vincula y muestra el contador
        tvPuntosMain = findViewById(R.id.tvPuntosMain);
        actualizarPuntos();

        // 3) Configura la lista de libros
        RecyclerView rv = findViewById(R.id.rvLibros);
        rv.setLayoutManager(new LinearLayoutManager(this));
        List<Libro> lista = LibroRepository.obtenerLibros();
        rv.setAdapter(new LibrosAdapter(lista, libro -> {
            // Si quieres, puedes resetear al inicio de cada libro:
            // GestorDePuntos.reset();
            actualizarPuntos();

            // Lanzar LectorActivity
            Intent i = new Intent(MainActivity.this, LectorActivity.class);
            i.putExtra("libroId", libro.getId());
            startActivity(i);
        }));
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Al volver de PreguntasActivity, recarga el total
        actualizarPuntos();
    }

    private void actualizarPuntos() {
        int pts = GestorDePuntos.obtenerPuntos();
        tvPuntosMain.setText("⭐ Puntos: " + pts);
    }
}
