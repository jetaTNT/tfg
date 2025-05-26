package com.example.apptfg;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.apptfg.modelos.Libro;
import java.util.List;

public class LibrosAdapter extends RecyclerView.Adapter<LibrosAdapter.LibroViewHolder> {
    public interface OnLibroClickListener { void onLibroClick(Libro libro); }
    private List<Libro> libros;
    private OnLibroClickListener listener;
    public LibrosAdapter(List<Libro> libros, OnLibroClickListener l) { this.libros = libros; this.listener = l; }

    @NonNull
    @Override
    public LibroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_libro, parent, false);
        return new LibroViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LibroViewHolder holder, int position) {
        Libro lib = libros.get(position);
        holder.tvTitulo.setText(lib.getTitulo());
        holder.itemView.setOnClickListener(v -> listener.onLibroClick(lib));
    }

    @Override public int getItemCount() { return libros.size(); }
    static class LibroViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo;
        public LibroViewHolder(@NonNull View iv) {
            super(iv);
            tvTitulo = iv.findViewById(R.id.tvTitulo);
        }
    }
}