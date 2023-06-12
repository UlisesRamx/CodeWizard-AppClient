package com.example.codewizard.ui.mainmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.example.codewizard.R;
import com.example.codewizard.api.ApiClient;
import com.example.codewizard.api.HttpMethod;
import com.example.codewizard.api.model.Usuario;

import java.io.IOException;

public class MainMenuActivity extends AppCompatActivity {

    private TextView textViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        textViewResultado = findViewById(R.id.textView_powerful);

        ApiClient.sendRequest("auth/login", HttpMethod.POST, "Basic", "cGFudGhlcjoxMjM0NTY=", new Usuario("Panther","123456"))
                .thenAccept(apiResponse -> {
                    if (apiResponse != null && !apiResponse.isError()) {
                        // Procesar la respuesta exitosa de la API
                        // Puedes acceder a los datos de la respuesta a través de los métodos de la clase ApiResponse
                        // Ejemplo: String mensaje = apiResponse.getMessage();
                        textViewResultado.setText(apiResponse.getToken());
                    } else {
                        textViewResultado.setText(apiResponse.getMessage());
                        // Procesar el error de la API
                        // Puedes acceder al mensaje de error a través de apiResponse.getMessage()
                    }
                })
                .exceptionally(e -> {
                    // Procesar la excepción ocurrida durante la llamada a la API
                    // Ejemplo: e.printStackTrace();
                    e.printStackTrace();
                    textViewResultado.setText(e.getMessage());
                    return null;
                });

    }

}