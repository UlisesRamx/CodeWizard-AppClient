package com.example.codewizard.ui.resenias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.codewizard.R;
import com.example.codewizard.api.ApiResponse;
import com.example.codewizard.api.model.Usuario;
import com.example.codewizard.api.services.ReviewService;
import com.example.codewizard.databinding.ActivityMainMenuBinding;
import com.example.codewizard.databinding.ActivityReseniaBinding;
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

        loadView(1);
    }

    private void loadView(int idLibro){
        reseniaAdapter = new ReseniaAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.rvReviews);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(reseniaAdapter);

        ApiResponse apiResponse = ReviewService.bookReviews(idLibro);
        reseniaAdapter.setItems(apiResponse.getResenias());
    }
}