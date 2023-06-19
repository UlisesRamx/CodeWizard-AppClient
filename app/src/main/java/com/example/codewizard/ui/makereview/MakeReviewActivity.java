package com.example.codewizard.ui.makereview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.codewizard.R;
import com.example.codewizard.api.ApiResponse;
import com.example.codewizard.api.model.Libro;
import com.example.codewizard.api.model.Resenia;
import com.example.codewizard.api.model.Usuario;
import com.example.codewizard.api.services.ReviewService;
import com.example.codewizard.singleton.CurrentUser;
import com.google.gson.Gson;

public class MakeReviewActivity extends AppCompatActivity {
    private EditText editTextReview;
    private RatingBar ratingBarScore;
    private Button buttonSetReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_review);

        editTextReview = findViewById(R.id.book_comment);
        ratingBarScore = findViewById(R.id.rating_book);
        buttonSetReview = findViewById(R.id.save_button_review);

        Intent intent = getIntent();
        String libroJson = intent.getStringExtra("libro");
        Gson gson = new Gson();
        Libro libroRecibido = gson.fromJson(libroJson, Libro.class);

        buttonSetReview.setOnClickListener(view -> {
            if (editTextReview.getText().toString().isEmpty() || !editTextReview.getText().toString().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
                Toast.makeText(getApplicationContext(), "Campos inválidos", Toast.LENGTH_SHORT).show();
            } else {
                setReview(libroRecibido);
            }
        });
    }

    private void setReview(Libro libro){
        float rating = ratingBarScore.getRating();
        int ratingInt = Math.round(rating);

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(CurrentUser.getInstance().getIdUsuario());

        Resenia resenia = new Resenia();
        resenia.setDescripcion(editTextReview.getText().toString());
        resenia.setValoracion(ratingInt);
        resenia.setIdResenia(2);
        int id = CurrentUser.getInstance().getLibro().getIdLibro();
        resenia.setIdLibro(libro.getIdLibro());
        resenia.setIdUsuario(usuario.getIdUsuario());
        resenia.setUsuario(usuario);

        ApiResponse apiResponse = ReviewService.leaveReview(resenia);
        if(!apiResponse.isError()){
            Toast.makeText(getApplicationContext(), "Tu review se a publicado con exito", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(), "Error al publicar, vuelve a intentarlo", Toast.LENGTH_SHORT).show();
        }
    }


}