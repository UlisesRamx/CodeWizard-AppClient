package com.example.codewizard.ui.mainmenu;

import static android.widget.CompoundButton.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codewizard.R;
import com.example.codewizard.api.ApiResponse;
import com.example.codewizard.api.model.Usuario;
import com.example.codewizard.api.services.AuthService;
import com.example.codewizard.api.services.BookService;
import com.example.codewizard.api.services.ReviewService;
import com.example.codewizard.api.services.UserService;
import com.example.codewizard.databinding.ActivityMainMenuBinding;
import com.example.codewizard.singleton.CurrentUser;
import com.example.codewizard.ui.broadcast.BroadcastActivity;
import com.example.codewizard.ui.passwordchange.PasswordChangeActivity;
import com.example.codewizard.ui.perfil.ProfileActivity;
import com.example.codewizard.ui.resenias.ReseniaActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainMenuActivity extends AppCompatActivity {

    private ActivityMainMenuBinding activityMainMenuBinding;
    private BookUserAdapter bookUserAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main_menu);
        activityMainMenuBinding = ActivityMainMenuBinding.inflate(getLayoutInflater());
        setContentView(activityMainMenuBinding.getRoot());

        loadView();

    }

    private void loadView(){
        //RecyclerView recyclerView = activityMainMenuBinding.rvBooksUsers;
        Switch switchBookUser = activityMainMenuBinding.toggleBookUsers;
        //FloatingActionButton floatingActionButton = activityMainMenuBinding.fabReviews;

        bookUserAdapter = new BookUserAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.rvBooksUsers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(bookUserAdapter);
        //ApiResponse apiResponse = BookService.allBooks();
        //bookUserAdapter.setItems(apiResponse.getLibros());

        if(CurrentUser.getInstance().getTipoUsuario() == 1){
            activityMainMenuBinding.fabReviews.setVisibility(INVISIBLE);
            activityMainMenuBinding.fabEmail.setVisibility(INVISIBLE);
        } else if (CurrentUser.getInstance().getTipoUsuario() == 2) {
            activityMainMenuBinding.fabReviews.setVisibility(VISIBLE);
            activityMainMenuBinding.fabEmail.setVisibility(VISIBLE);
        }
        activityMainMenuBinding.updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, PasswordChangeActivity.class);//Para enviar broadcast

                startActivity(intent);
            }
        });
        activityMainMenuBinding.fabReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, ReseniaActivity.class);//Para enviar broadcast
                intent.putExtra("idLibro", 0);
                startActivity(intent);
            }
        });



        activityMainMenuBinding.fabEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, BroadcastActivity.class);//Para enviar a reseñas
                startActivity(intent);
            }
        });


        activityMainMenuBinding.toggleBookUsers.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switchBookUser.isChecked()) {
                    switchBookUser.setText("Usuarios");
                } else {
                    switchBookUser.setText("Libros");
                }
            }
        });
        activityMainMenuBinding.searchView.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Lógica para manejar la búsqueda cuando se presiona "Enter"
                searchItem(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Lógica para manejar la búsqueda en tiempo real a medida que el texto cambia
                filterResults(newText);
                return true;
            }
        });

    }

    private void searchItem(String query) {
        RecyclerView recyclerView = activityMainMenuBinding.rvBooksUsers;
        Switch switchBookUser = activityMainMenuBinding.toggleBookUsers;
        if (switchBookUser.isChecked()) {//Usuarios
            ApiResponse apiResponse = UserService.findUser(query);
            if (apiResponse.getUsuarios().size() > 0){
                bookUserAdapter.setItems(apiResponse.getUsuarios());
                //Toast.makeText(getApplicationContext(), "Jala: " + apiResponse.getUsuarios().size(), Toast.LENGTH_SHORT).show(); // Muestra un Toast con el mensaje de error
            }else{
                Toast.makeText(getApplicationContext(), "Error: " + apiResponse.getUsuarios().size(), Toast.LENGTH_SHORT).show(); // Muestra un Toast con el mensaje de error
            }

        } else {//Libros
            ApiResponse apiResponse = BookService.findbook(query);
            if (apiResponse.getLibros().size() > 0){
                bookUserAdapter.setItems(apiResponse.getLibros());
                //Toast.makeText(getApplicationContext(), "Jala: " + apiResponse.getUsuarios().size(), Toast.LENGTH_SHORT).show(); // Muestra un Toast con el mensaje de error
            }else{
                Toast.makeText(getApplicationContext(), "Error: " + apiResponse.getUsuarios().size(), Toast.LENGTH_SHORT).show(); // Muestra un Toast con el mensaje de error
            }
        }

    }

    private void filterResults(String newText) {
        // Lógica para filtrar los resultados en tiempo real a medida que se ingresa texto
        // Puedes filtrar una lista de elementos, por ejemplo
    }
}