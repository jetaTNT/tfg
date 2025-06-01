package com.example.apptfg.data.model;
import android.os.Parcel;import android.os.Parcelable;import java.util.Collections;import java.util.List;
public class Pagina implements Parcelable {
    private final int id; private final String texto; private final List<Opcion> opciones;
    public Pagina(int id, String texto, List<Opcion> opciones) { this.id=id; this.texto=texto; this.opciones = opciones!=null?opciones:Collections.emptyList(); }
    protected Pagina(Parcel in) { id=in.readInt(); texto=in.readString(); opciones=in.createTypedArrayList(Opcion.CREATOR); }
    public static final Creator<Pagina> CREATOR = new Creator<Pagina>() {
        @Override public Pagina createFromParcel(Parcel in) { return new Pagina(in); }
        @Override public Pagina[] newArray(int size) { return new Pagina[size]; }
    };
    public int getId() { return id; } public String getTexto() { return texto; } public List<Opcion> getOpciones() { return opciones; }
    public boolean esPaginaFinal() { return opciones.isEmpty(); }
    @Override public int describeContents() { return 0; }
    @Override public void writeToParcel(Parcel parcel, int flags) { parcel.writeInt(id); parcel.writeString(texto); parcel.writeTypedList(opciones); }
}