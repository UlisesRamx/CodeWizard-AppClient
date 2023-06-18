package com.example.codewizard.ui.bookmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.codewizard.R;
import com.example.codewizard.ui.registerbook.RegisterBook;
import com.example.codewizard.ui.updateanddeletebooks.UpdateAndDeleteBooks;

public class BookManagement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmanagement);

        Button buttonRegister = findViewById(R.id.buttonRegistrarLibro);
        Button buttonUpdateAndDelete = findViewById(R.id.buttonEditarEliminar);

        buttonRegister.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), RegisterBook.class);
            startActivity(intent);
        });

        buttonUpdateAndDelete.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), UpdateAndDeleteBooks.class);
            startActivity(intent);
        });

    }
}