package com.example.codewizard.ui.registerbook;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.codewizard.R;
import com.example.codewizard.api.ApiResponse;
import com.example.codewizard.api.model.Autor;
import com.example.codewizard.api.model.Editorial;
import com.example.codewizard.api.model.Libro;
import com.example.codewizard.api.services.BookService;

import java.text.ParseException;
import java.util.Date;
import java.util.List;


public class RegisterBook extends AppCompatActivity {
    private Button buttonRegisterBook;
    private EditText editTextSinopsis;
    private EditText editTextTitulo;
    private EditText editTextIsbn;
    private EditText editTextEdicion;
    private EditText editTextFechaPublicacion;
    private EditText editTextNumeroDePaginas;
    private Spinner spinnerIdioma;
    private Spinner spinnerAutor;
    private Spinner spinnerEditorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerbook);
        loadData();
        buttonRegisterBook = findViewById(R.id.buttonRegisterBook);
        editTextSinopsis = findViewById(R.id.editTextSinopsis);
        editTextTitulo = findViewById(R.id.editTextTitulo);
        editTextIsbn = findViewById(R.id.editTextIsbn);
        editTextEdicion = findViewById(R.id.editTextEdicion);
        editTextFechaPublicacion = findViewById(R.id.editTextFechaPublicacion);
        editTextNumeroDePaginas = findViewById(R.id.editTextNumeroDePaginas);
        spinnerIdioma = findViewById(R.id.spinnerIdioma);

        buttonRegisterBook.setOnClickListener(view -> {
            registerBook();
        });
    }

    private void registerBook() {
        Libro libro = new Libro();
        libro.setEdicion(editTextEdicion.getText().toString());
        libro.setsipnosis(editTextSinopsis.getText().toString());
        libro.setTitulo(editTextTitulo.getText().toString());
        libro.setIsbn(editTextIsbn.getText().toString());

        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yy");
        try {
            Date fechaPublicacion = inputFormat.parse(editTextFechaPublicacion.getText().toString());
            libro.setFechaPublicacion(fechaPublicacion);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Autor autorSelected = (Autor) spinnerAutor.getSelectedItem();
        libro.setIdAutor(autorSelected.getIdAutor());
        libro.setIdEditorial(3);
        libro.setIdioma("Español");

        libro.setNumeroDePaginas(Integer.parseInt(editTextNumeroDePaginas.getText().toString()));
        Toast.makeText(getApplicationContext(), editTextFechaPublicacion.getText().toString(), Toast.LENGTH_SHORT).show();

        //ApiResponse apiResponse = BookService.addBook(libro);
/*
        if(!apiResponse.isError()){
            Toast.makeText(getApplicationContext(), "Tu review se a publicado con exito", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(), "Error al publicar, vuelve a intentarlo", Toast.LENGTH_SHORT).show();
        }*/

    }

    private void loadData(){
        spinnerAutor = findViewById(R.id.spinnerAutor);
        spinnerEditorial = findViewById(R.id.spinnerEditorial);
        ApiResponse apiResponseEditoriales = BookService.allEditorialBooks();
        ApiResponse apiResponseAutores = BookService.allAuthorsBook();

        if(!apiResponseAutores.isError() && !apiResponseEditoriales.isError()){
            List<Autor> listAutores = apiResponseAutores.getAutores();
            ArrayAdapter<Autor> adapterAutor = new ArrayAdapter<>(RegisterBook.this, android.R.layout.simple_spinner_item, listAutores);
            adapterAutor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerAutor = findViewById(R.id.spinnerAutor);
            spinnerAutor.setAdapter(adapterAutor);

            List<Editorial> listEditoriales = apiResponseEditoriales.getEditoriales();
            ArrayAdapter<Editorial> adapterEditorial = new ArrayAdapter<>(RegisterBook.this, android.R.layout.simple_spinner_item, listEditoriales);
            adapterEditorial.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerEditorial = findViewById(R.id.spinnerEditorial);
            spinnerEditorial.setAdapter(adapterEditorial);
        }else {
            Toast.makeText(getApplicationContext(), "Error intentelo mas tarde", Toast.LENGTH_SHORT).show();
        }
    }

}