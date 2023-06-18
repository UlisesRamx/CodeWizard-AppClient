package com.example.codewizard.ui.makereview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.codewizard.R;
import com.example.codewizard.api.ApiResponse;
import com.example.codewizard.api.model.Resenia;
import com.example.codewizard.api.model.Usuario;
import com.example.codewizard.api.services.ReviewService;
import com.example.codewizard.singleton.CurrentUser;

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


        buttonSetReview.setOnClickListener(view -> {
            if (editTextReview.getText().toString().isEmpty() || !editTextReview.getText().toString().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
                Toast.makeText(getApplicationContext(), "Campos inválidos", Toast.LENGTH_SHORT).show();
            } else {
                setReview();
            }
        });
    }

    private void setReview(){
        float rating = ratingBarScore.getRating();
        int ratingInt = Math.round(rating);

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(CurrentUser.getInstance().getIdUsuario());

        Resenia resenia = new Resenia();
        resenia.setDescripcion(editTextReview.getText().toString());
        resenia.setValoracion(ratingInt);
        resenia.setIdResenia(2);
        resenia.setActiva(0);
        resenia.setIdLibro(2);
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