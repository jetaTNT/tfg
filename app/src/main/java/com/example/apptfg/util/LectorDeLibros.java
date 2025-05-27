package com.example.apptfg.util;
import android.content.Context;
import com.example.apptfg.modelos.Libro;
import com.google.gson.Gson;
import java.io.InputStreamReader;
public class LectorDeLibros {
    public static Libro cargar(Context c,String a){
        try{InputStreamReader r=new InputStreamReader(c.getAssets().open(a)); return new Gson().fromJson(r,Libro.class);}catch(Exception e){e.printStackTrace();return null;}}
}