package com.example.apptfg;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.apptfg.modelos.Libro;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = findViewById(R.id.recycler_libros);
        LibroRepository repo = new LibroRepository();
        List<Libro> lista = repo.obtenerListaAssets(this);
        LibrosAdapter adapter = new LibrosAdapter(lista, libro -> {
            Intent i = new Intent(MainActivity.this, LectorActivity.class);
            i.putExtra("libro", libro.getArchivo());
            startActivity(i);
        });
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }
}