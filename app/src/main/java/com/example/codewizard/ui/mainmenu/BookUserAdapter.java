package com.example.codewizard.ui.mainmenu;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codewizard.R;
import com.example.codewizard.api.model.Libro;
import com.example.codewizard.api.model.Usuario;

import java.net.CookieManager;
import java.util.ArrayList;
import java.util.List;

public class BookUserAdapter extends RecyclerView.Adapter<BookUserAdapter.ViewHolder> {
    private Context context;
    private List<Usuario> userItemList;
    private List<Usuario> userFilteredList;
    private List<Libro> bookItemList;
    private List<Libro> bookFilteredList;
    private List<?> filteredList;
    private List<?> itemList;

    public BookUserAdapter(Context context) {
        this.context = context;
        this.userItemList = new ArrayList<>();
        this.userFilteredList = new ArrayList<>();
        this.bookItemList = new ArrayList<>();
        this.bookFilteredList = new ArrayList<>();
        this.filteredList = new ArrayList<>();
        this.itemList = new ArrayList<>();
    }

    public void filterUser(String query) {
        userFilteredList.clear();

        if (query.isEmpty()) {
            userFilteredList.addAll(userItemList);
        } else {
            for (Usuario item : userItemList) {
                if (item.getUsername().toLowerCase().contains(query.toLowerCase())) {
                    userFilteredList.add(item);
                }
            }
        }
        filteredList = userFilteredList;

        notifyDataSetChanged();
    }

    public void filterBook(String query) {
        bookFilteredList.clear();

        if (query.isEmpty()) {
            bookFilteredList.addAll(bookItemList);
        } else {
            for (Libro item : bookItemList) {
                if (item.getTitulo().toLowerCase().contains(query.toLowerCase())) {
                    bookFilteredList.add(item);
                }
            }
        }
        filteredList = bookFilteredList;

        notifyDataSetChanged();
    }

    public void setItems(List<?> items) {
        if (items.get(0) instanceof Usuario) {
            userItemList = (List<Usuario>) items;
            userFilteredList.clear();
            userFilteredList.addAll(userItemList);
            filteredList = userFilteredList;
        } else if (items.get(0) instanceof Libro) {
            bookItemList = (List<Libro>) items;
            bookFilteredList.clear();
            bookFilteredList.addAll(bookItemList);
            filteredList = bookFilteredList;
        }
        itemList = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Crear y retornar una instancia de ViewHolder
        // Inflar el layout de la fila del RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item_list, parent, false);

        // Crear una instancia del ViewHolder y pasarle la vista inflada
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Vincular los datos del elemento en la posición 'position' con los views del ViewHolder
        // Obtener el elemento en la posición 'position' de la lista filtrada
        if(filteredList.get(position) instanceof Usuario){
            Usuario item = (Usuario) filteredList.get(position);
            holder.textViewName.setText(item.getUsername());
            holder.textViewDescription.setText("");
        }else{
            if (filteredList.get(position) instanceof Libro){
                Libro item = (Libro) filteredList.get(position);
                holder.textViewName.setText(item.getTitulo());
                holder.textViewDescription.setText(item.getAutores().get(0).getNombre());
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lógica para manejar el evento de clic en un elemento del RecyclerView
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Declarar los views del elemento en el layout de cada fila
        // Ejemplo: TextView, ImageView, etc.

        TextView textViewName;
        TextView textViewDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Inicializar los views del ViewHolder
            textViewName = itemView.findViewById(R.id.tvName);
            textViewDescription = itemView.findViewById(R.id.tvAutor);
        }
    }
}