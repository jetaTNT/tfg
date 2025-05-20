package com.example.apptfg;

import android.content.Context;
import com.example.apptfg.modelos.Libro;
import com.example.apptfg.util.LectorDeLibros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LibroRepository {
    public List<Libro> obtenerListaAssets(Context c) {
        try {
            String[] archivos = c.getAssets().list("");
            List<Libro> lista = new ArrayList<>();
            for (String a : archivos) {
                if (a.endsWith(".json")) {
                    Libro l = LectorDeLibros.cargar(c, a);
                    if (l != null) {
                        l.setArchivo(a);
                        lista.add(l);
                    }
                }
            }
            return lista;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
