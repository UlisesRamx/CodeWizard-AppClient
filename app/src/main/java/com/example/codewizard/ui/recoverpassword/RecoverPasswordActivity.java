package com.example.codewizard.ui.recoverpassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.codewizard.R;
import com.example.codewizard.api.ApiResponse;
import com.example.codewizard.api.services.UserService;
import com.example.codewizard.singleton.CurrentUser;
import com.example.codewizard.ui.newpassword.NewPasswordActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RecoverPasswordActivity extends AppCompatActivity {

    private EditText editTextCorreo;
    private Button buttonEnviarCodigo;
    private EditText editTextCodigo;
    private Button buttonVerificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_password);

        editTextCorreo = findViewById(R.id.editTextCorreo);
        buttonEnviarCodigo = findViewById(R.id.buttonEnviarCodigo);
        editTextCodigo = findViewById(R.id.editTextCodigo);
        buttonVerificar = findViewById(R.id.buttonVerificar);

        buttonEnviarCodigo.setEnabled(false);
        editTextCodigo.setEnabled(false);
        buttonVerificar.setEnabled(false);

        editTextCorreo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                boolean isEmailValid = isValidEmail(s.toString());

                buttonEnviarCodigo.setEnabled(isEmailValid);
            }
        });

        // Agregar listener al botón de enviar código
        buttonEnviarCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextCodigo.setEnabled(true);
                buttonVerificar.setEnabled(true);

                editTextCorreo.setEnabled(false);
                buttonEnviarCodigo.setEnabled(false);


                ApiResponse apiResponse = UserService.sendOtp(CurrentUser.getInstance().getEmail(),CurrentUser.getInstance().getUsername());

                if (!apiResponse.isError()) {
                    String token = apiResponse.getToken();
                    Toast.makeText(getApplicationContext(), "Se ha enviado el código", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), NewPasswordActivity.class);
                    startActivity(intent);

                } else {
                    String errorMessage = apiResponse.getMessage();
                    Toast.makeText(getApplicationContext(), "Error en la conexión del sistema", Toast.LENGTH_SHORT).show();


                }
            }
        });
    }

    private boolean isValidEmail(String email) {

        return email.contains("@");
    }
}
