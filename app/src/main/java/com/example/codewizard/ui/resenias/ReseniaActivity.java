package com.example.codewizard.ui.resenias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.codewizard.R;
import com.example.codewizard.api.ApiResponse;
import com.example.codewizard.api.model.Usuario;
import com.example.codewizard.api.services.ReviewService;
import com.example.codewizard.databinding.ActivityMainMenuBinding;
import com.example.codewizard.databinding.ActivityReseniaBinding;
import com.example.codewizard.singleton.CurrentUser;
import com.example.codewizard.ui.mainmenu.BookUserAdapter;
import com.google.gson.Gson;

public class ReseniaActivity extends AppCompatActivity {

    private ActivityReseniaBinding activityReseniaBinding;
    private ReseniaAdapter reseniaAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_resenia);
        activityReseniaBinding = ActivityReseniaBinding.inflate(getLayoutInflater());
        setContentView(activityReseniaBinding.getRoot());

        Intent intent = getIntent();
        Integer idLibro = intent.getIntExtra("idLibro",0);
        loadView(idLibro);
    }

    private void loadView(int idLibro){
        reseniaAdapter = new ReseniaAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.rvReviews);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(reseniaAdapter);

        ApiResponse apiResponse = new ApiResponse();

        if(CurrentUser.getInstance().getTipoUsuario() == 1){
            apiResponse = ReviewService.bookReviews(idLibro);
        } else if (CurrentUser.getInstance().getTipoUsuario() == 2) {
            apiResponse = ReviewService.reportedReviews();
            if(apiResponse.getResenias().isEmpty()){
                Toast.makeText(getApplicationContext(), "No hay reseñas", Toast.LENGTH_SHORT).show();
            }
        }
        reseniaAdapter.setItems(apiResponse.getResenias());
    }
}