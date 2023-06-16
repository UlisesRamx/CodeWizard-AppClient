package com.example.codewizard.ui.loginApp;

import androidx.appcompat.app.AppCompatActivity;
import com.example.codewizard.segurity.*;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.codewizard.api.ApiResponse;
import com.example.codewizard.singleton.CurrentUser;
import com.example.codewizard.api.services.AuthService;


import com.example.codewizard.R;
import com.example.codewizard.api.model.Usuario;
import com.example.codewizard.ui.mainmenu.MainMenuActivity;
import com.example.codewizard.ui.passwordchange.PasswordChangeActivity;

import com.example.codewizard.ui.recoverpassword.RecoverPasswordActivity;
import com.example.codewizard.ui.signup.SignUpActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonRegister;
    private TextView textViewOlvideContrasena;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button  buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);
        textViewOlvideContrasena = findViewById(R.id.textViewOlvideContrasena);



        textViewOlvideContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RecoverPasswordActivity.class);
                startActivity(intent);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                if(!password.isEmpty() && !email.isEmpty()){
                    Usuario usuario = new Usuario(email, PasswordUtils.generateSHA512Hash(password));

                    ApiResponse apiResponse = AuthService.login(usuario);

                    if (!apiResponse.isError()) {
                        String token = apiResponse.getToken();
                        CurrentUser.getInstance().setToken(token);
                        CurrentUser.getInstance().setIdUsuario(apiResponse.getUser().getIdUsuario());
                        CurrentUser.getInstance().setEmail(apiResponse.getUser().getEmail());
                        CurrentUser.getInstance().setNombre(apiResponse.getUser().getNombre());
                        CurrentUser.getInstance().setApellidoMaterno(apiResponse.getUser().getApellidoMaterno());
                        CurrentUser.getInstance().setApellidoPaterno(apiResponse.getUser().getApellidoPaterno());
                        CurrentUser.getInstance().setUsername(apiResponse.getUser().getUsername());
                        CurrentUser.getInstance().setEliminado(apiResponse.getUser().getEliminado());
                        CurrentUser.getInstance().setPassword(apiResponse.getUser().getPassword());
                        CurrentUser.getInstance().setTipoUsuario(apiResponse.getUser().getTipoUsuario());

                        Intent intent = new Intent(getApplicationContext(), PasswordChangeActivity.class);
                        startActivity(intent);

                    } else {
                        String errorMessage = apiResponse.getMessage();
                        Toast.makeText(getApplicationContext(), "Credenciales incorrectas", Toast.LENGTH_SHORT).show();


                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Campos Vacios", Toast.LENGTH_SHORT).show();
                }

            }
        });

        buttonRegister.setOnClickListener(v-> {

            Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
            startActivity(intent);

        });



    }
}