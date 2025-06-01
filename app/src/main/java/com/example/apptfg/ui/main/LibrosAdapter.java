package com.example.apptfg.ui.main;
import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.TextView;import androidx.annotation.NonNull;import androidx.recyclerview.widget.DiffUtil;import androidx.recyclerview.widget.ListAdapter;import androidx.recyclerview.widget.RecyclerView;import com.example.apptfg.R;import com.example.apptfg.data.model.Libro;import com.google.android.material.card.MaterialCardView;
public class LibrosAdapter extends ListAdapter<Libro, LibrosAdapter.LibroViewHolder> {
    public interface OnLibroClickListener { void onLibroClick(Libro libro); }
    private final OnLibroClickListener listener;
    public LibrosAdapter(OnLibroClickListener listener) { super(DIFF_CALLBACK); this.listener = listener; }
    private static final DiffUtil.ItemCallback<Libro> DIFF_CALLBACK = new DiffUtil.ItemCallback<Libro>() {
        @Override public boolean areItemsTheSame(@NonNull Libro oldItem, @NonNull Libro newItem) { return oldItem.getId() == newItem.getId(); }
        @Override public boolean areContentsTheSame(@NonNull Libro oldItem, @NonNull Libro newItem) { return oldItem.getTitulo().equals(newItem.getTitulo()); }
    };
    @NonNull @Override public LibroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_libro, parent, false);
        return new LibroViewHolder(view);
    }
    @Override public void onBindViewHolder(@NonNull LibroViewHolder holder, int position) {
        Libro libro = getItem(position); holder.bind(libro);
    }
    class LibroViewHolder extends RecyclerView.ViewHolder {
        private final MaterialCardView card; private final TextView tvTitulo;
        public LibroViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card_libro);
            tvTitulo = itemView.findViewById(R.id.tvTituloLibro);
        }
        void bind(Libro libro) {
            tvTitulo.setText(libro.getTitulo());
            card.setOnClickListener(v -> listener.onLibroClick(libro));
        }
    }
}