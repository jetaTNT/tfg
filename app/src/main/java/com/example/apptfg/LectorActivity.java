package com.example.apptfg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.apptfg.modelos.Libro;
import com.example.apptfg.modelos.Pagina;
import com.example.apptfg.modelos.Opcion;
import com.example.apptfg.util.LectorDeLibros;

public class LectorActivity extends AppCompatActivity {
    private Libro libro;
    private String nombreLibro;
    private int paginaActual;

    @Override protected void onCreate(Bundle b) {
        super.onCreate(b);
        nombreLibro = getIntent().getStringExtra("libro");
        libro = LectorDeLibros.cargar(this, nombreLibro);
        paginaActual = 0;
        mostrarPagina();
    }
    private void mostrarPagina() {
        Pagina p = libro.getPaginas().get(paginaActual);
        ScrollView sv = new ScrollView(this);
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setPadding(40,40,40,40);
        TextView tv = new TextView(this);
        tv.setText(p.getTexto()); tv.setTextSize(18);
        ll.addView(tv);
        for (Opcion op : p.getOpciones()) {
            Button b = new Button(this); b.setText(op.getTexto());
            b.setOnClickListener(v -> {
                paginaActual = op.getSiguiente();
                if (paginaActual>=libro.getPaginas().size()) {
                    Intent i=new Intent(this,PreguntasActivity.class);
                    i.putExtra("libro",nombreLibro);
                    startActivity(i); finish();
                } else mostrarPagina();
            }); ll.addView(b);
        }
        sv.addView(ll); setContentView(sv);
    }
}