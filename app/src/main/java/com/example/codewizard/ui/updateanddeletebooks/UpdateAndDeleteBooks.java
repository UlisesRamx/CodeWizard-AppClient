package com.example.codewizard.ui.updateanddeletebooks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.codewizard.R;
import com.example.codewizard.api.ApiResponse;
import com.example.codewizard.api.model.Autor;
import com.example.codewizard.api.model.Editorial;
import com.example.codewizard.api.model.Libro;
import com.example.codewizard.api.services.BookService;
import com.example.codewizard.ui.registerbook.RegisterBook;

import java.util.List;

public class UpdateAndDeleteBooks extends AppCompatActivity {

    private Spinner spinnerBooks;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete_books);
        spinnerBooks = findViewById(R.id.spinnerAllBooks);
        loadData();


    }

    private void loadData(){
        ApiResponse apiResponseBooks = BookService.allBooks();

        if(!apiResponseBooks.isError()){
            List<Libro> listLibros = apiResponseBooks.getLibros();
            ArrayAdapter<Libro> adapterLibros = new ArrayAdapter<>(UpdateAndDeleteBooks.this, android.R.layout.simple_spinner_item, listLibros);
            adapterLibros.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerBooks = findViewById(R.id.spinnerAllBooks);
            spinnerBooks.setAdapter(adapterLibros);

            /*spinnerBooks.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                    Libro selectedLibro = (Libro) adapterView.getItemAtPosition(position);
                    // Realizar acciones con el objeto Libro seleccionado
                    Toast.makeText(getApplicationContext(), "Libro seleccionado: " + selectedLibro.getTitulo(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    // Manejar el caso cuando no se seleccione ningún elemento
                }
            });*/


        }else {
            Toast.makeText(getApplicationContext(), "Error intentelo mas tarde", Toast.LENGTH_SHORT).show();
        }
    }
}