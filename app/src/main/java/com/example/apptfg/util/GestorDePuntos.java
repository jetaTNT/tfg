package com.example.apptfg.util;
import android.content.Context;import android.content.SharedPreferences;
public class GestorDePuntos {
    public static void sumarPuntos(Context c,int p){SharedPreferences prefs=c.getSharedPreferences("puntos",Context.MODE_PRIVATE);int cur=prefs.getInt("total",0);prefs.edit().putInt("total",cur+p).apply();}
    public static int obtenerPuntos(Context c){return c.getSharedPreferences("puntos",Context.MODE_PRIVATE).getInt("total",0);}
}