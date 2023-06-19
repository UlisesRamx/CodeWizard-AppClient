package com.example.codewizard.ui.updateanddeletebooks;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codewizard.R;
import com.example.codewizard.api.ApiResponse;
import com.example.codewizard.api.model.Autor;
import com.example.codewizard.api.model.Editorial;
import com.example.codewizard.api.model.Libro;
import com.example.codewizard.api.services.BookService;
import com.example.codewizard.ui.registerbook.RegisterBook;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UpdateAndDeleteBooks extends AppCompatActivity {

    private Spinner spinnerBooks;
    private Button buttonDelete;
    private Button buttonSave;
    private EditText editTextTitulo;
    private EditText editTextIsbn;
    private EditText editTextEdicion;
    private EditText editTextFechaPublicacion;
    private EditText editTextNumeroDePaginas;
    private EditText editTextSinopsis;
    private Spinner spinnerIdioma;
    private Spinner spinnerAutor;
    private Spinner spinnerEditorial;
    private Libro selectedLibro;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete_books);
        buttonSave = findViewById(R.id.buttonSave);
        buttonDelete = findViewById(R.id.buttonDelete);
        editTextSinopsis = findViewById(R.id.editSinopsis);
        editTextTitulo = findViewById(R.id.editTitle);
        editTextIsbn = findViewById(R.id.editIsbnEdit);
        editTextFechaPublicacion = findViewById(R.id.editFechaPublicacion);
        editTextNumeroDePaginas = findViewById(R.id.editTextNumeroPaginas);
        editTextEdicion = findViewById(R.id.editEdicion);
        spinnerBooks = findViewById(R.id.spinnerAllBooks);
        spinnerIdioma = findViewById(R.id.spinnerEditLanguage);
        spinnerEditorial = findViewById(R.id.spinnerEditEditorial);
        spinnerAutor = findViewById(R.id.spinnerEditAutor);
        loadData();
        loadLenguages();

        spinnerBooks.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedLibro = (Libro) parent.getItemAtPosition(position);
                updateFields(selectedLibro);
            }

            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Seleccione un libro", Toast.LENGTH_SHORT).show();
            }
        });

        buttonDelete.setOnClickListener(view -> {
            if(selectedLibro.getIdLibro() > 0){
                deleteBook(selectedLibro.getIdLibro());
            }else{
                Toast.makeText(getApplicationContext(), "Seleccione un libro", Toast.LENGTH_SHORT).show();
            }
        });

        buttonSave.setOnClickListener(view -> {
            if(selectedLibro.getIdLibro() > 0){
                deleteBook(selectedLibro.getIdLibro());
            }else{
                Toast.makeText(getApplicationContext(), "Seleccione un libro", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadData() {
        ApiResponse apiResponseBooks = BookService.allBooks();
        ApiResponse apiResponseAutores = BookService.allAuthorsBook();
        ApiResponse apiResponseEditoriales = BookService.allEditorialBooks();
        if (!apiResponseBooks.isError() && !apiResponseEditoriales.isError() && !apiResponseAutores.isError()) {
            Map<Integer, Libro> librosAutores = apiResponseBooks.getLibrosAutores();
            List<Libro> listLibros = new ArrayList<>(librosAutores.values());
            ArrayAdapter<Libro> adapterLibros = new ArrayAdapter<>(UpdateAndDeleteBooks.this, android.R.layout.simple_spinner_item, listLibros);
            adapterLibros.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerBooks.setAdapter(adapterLibros);

            List<Autor> listAutores = apiResponseAutores.getAutores();
            ArrayAdapter<Autor> adapterAutor = new ArrayAdapter<>(UpdateAndDeleteBooks.this, android.R.layout.simple_spinner_item, listAutores);
            adapterAutor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerAutor.setAdapter(adapterAutor);

            List<Editorial> listEditoriales = apiResponseEditoriales.getEditoriales();
            ArrayAdapter<Editorial> adapterEditorial = new ArrayAdapter<>(UpdateAndDeleteBooks.this, android.R.layout.simple_spinner_item, listEditoriales);
            adapterEditorial.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerEditorial.setAdapter(adapterEditorial);
        } else {
            Toast.makeText(getApplicationContext(), "Error, inténtelo más tarde", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateFields(Libro selectedLibro){
        if(selectedLibro!=null){
            editTextTitulo.setText(selectedLibro.getTitulo());
            editTextIsbn.setText(selectedLibro.getIsbn());
            editTextEdicion.setText(selectedLibro.getEdicion());
            editTextFechaPublicacion.setText(selectedLibro.getFechaPublicacion().toString());
            editTextSinopsis.setText(selectedLibro.getSipnosis());
            String numeroDePaginas = String.valueOf(selectedLibro.getNumeroDePaginas());
            editTextNumeroDePaginas.setText(numeroDePaginas);
            int idAutor = selectedLibro.getAutores().get(0).getIdAutor();
            String idioma = selectedLibro.getIdioma();
            int idEditorial = selectedLibro.getIdEditorial();

            ArrayAdapter<Autor> adapterAutor = (ArrayAdapter<Autor>) spinnerAutor.getAdapter();
            if (adapterAutor != null) {
                int autorPosition = findPositionByIdAutor(adapterAutor,idAutor);
                spinnerAutor.setSelection(autorPosition);
            }

            ArrayAdapter<String> adapterIdioma = (ArrayAdapter<String>) spinnerIdioma.getAdapter();
            if (adapterIdioma != null) {
                int idiomaPosition = adapterIdioma.getPosition(idioma);
                spinnerIdioma.setSelection(idiomaPosition);
            }

            ArrayAdapter<Editorial> adapterEditorial = (ArrayAdapter<Editorial>) spinnerEditorial.getAdapter();
            if (adapterEditorial != null) {
                int editorialPosition = findPositionByIdEditorial(adapterEditorial, idEditorial);
                spinnerEditorial.setSelection(editorialPosition);
            }
        }
    }

    private void loadLenguages() {
        ArrayAdapter<CharSequence> adapterIdioma = ArrayAdapter.createFromResource(this, R.array.idiomas_array, android.R.layout.simple_spinner_item);
        adapterIdioma.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdioma.setAdapter(adapterIdioma);
    }

    private int findPositionByIdAutor(ArrayAdapter<Autor> adapter, int idAutor) {
        for (int i = 0; i < adapter.getCount(); i++) {
            Autor autor = adapter.getItem(i);
            if (autor != null && autor.getIdAutor() == idAutor) {
                return i;
            }
        }
        return 0;
    }

    private int findPositionByIdEditorial(ArrayAdapter<Editorial> adapter, int idEditorial) {
        for (int i = 0; i < adapter.getCount(); i++) {
            Editorial editorial = adapter.getItem(i);
            if (editorial != null && editorial.getIdEditorial() == idEditorial) {
                return i;
            }
        }
        return 0;
    }

    private void deleteBook(int idLibro){
        ApiResponse apiResponseBooks = BookService.deleteBook(idLibro);
        if(!apiResponseBooks.isError()){
            Toast.makeText(getApplicationContext(), "Acción realizada con exito", Toast.LENGTH_SHORT).show();
            ArrayAdapter<Libro> adapterLibrosToDelete = (ArrayAdapter<Libro>) spinnerBooks.getAdapter();
            if (adapterLibrosToDelete != null) {
                for (int i = 0; i < adapterLibrosToDelete.getCount(); i++) {
                    Libro libro = adapterLibrosToDelete.getItem(i);
                    if (libro != null && libro.equals(selectedLibro)) {
                        adapterLibrosToDelete.remove(libro);
                        adapterLibrosToDelete.notifyDataSetChanged();
                        break;
                    }
                }
            }
        }else{
            Toast.makeText(getApplicationContext(), "Error, inténtelo más tarde", Toast.LENGTH_SHORT).show();
        }
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

    private void updateBook() {
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
        ApiResponse apiResponse = BookService.updateBook(libro);
        if(!apiResponse.isError()){
            Toast.makeText(getApplicationContext(), "El libro se actualizo con exito", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(), "No se pudo actualizar el libro", Toast.LENGTH_SHORT).show();
        }
    }

}