package com.example.apptfg.data.model;
import android.os.Parcel;import android.os.Parcelable;
public class Opcion implements Parcelable {
    private final String texto; private final int siguientePaginaId;
    public Opcion(String texto, int siguientePaginaId) { this.texto = texto; this.siguientePaginaId = siguientePaginaId; }
    protected Opcion(Parcel in) { texto=in.readString(); siguientePaginaId=in.readInt(); }
    public static final Creator<Opcion> CREATOR = new Creator<Opcion>() {
        @Override public Opcion createFromParcel(Parcel in) { return new Opcion(in); }
        @Override public Opcion[] newArray(int size) { return new Opcion[size]; }
    };
    public String getTexto() { return texto; } public int getSiguientePaginaId() { return siguientePaginaId; }
    @Override public int describeContents() { return 0; }
    @Override public void writeToParcel(Parcel parcel, int flags) { parcel.writeString(texto); parcel.writeInt(siguientePaginaId); }
}