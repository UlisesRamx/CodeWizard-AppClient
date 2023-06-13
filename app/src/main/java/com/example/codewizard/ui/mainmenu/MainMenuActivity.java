package com.example.codewizard.ui.mainmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.example.codewizard.R;
import com.example.codewizard.api.ApiClient;
import com.example.codewizard.api.ApiResponse;
import com.example.codewizard.api.HttpMethod;
import com.example.codewizard.api.model.Usuario;
import com.example.codewizard.api.services.AuthService;
import com.example.codewizard.singleton.CurrentUser;

import java.io.IOException;

public class MainMenuActivity extends AppCompatActivity {

    private TextView textViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        textViewResultado = findViewById(R.id.textView_powerful);
        ApiResponse apiResponse = AuthService.login(new Usuario("Panther","123456"));

        CurrentUser.getInstance().setToken(apiResponse.getToken());

        textViewResultado.setText(" CurrentUser: " + CurrentUser.getInstance().getToken());
    }

}