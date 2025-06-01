// Archivo: app/src/main/java/com/example/apptfg/ui/main/MainActivity.java
package com.example.apptfg.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.widget.TextView;

import com.example.apptfg.R;
import com.example.apptfg.data.model.Libro;
import com.example.apptfg.ui.lector.LectorActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LibrosAdapter.OnLibroClickListener {

    private MainViewModel viewModel;
    private LibrosAdapter adapter;
    private RecyclerView rvLibros;
    private TextView tvPuntos;
    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        // Referencias a vistas
        rvLibros = findViewById(R.id.rvLibros);
        tvPuntos = findViewById(R.id.tvPuntosMain);
        swipeRefresh = findViewById(R.id.swipeRefreshMain);

        // Configurar RecyclerView
        rvLibros.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LibrosAdapter(this);
        rvLibros.setAdapter(adapter);
        rvLibros.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        // Instanciar ViewModel
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // Observar la lista de libros
        viewModel.getLibros().observe(this, libros -> {
            if (libros != null) {
                adapter.submitList(libros);
                swipeRefresh.setRefreshing(false);
            }
        });

        // Observar puntos totales
        viewModel.getPuntosLive().observe(this, puntos -> {
            if (puntos != null) {
                tvPuntos.setText(getString(R.string.puntos_main, puntos.getCantidad()));
            } else {
                tvPuntos.setText(getString(R.string.puntos_main, 0));
            }
        });

        // Configurar SwipeRefresh para recargar la lista
        swipeRefresh.setOnRefreshListener(() ->
                viewModel.refrescarLibros(getApplicationContext())
        );
    }

    @Override
    public void onLibroClick(Libro libro) {
        // Al hacer clic en un libro, abrir LectorActivity con su ID
        Intent intent = new Intent(this, LectorActivity.class);
        intent.putExtra(LectorActivity.EXTRA_LIBRO_ID, libro.getId());
        startActivity(intent);
    }
}
