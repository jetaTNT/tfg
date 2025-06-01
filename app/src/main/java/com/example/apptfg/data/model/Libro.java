package com.example.apptfg.data.model;
import android.os.Parcel;import android.os.Parcelable;import java.util.List;
public class Libro implements Parcelable {
    private final int id; private final String titulo; private final List<Pagina> paginas;
    public Libro(int id, String titulo, List<Pagina> paginas) { this.id = id; this.titulo = titulo; this.paginas = paginas; }
    protected Libro(Parcel in) { id=in.readInt(); titulo=in.readString(); paginas=in.createTypedArrayList(Pagina.CREATOR); }
    public static final Creator<Libro> CREATOR = new Creator<Libro>() {
        @Override public Libro createFromParcel(Parcel in) { return new Libro(in); }
        @Override public Libro[] newArray(int size) { return new Libro[size]; }
    };
    public int getId() { return id; } public String getTitulo() { return titulo; } public List<Pagina> getPaginas() { return paginas; }
    @Override public int describeContents() { return 0; }
    @Override public void writeToParcel(Parcel parcel, int flags) { parcel.writeInt(id); parcel.writeString(titulo); parcel.writeTypedList(paginas); }
}