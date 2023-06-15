package com.example.codewizard.ui.mainmenu;

import static android.widget.CompoundButton.*;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codewizard.api.ApiResponse;
import com.example.codewizard.api.services.BookService;
import com.example.codewizard.api.services.UserService;
import com.example.codewizard.databinding.ActivityMainMenuBinding;

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
        RecyclerView recyclerView = activityMainMenuBinding.rvBooksUsers;
        Switch switchBookUser = activityMainMenuBinding.toggleBookUsers;

        bookUserAdapter = new BookUserAdapter(this);
        ApiResponse apiResponse = BookService.allBooks();
        System.out.println("HEY: "+apiResponse.getLibros());
        bookUserAdapter.setItems(apiResponse.getLibros());
        recyclerView.setAdapter(bookUserAdapter);

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
        /*Intent intent = new Intent(MainMenuActivity.this, ProfileActivity.class);
        intent.putExtra("status", "Finished");
        startActivity(intent);
        */
    }

    private void searchItem(String query) {
        RecyclerView recyclerView = activityMainMenuBinding.rvBooksUsers;
        Switch switchBookUser = activityMainMenuBinding.toggleBookUsers;
        if (switchBookUser.isChecked()) {//Usuarios
            ApiResponse apiResponse = UserService.findUser(query);
            bookUserAdapter.setItems(apiResponse.getUsuarios());
        } else {//Libros
            ApiResponse apiResponse = BookService.findbook(query);
            bookUserAdapter.setItems(apiResponse.getLibros());
        }
        recyclerView.setAdapter(bookUserAdapter);

    }

    private void filterResults(String newText) {
        // Lógica para filtrar los resultados en tiempo real a medida que se ingresa texto
        // Puedes filtrar una lista de elementos, por ejemplo
    }
}