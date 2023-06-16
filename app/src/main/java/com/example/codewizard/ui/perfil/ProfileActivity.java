package com.example.codewizard.ui.perfil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.codewizard.R;
import com.example.codewizard.api.ApiResponse;
import com.example.codewizard.api.model.Usuario;
import com.example.codewizard.api.services.UserService;
import com.example.codewizard.databinding.ActivityMainMenuBinding;
import com.example.codewizard.databinding.ActivityProfileBinding;
import com.example.codewizard.singleton.CurrentUser;
import com.example.codewizard.ui.login.LoginActivity;
import com.example.codewizard.ui.mainmenu.MainMenuActivity;

public class ProfileActivity extends AppCompatActivity {

    //private TextView tvUsername = findViewById(R.id.tvUsername);
    private ActivityProfileBinding activityProfileBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_profile);
        activityProfileBinding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(activityProfileBinding.getRoot());

        //Recuperar los datos pasados desde la Activity anterior
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        //Hacer algo con los datos recibidos
        loadView(username);
    }

    private void loadView(String username) {
        ApiResponse apiResponse = UserService.userProfile(username);
        activityProfileBinding.tvUsername.setText(apiResponse.getUsuario().getUsername());
        /**
         * //Esto va en el activity de la biblioteca en el onCreate para saber en que pestaña debe empezar
         * //Recuperar los datos pasados desde la Activity anterior
         * Intent intent = getIntent();
         * String valor = intent.getStringExtra("status");
         * //Hacer algo con los datos recibidos
         */
        activityProfileBinding.btnFinished.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, MainMenuActivity.class);
            intent.putExtra("status", "Finished");
            startActivity(intent);
        });
        activityProfileBinding.btnReading.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, MainMenuActivity.class);
            intent.putExtra("status", "Finished");
            startActivity(intent);
        });
        activityProfileBinding.btnPending.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, MainMenuActivity.class);
            intent.putExtra("status", "Finished");
            startActivity(intent);
        });
    }
}