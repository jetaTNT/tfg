package com.example.apptfg.ui.lector;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.apptfg.R;
import com.example.apptfg.data.model.Opcion;
import com.example.apptfg.data.model.Pagina;
import com.example.apptfg.ui.quiz.PreguntasActivity;

import java.util.List;

/**
 * Activity que muestra las páginas de un libro interactivo.
 * - Cada página puede tener varias opciones (buttons). Al pulsar, avanza a la siguiente página.
 * - Si la página no tiene opciones, se considera final y redirige a PreguntasActivity.
 */
public class LectorActivity extends AppCompatActivity {
    public static final String EXTRA_LIBRO_ID = "libro_id";

    private ReaderViewModel readerViewModel;
    private TextView tvTextoPagina;
    private LinearLayout opcionesContainer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lector);

        // 1) Recuperar el ID del libro que viene en el Intent
        int libroId = getIntent().getIntExtra(EXTRA_LIBRO_ID, -1);

        // 2) Configurar Toolbar
        toolbar = findViewById(R.id.toolbar_lector);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        // 3) Obtener referencias a las vistas
        tvTextoPagina = findViewById(R.id.tvTextoPagina);
        opcionesContainer = findViewById(R.id.opcionesContainer);

        // 4) Instanciar el ViewModel y cargar el libro
        readerViewModel = new ViewModelProvider(this).get(ReaderViewModel.class);
        readerViewModel.init(libroId, getApplicationContext());

        // 5) Observar LiveData<Pagina> para actualizar la UI al cambiar de página
        readerViewModel.getPaginaActual().observe(this, pagina -> {
            if (pagina != null) {
                mostrarPagina(pagina, libroId);
            }
        });
    }

    /**
     * Actualiza el contenido de la página en pantalla:
     * - Muestra el texto en tvTextoPagina.
     * - Si hay opciones, crea un Button para cada una.
     * - Si no hay opciones (página final), lanza PreguntasActivity.
     *
     * @param pagina   La página actual con su texto y lista de opciones.
     * @param libroId  ID del libro (se pasa a PreguntasActivity si es final).
     */
    private void mostrarPagina(Pagina pagina, int libroId) {
        // Mostrar el texto principal de la página
        tvTextoPagina.setText(pagina.getTexto());
        // Limpiar cualquier botón de opción previo
        opcionesContainer.removeAllViews();

        List<Opcion> opciones = pagina.getOpciones();
        if (opciones == null || opciones.isEmpty()) {
            // No hay opciones → página final → ir a las preguntas
            irAPreguntas(libroId);
        } else {
            // Por cada opción, generar un Button dinámicamente
            for (Opcion opcion : opciones) {
                Button btn = new Button(this);
                btn.setText(opcion.getTexto());
                btn.setTextSize(18f);
                btn.setAllCaps(false);
                // Fondo personalizado (asegúrate de tener R.drawable.drawable)
                btn.setBackgroundResource(R.drawable.drawable);
                // Color de texto (blanco)
                btn.setTextColor(getResources().getColor(R.color.white));

                // Al hacer clic, avanza a la siguiente página indicada por la opción
                btn.setOnClickListener(v ->
                        readerViewModel.irASiguientePagina(opcion.getSiguientePaginaId())
                );

                // Añadir márgenes (16dp arriba) para separar botones
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 16, 0, 0);
                opcionesContainer.addView(btn, params);
            }
        }
    }

    /**
     * Lanza PreguntasActivity pasándole el ID del libro (EXTRA_LIBRO_ID).
     * Termina esta Activity para que, al volver, no quede en la pila de atrás.
     *
     * @param libroId  ID del libro que acaba de terminar la historia.
     */
    private void irAPreguntas(int libroId) {
        Intent intent = new Intent(this, PreguntasActivity.class);
        intent.putExtra(PreguntasActivity.EXTRA_LIBRO_ID, libroId);
        startActivity(intent);
        finish();
    }
}
