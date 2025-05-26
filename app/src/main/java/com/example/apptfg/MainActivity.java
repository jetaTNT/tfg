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
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_main);
        GestorDePuntos.init(this);
        RecyclerView rv = findViewById(R.id.rvLibros);
        rv.setLayoutManager(new LinearLayoutManager(this));
        List<Libro> libs = LibroRepository.obtenerLibros();
        rv.setAdapter(new LibrosAdapter(libs, l -> {
            GestorDePuntos.reset();
            Intent i = new Intent(this, LectorActivity.class);
            i.putExtra("libroId", l.getId());
            startActivity(i);
        }));
    }
}