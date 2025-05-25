package com.example.apptfg;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import com.example.apptfg.modelos.*;

public class LibrosAdapter extends RecyclerView.Adapter<LibrosAdapter.LibroViewHolder> {

    public interface OnLibroClickListener { void onLibroClick(Libro libro); }

    private List<Libro> libros;
    private OnLibroClickListener listener;

    public LibrosAdapter(List<Libro> libros, OnLibroClickListener listener) {
        this.libros = libros;
        this.listener = listener;
    }

    @NonNull
    @Override
    public LibroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_libro, parent, false);
        return new LibroViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LibroViewHolder holder, int position) {
        Libro libro = libros.get(position);
        holder.tvTitulo.setText(libro.getTitulo());
        holder.tvAutor.setText(libro.getAutor());
        holder.itemView.setOnClickListener(v -> listener.onLibroClick(libro));
    }

    @Override
    public int getItemCount() { return libros.size(); }

    static class LibroViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo, tvAutor;
        LibroViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvAutor = itemView.findViewById(R.id.tvAutor);
        }
    }
}