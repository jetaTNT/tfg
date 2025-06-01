package com.example.apptfg.ui.stats;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.widget.TextView;

import com.example.apptfg.R;
import com.example.apptfg.data.local.entities.Puntos;

/**
 * Activity que muestra las estadísticas del usuario:
 * - Puntos totales obtenidos almacenados en la base de datos.
 */
public class StatsActivity extends AppCompatActivity {

    private StatsViewModel statsViewModel;
    private TextView tvPuntosTotales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        // Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_stats);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        // Obtener referencia al TextView que muestra puntos totales
        tvPuntosTotales = findViewById(R.id.tvPuntosTotalesStats);

        // Instanciar ViewModel
        statsViewModel = new ViewModelProvider(this).get(StatsViewModel.class);

        // Observar LiveData<Puntos> para actualizar puntos totales
        statsViewModel.getPuntosTotales().observe(this, puntos -> {
            int cantidad = (puntos != null) ? puntos.getCantidad() : 0;
            // Usamos la cadena puntos_main para mostrar los puntos
            tvPuntosTotales.setText(
                    getString(R.string.puntos_main, cantidad)
            );
        });
    }
}
