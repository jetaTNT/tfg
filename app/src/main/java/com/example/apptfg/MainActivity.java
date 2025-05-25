package com.example.apptfg;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptfg.modelos.Libro;
import com.example.apptfg.util.GestorDePuntos;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = findViewById(R.id.rvLibros);
        rv.setLayoutManager(new LinearLayoutManager(this));

        List<Libro> libros = LibroRepository.obtenerLibros();
        LibrosAdapter adapter = new LibrosAdapter(libros, libro -> {
            GestorDePuntos.reset();
            Intent i = new Intent(MainActivity.this, LectorActivity.class);
            i.putExtra("libroId", libro.getId());
            startActivity(i);
        });
        rv.setAdapter(adapter);
    }
}