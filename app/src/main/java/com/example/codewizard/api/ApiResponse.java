package com.example.codewizard.api;

import com.example.codewizard.api.model.Libro;
import com.example.codewizard.api.model.Usuario;

import java.util.List;

public class ApiResponse {
    private Usuario usuario;//Para ver perfil
    private List<Usuario> usuarios;//Para buscar usuarios
    private List<Libro> libros;
    private  Libro libro;
    private  int affectedRows;//Para cualquier insert, delete, update
}
