package com.example.codewizard.ui.resenias;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.codewizard.R;
import com.example.codewizard.api.ApiResponse;
import com.example.codewizard.api.services.ReviewService;

public class ReseniaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resenia);
    }

    private void loadView(int idLibro){
        ApiResponse apiResponse = ReviewService.bookReviews(idLibro);
    }
}