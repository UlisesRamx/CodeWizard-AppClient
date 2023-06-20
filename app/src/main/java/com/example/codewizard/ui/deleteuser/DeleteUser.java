package com.example.codewizard.ui.deleteuser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codewizard.R;
import com.example.codewizard.api.ApiResponse;
import com.example.codewizard.api.model.Autor;
import com.example.codewizard.api.model.Editorial;
import com.example.codewizard.api.model.Libro;
import com.example.codewizard.api.model.Usuario;
import com.example.codewizard.api.services.BookService;
import com.example.codewizard.api.services.UserService;
import com.example.codewizard.databinding.ActivityMainMenuBinding;
import com.example.codewizard.ui.updateanddeletebooks.UpdateAndDeleteBooks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeleteUser extends AppCompatActivity {
    private Button buttonDeleteUser;
    private Spinner spinnerUser;
    private Usuario userSelected;
    private TextView textViewNombreCompleto;
    private TextView textViewUsername;
    private TextView textViewTotalReportes;
    private TextView textViewCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        spinnerUser = findViewById(R.id.spinnerUsuario);
        buttonDeleteUser = findViewById(R.id.buttonDeleteUser);
        textViewNombreCompleto = findViewById(R.id.textViewNombre);
        textViewUsername = findViewById(R.id.textViewUsername);
        textViewTotalReportes = findViewById(R.id.textViewTotalReportes);
        textViewCorreo = findViewById(R.id.textViewCorreo);
        loadData();

        buttonDeleteUser.setOnClickListener(view -> {
            if(userSelected!=null){
                deleteUser();
            }
        });

        spinnerUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userSelected = (Usuario) parent.getItemAtPosition(position);
                updateFields(userSelected);
            }

            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Seleccione un usuario", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateFields(Usuario userSelected){
        if(userSelected!=null){
            textViewNombreCompleto.setText(userSelected.getNombre() + " " + userSelected.getApellidoPaterno() + " " + userSelected.getApellidoMaterno());
            textViewUsername.setText(userSelected.getUsername());
            textViewTotalReportes.setText(String.valueOf(userSelected.getTotalReportes()));
            textViewCorreo.setText(userSelected.getEmail());
        }
    }

    private void deleteUser(){
        ApiResponse apiResponse = UserService.deleteProfile(userSelected);
        if (!apiResponse.isError()){
            Toast.makeText(getApplicationContext(), "Accion realizada con éxito", Toast.LENGTH_SHORT).show();
            ArrayAdapter<Usuario> adapterUserToDelete = (ArrayAdapter<Usuario>) spinnerUser.getAdapter();
            if (adapterUserToDelete != null) {
                for (int i = 0; i < adapterUserToDelete.getCount(); i++) {
                    Usuario usuario = adapterUserToDelete.getItem(i);
                    if (usuario != null && usuario.equals(usuario)) {
                        adapterUserToDelete.remove(usuario);
                        adapterUserToDelete.notifyDataSetChanged();
                        break;
                    }
                }
            }
        }else{
            Toast.makeText(getApplicationContext(), "Error, inténtelo más tarde", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadData() {
        ApiResponse apiResponseUsuarios = UserService.getAllUsers();
        if (!apiResponseUsuarios.isError()) {
            List<Usuario> listUsuario = apiResponseUsuarios.getUsuarios();
            ArrayAdapter<Usuario> adapterUsuario = new ArrayAdapter<>(DeleteUser.this, android.R.layout.simple_spinner_item, listUsuario);
            adapterUsuario.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerUser.setAdapter(adapterUsuario);
        } else {
            Toast.makeText(getApplicationContext(), "Error, inténtelo más tarde", Toast.LENGTH_SHORT).show();
        }
    }
}