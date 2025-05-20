package com.example.apptfg;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.apptfg.modelos.Libro;
import com.example.apptfg.modelos.Pregunta;
import com.example.apptfg.util.GestorDePuntos;
import com.example.apptfg.util.LectorDeLibros;

public class PreguntasActivity extends AppCompatActivity {
    private Libro libro;
    private int indice,puntos;

    @Override protected void onCreate(Bundle b) {
        super.onCreate(b);
        String nombre=getIntent().getStringExtra("libro");
        libro=LectorDeLibros.cargar(this,nombre);
        indice=0;puntos=0;
        mostrarPregunta();
    }
    private void mostrarPregunta() {
        Pregunta q=libro.getPreguntas().get(indice);
        LinearLayout ll=new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setPadding(40,40,40,40);
        TextView tv=new TextView(this);
        tv.setText(q.getPregunta()); tv.setTextSize(18);
        ll.addView(tv);
        for (int i=0;i<q.getRespuestas().size();i++){
            String r=q.getRespuestas().get(i);
            int fi=i;
            Button b=new Button(this);b.setText(r);
            b.setOnClickListener(v->{
                if(fi==q.getCorrecta()) puntos++;
                indice++;
                if(indice>=libro.getPreguntas().size()){
                    GestorDePuntos.sumarPuntos(this,puntos);
                    Toast.makeText(this,"¡Ganaste "+puntos+" puntos!",Toast.LENGTH_LONG).show();
                    finish();
                }else mostrarPregunta();
            }); ll.addView(b);
        }
        setContentView(ll);
    }
}