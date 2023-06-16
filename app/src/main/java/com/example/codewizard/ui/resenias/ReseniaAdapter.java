package com.example.codewizard.ui.resenias;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codewizard.R;
import com.example.codewizard.api.ApiResponse;
import com.example.codewizard.api.model.Libro;
import com.example.codewizard.api.model.Resenia;
import com.example.codewizard.api.model.Usuario;
import com.example.codewizard.api.services.ReviewService;
import com.example.codewizard.singleton.CurrentUser;
import com.example.codewizard.ui.mainmenu.BookUserAdapter;
import com.example.codewizard.ui.perfil.ProfileActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ReseniaAdapter extends RecyclerView.Adapter<ReseniaAdapter.ViewHolder> {
    private Context context;
    private List<Resenia> filteredList;
    private List<Resenia> itemList;

    public ReseniaAdapter(Context context) {
        this.context = context;
        this.filteredList = new ArrayList<>();
        this.itemList = new ArrayList<>();
    }

    public void filter(int query) {
        filteredList.clear();

        if (query == 0) {
            filteredList.addAll(itemList);
        } else {
            for (Resenia item : itemList) {
                if (item.getIdUsuario() == CurrentUser.getInstance().getIdUsuario()) {
                    filteredList.add(item);
                }
            }
        }

        notifyDataSetChanged();
    }

    public void setItems(List<Resenia> items) {
        itemList = items;
        filteredList = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReseniaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Crear y retornar una instancia de ViewHolder
        // Inflar el layout de la fila del RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.resenia_item_list, parent, false);

        // Crear una instancia del ViewHolder y pasarle la vista inflada
        ReseniaAdapter.ViewHolder viewHolder = new ReseniaAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReseniaAdapter.ViewHolder holder, int position) {
        // Vincular los datos del elemento en la posición 'position' con los views del ViewHolder
        // Obtener el elemento en la posición 'position' de la lista filtrada
        Resenia item = filteredList.get(position);

        // Vincular los datos del elemento con los views del ViewHolder
        holder.textViewName.setText(item.getDescripcion());
        holder.textViewDescription.setText(item.getUsuario().getUsername());

        if(CurrentUser.getInstance().getTipoUsuario() == 1){
            if(item.getIdUsuario() == CurrentUser.getInstance().getIdUsuario()){
                holder.buttonReprotDelete.setText("Delete");
            }else{
                holder.buttonReprotDelete.setText("Report");
            }
        } else if (CurrentUser.getInstance().getTipoUsuario() == 2) {
            holder.buttonReprotDelete.setText("Delete");
        }

        holder.buttonReprotDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Resenia review = itemList.get(holder.getAdapterPosition());

                if(holder.buttonReprotDelete.getText().equals("Delete")){
                    ApiResponse apiResponse = ReviewService.deleteReview(review.getIdResenia());
                    if(apiResponse.getAffectedRows() > 0){
                        Toast.makeText(context.getApplicationContext(), "Reseña eliminada", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context.getApplicationContext(), "Reseña no eliminada", Toast.LENGTH_SHORT).show();
                    }
                } else if (holder.buttonReprotDelete.getText().equals("Report")) {
                    ApiResponse apiResponse = ReviewService.reportReview(CurrentUser.getInstance().getIdUsuario(), review.getIdResenia());
                    if(apiResponse.getAffectedRows() > 0){
                        Toast.makeText(context.getApplicationContext(), "Reseña reportada correctamente", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context.getApplicationContext(), "Reseña no reportada", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

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

        Button buttonReprotDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Inicializar los views del ViewHolder
            textViewName = itemView.findViewById(R.id.tvReviewerName);
            textViewDescription = itemView.findViewById(R.id.tvResenia);
            buttonReprotDelete = itemView.findViewById(R.id.btnReportDelete);
        }
    }
}
