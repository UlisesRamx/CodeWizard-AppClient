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
        spinnerIdioma = findViewById(R.id.spinnerIdioma);
        loadData();
        loadLenguages();
        buttonRegisterBook = findViewById(R.id.buttonRegisterBook);
        editTextSinopsis = findViewById(R.id.editTextSinopsis);
        editTextTitulo = findViewById(R.id.editTextTitulo);
        editTextIsbn = findViewById(R.id.editTextIsbn);
        editTextEdicion = findViewById(R.id.editTextEdicion);
        editTextFechaPublicacion = findViewById(R.id.editTextFechaPublicacion);
        editTextNumeroDePaginas = findViewById(R.id.editTextNumeroDePaginas);

        buttonRegisterBook.setOnClickListener(view -> {
            if (validateFields()) {
                registerBook();
            }
        });
    }

    private void registerBook() {
        Libro libro = new Libro();
        libro.setEdicion(editTextEdicion.getText().toString());
        libro.setSipnosis(editTextSinopsis.getText().toString());
        libro.setTitulo(editTextTitulo.getText().toString());
        libro.setIsbn(editTextIsbn.getText().toString());
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yy");
        try {
            Date fechaPublicacion = inputFormat.parse(editTextFechaPublicacion.getText().toString());
            libro.setFechaPublicacion(fechaPublicacion);
            Toast.makeText(getApplicationContext(), fechaPublicacion.toString(), Toast.LENGTH_SHORT).show();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Autor autorSelected = (Autor) spinnerAutor.getSelectedItem();
        libro.setIdAutor(autorSelected.getIdAutor());
        Editorial editorialSelected = (Editorial) spinnerEditorial.getSelectedItem();
        libro.setIdEditorial(editorialSelected.getIdEditorial());
        libro.setIdioma(spinnerIdioma.getSelectedItem().toString());
        libro.setNumeroDePaginas(Integer.parseInt(editTextNumeroDePaginas.getText().toString()));
        Toast.makeText(getApplicationContext(), editTextFechaPublicacion.getText().toString(), Toast.LENGTH_SHORT).show();

        ApiResponse apiResponse = BookService.addBook(libro);
        if(!apiResponse.isError()){
            Toast.makeText(getApplicationContext(), "El libro se publico con exito", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(), "No se pudo publicar el libro", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadData(){
        spinnerAutor = findViewById(R.id.spinnerAutor);
        spinnerEditorial = findViewById(R.id.spinnerEditorial);
        ApiResponse apiResponseAutores = BookService.allAuthorsBook();
        ApiResponse apiResponseEditoriales = BookService.allEditorialBooks();
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

    private void loadLenguages() {
        ArrayAdapter<CharSequence> adapterIdioma = ArrayAdapter.createFromResource(this, R.array.idiomas_array, android.R.layout.simple_spinner_item);
        adapterIdioma.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdioma.setAdapter(adapterIdioma);
    }

    private boolean validateFields() {
        boolean validation = true;
        String sinopsis = editTextSinopsis.getText().toString().trim();
        String titulo = editTextTitulo.getText().toString().trim();
        String isbn = editTextIsbn.getText().toString().trim();
        String edicion = editTextEdicion.getText().toString().trim();
        String fechaPublicacion = editTextFechaPublicacion.getText().toString().trim();
        String numeroDePaginas = editTextNumeroDePaginas.getText().toString().trim();
        if (sinopsis.isEmpty()) {
            validation = false;
            Toast.makeText(getApplicationContext(), "Campo Sinopsis está vacío.", Toast.LENGTH_SHORT).show();
        }
        if (titulo.isEmpty()) {
            validation = false;
            Toast.makeText(getApplicationContext(), "Campo Título está vacío.", Toast.LENGTH_SHORT).show();
        }
        if (isbn.isEmpty()) {
            validation = false;
            Toast.makeText(getApplicationContext(), "Campo ISBN está vacío.", Toast.LENGTH_SHORT).show();
        }
        if (edicion.isEmpty()) {
            validation = false;
            Toast.makeText(getApplicationContext(), "Campo Edición está vacío.", Toast.LENGTH_SHORT).show();
        }
        if (fechaPublicacion.isEmpty()) {
            validation = false;
            Toast.makeText(getApplicationContext(), "Campo Fecha de Publicación está vacío.", Toast.LENGTH_SHORT).show();
        }
        if (numeroDePaginas.isEmpty()) {
            validation = false;
            Toast.makeText(getApplicationContext(), "Campo Número de Páginas está vacío.", Toast.LENGTH_SHORT).show();
        }
        return validation;
    }

}