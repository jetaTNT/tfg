package com.example.apptfg;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.apptfg.modelos.Libro;
import java.util.List;

public class LibrosAdapter extends RecyclerView.Adapter<LibrosAdapter.VH> {
    public interface OnClickListener { void onClick(Libro libro); }
    private List<Libro> lista;
    private OnClickListener listener;

    public LibrosAdapter(List<Libro> lista, OnClickListener listener) {
        this.lista = lista;
        this.listener = listener;
    }

    public static class VH extends RecyclerView.ViewHolder {
        ImageView portada;
        TextView titulo, desc;
        public VH(View item) {
            super(item);
            portada = item.findViewById(R.id.iv_portada);
            titulo = item.findViewById(R.id.tv_titulo);
            desc = item.findViewById(R.id.tv_descripcion);
        }
    }

    @Override public VH onCreateViewHolder(ViewGroup p, int vt) {
        View v = LayoutInflater.from(p.getContext())
                .inflate(R.layout.item_libro, p, false);
        return new VH(v);
    }
    @Override public void onBindViewHolder(VH h, int pos) {
        Libro l = lista.get(pos);
        h.titulo.setText(l.getTitulo());
        h.desc.setText(l.getDescripcion());
        h.portada.setImageResource(R.drawable.ic_launcher_background);
        h.itemView.setOnClickListener(v -> listener.onClick(l));
    }
    @Override public int getItemCount() { return lista.size(); }
}
