package com.example.codewizard.ui.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.codewizard.R;
import com.example.codewizard.api.model.Usuario;
import com.example.codewizard.api.ApiResponse;
import com.example.codewizard.api.services.AuthService;
import com.example.codewizard.ui.mainmenu.MainMenuActivity;
import com.example.codewizard.segurity.*;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Security;


public class SignUpActivity extends AppCompatActivity {

    private EditText editTextNombre;
    private EditText editTextApellidoPaterno;
    private EditText editTextApellidoMaterno;
    private EditText editTextCorreo;
    private EditText editTextNombreUsuario;
    private EditText editTextContrasena;
    private EditText editTextConfirmarContrasena;
    private Button buttonRegistrar;

    private TextView textViewErrorNombre;
    private TextView textViewErrorApellidoPaterno;
    private TextView textViewErrorApellidoMaterno;
    private TextView textViewErrorCorreo;
    private TextView textViewErrorNombreUsuario;
    private TextView textViewErrorContrasena;
    private TextView textViewErrorConfirmarContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        editTextNombre = findViewById(R.id.editTextNombre);
        editTextApellidoPaterno = findViewById(R.id.editTextApellidoPaterno);
        editTextApellidoMaterno = findViewById(R.id.editTextApellidoMaterno);
        editTextCorreo = findViewById(R.id.editTextCorreo);
        editTextNombreUsuario = findViewById(R.id.editTextNombreUsuario);
        editTextContrasena = findViewById(R.id.editTextContrasena);
        editTextConfirmarContrasena = findViewById(R.id.editTextConfirmarContrasena);
        buttonRegistrar = findViewById(R.id.buttonRegistrar);

        textViewErrorNombre = findViewById(R.id.textViewErrorNombre);
        textViewErrorApellidoPaterno = findViewById(R.id.textViewErrorApellidoPaterno);
        textViewErrorApellidoMaterno = findViewById(R.id.textViewErrorApellidoMaterno);
        textViewErrorCorreo = findViewById(R.id.textViewErrorCorreo);
        textViewErrorNombreUsuario = findViewById(R.id.textViewErrorNombreUsuario);
        textViewErrorContrasena = findViewById(R.id.textViewErrorContrasena);
        textViewErrorConfirmarContrasena = findViewById(R.id.textViewErrorConfirmarContrasena);

        // Agregar listeners a los campos de texto
        editTextNombre.addTextChangedListener(new FormFieldTextWatcher());
        editTextApellidoPaterno.addTextChangedListener(new FormFieldTextWatcher());
        editTextApellidoMaterno.addTextChangedListener(new FormFieldTextWatcher());
        editTextCorreo.addTextChangedListener(new FormFieldTextWatcher());
        editTextNombreUsuario.addTextChangedListener(new FormFieldTextWatcher());
        editTextContrasena.addTextChangedListener(new FormFieldTextWatcher());
        editTextConfirmarContrasena.addTextChangedListener(new FormFieldTextWatcher());




        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              signUp();
            }
        });
    }

    private void signUp() {
        String nombre = editTextNombre.getText().toString().trim();
        String apellidoPaterno = editTextApellidoPaterno.getText().toString().trim();
        String apellidoMaterno = editTextApellidoMaterno.getText().toString().trim();
        String correo = editTextCorreo.getText().toString().trim();
        String nombreUsuario = editTextNombreUsuario.getText().toString().trim();
        String contrasena = editTextContrasena.getText().toString().trim();
        String confirmarContrasena = editTextConfirmarContrasena.getText().toString().trim();

        // Realizar validaciones
        boolean isValid = true;

        if (nombre.isEmpty() || !isValidName(nombre)) {
            textViewErrorNombre.setText("Datos inválidos");
            isValid = false;
        } else {
            textViewErrorNombre.setText("");
        }

        if (apellidoPaterno.isEmpty() || !isValidName(apellidoPaterno)) {
            textViewErrorApellidoPaterno.setText("Datos inválidos");
            isValid = false;
        } else {
            textViewErrorApellidoPaterno.setText("");
        }

        if (apellidoMaterno.isEmpty() || !isValidName(apellidoMaterno)) {
            textViewErrorApellidoMaterno.setText("Datos inválidos");
            isValid = false;
        } else {
            textViewErrorApellidoMaterno.setText("");
        }

        if (correo.isEmpty() || !isValidEmail(correo)) {
            textViewErrorCorreo.setText("Datos inválidos");
            isValid = false;
        } else {
            textViewErrorCorreo.setText("");
        }

        if (nombreUsuario.isEmpty() || !isValidUsername(nombreUsuario)) {
            textViewErrorNombreUsuario.setText("Datos inválidos");
            Toast.makeText(getApplicationContext(), "Campos Vacios", Toast.LENGTH_SHORT).show();

            isValid = false;
        } else {
            textViewErrorNombreUsuario.setText("");
        }

        if (contrasena.isEmpty() || !isValidPassword(contrasena)) {
            textViewErrorContrasena.setText("Datos inválidos");
            isValid = false;
        } else {
            textViewErrorContrasena.setText("");
        }

        if (!confirmarContrasena.equals(contrasena)) {
            textViewErrorConfirmarContrasena.setText("Las contraseñas no coinciden");
            isValid = false;
        } else {
            textViewErrorConfirmarContrasena.setText("");
        }

        if (isValid) {

            Usuario usuario = new Usuario(nombreUsuario,nombre, apellidoPaterno,apellidoMaterno,correo, PasswordUtils.generateSHA512Hash(contrasena),1,0,0);
            ApiResponse apiResponse = AuthService.signUp(usuario);

            if (!apiResponse.isError()) {
                String token = apiResponse.getToken();
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                startActivity(intent);

            } else {
                String errorMessage = apiResponse.getMessage();
                Toast.makeText(getApplicationContext(), "Credenciales incorrectas", Toast.LENGTH_SHORT).show();


            }
        }


         else {
            Toast.makeText(this, "Por favor, corrige los datos inválidos", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidName(String name) {
        String regex = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$";
        return name.matches(regex) && name.length() <= 30;
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.length() <= 50;
    }

    private boolean isValidUsername(String username) {
        String regex = "^[a-zA-Z0-9]+$";
        return username.matches(regex) && username.length() <= 15 && !username.contains(" ");
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8;
    }



    private class FormFieldTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            buttonRegistrar.setEnabled(isValidForm());

        }


    }

    private boolean isValidForm() {
        String nombre = editTextNombre.getText().toString().trim();
        String apellidoPaterno = editTextApellidoPaterno.getText().toString().trim();
        String apellidoMaterno = editTextApellidoMaterno.getText().toString().trim();
        String correo = editTextCorreo.getText().toString().trim();
        String nombreUsuario = editTextNombreUsuario.getText().toString().trim();
        String contrasena = editTextContrasena.getText().toString().trim();
        String confirmarContrasena = editTextConfirmarContrasena.getText().toString().trim();

        return isValidName(nombre) && isValidName(apellidoPaterno) && isValidName(apellidoMaterno)
                && isValidEmail(correo) && isValidUsername(nombreUsuario)
                && isValidPassword(contrasena) && confirmarContrasena.equals(contrasena);
    }
}