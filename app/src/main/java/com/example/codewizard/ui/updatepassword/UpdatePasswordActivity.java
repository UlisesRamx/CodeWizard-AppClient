package com.example.codewizard.ui.updatepassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.codewizard.R;
import com.example.codewizard.api.ApiResponse;
import com.example.codewizard.api.services.UserService;
import com.example.codewizard.singleton.CurrentUser;
import com.example.codewizard.ui.mainmenu.MainMenuActivity;
import com.example.codewizard.segurity.*;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.Security;

public class UpdatePasswordActivity extends AppCompatActivity {

    private EditText editTextContraseñaActual;
    private EditText editTextContraseñaNueva;
    private Button buttonActualizarContraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        editTextContraseñaActual = findViewById(R.id.editTextContraseñaActual);
        editTextContraseñaNueva = findViewById(R.id.editTextContraseñaNueva);
        buttonActualizarContraseña = findViewById(R.id.buttonActualizarContraseña);

        // Agregar un TextWatcher a los campos de contraseña para verificar su longitud
        editTextContraseñaActual.addTextChangedListener(passwordTextWatcher);
        editTextContraseñaNueva.addTextChangedListener(passwordTextWatcher);

        // Deshabilitar el botón de "Actualizar contraseña" inicialmente
        buttonActualizarContraseña.setEnabled(false);
        buttonActualizarContraseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarContraseña();
            }
        });
    }

    private TextWatcher passwordTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            String contraseñaActual = editTextContraseñaActual.getText().toString().trim();
            String contraseñaNueva = editTextContraseñaNueva.getText().toString().trim();

            // Verificar si ambos campos de contraseña están llenos y si la contraseña nueva tiene al menos 8 caracteres
            if (!contraseñaActual.isEmpty() && !contraseñaNueva.isEmpty() && contraseñaNueva.length() >= 8) {
                buttonActualizarContraseña.setEnabled(true);
            } else {
                buttonActualizarContraseña.setEnabled(false);
            }
        }
    };

    private void actualizarContraseña() {
        String contraseñaActual = PasswordUtils.generateSHA512Hash(editTextContraseñaActual.getText().toString().trim()) ;
        String contraseñaNueva = PasswordUtils.generateSHA512Hash(editTextContraseñaNueva.getText().toString().trim());


        ApiResponse apiResponse = UserService.updatePassword(CurrentUser.getInstance().getIdUsuario(),contraseñaActual,contraseñaNueva);

        if (!apiResponse.isError()) {
            String token = apiResponse.getToken();
            Toast.makeText(this, "Actualización exitosa", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
            startActivity(intent);

        } else {
            String errorMessage = apiResponse.getMessage();
            Toast.makeText(getApplicationContext(), "Error en la conexión del sistema", Toast.LENGTH_SHORT).show();


        }

        // Aquí puedes implementar la lógica para actualizar la contraseña
        // Por ejemplo, puedes realizar una llamada a una API o ejecutar una consulta en la base de datos

        // Mostrar un mensaje de éxito
        Toast.makeText(this, "Contraseña actualizada", Toast.LENGTH_SHORT).show();

        // Restablecer los campos de contraseña
        editTextContraseñaActual.setText("");
        editTextContraseñaNueva.setText("");

        // Deshabilitar el botón de "Actualizar contraseña" después de realizar la actualización
        buttonActualizarContraseña.setEnabled(false);
    }
}
