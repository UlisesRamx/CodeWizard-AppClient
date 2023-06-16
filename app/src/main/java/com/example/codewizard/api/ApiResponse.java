package com.example.codewizard.api;

import com.example.codewizard.api.model.Autor;
import com.example.codewizard.api.model.Libro;
import com.example.codewizard.api.model.Resenia;
import com.example.codewizard.api.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ApiResponse {

    private int code;
    private boolean error;
    private String message;
    private  String token;
    private Usuario user;
    private int otp;
    private String correo;
    private Usuario usuario;//Para ver perfil
    private List<Usuario> usuarios;//Para buscar usuarios
    private  int affectedRows;//Para cualquier insert, delete, update
    private List<Libro> libros;
    private  Libro libro;
    private List<Resenia> resenias;
    private List<Autor> autores;
    private Autor autor;

    public ApiResponse() {
        this.code = 0;
        this.error = true;
        this.message = "";
        this.token = "";
        this.user = new Usuario();
        this.otp = 0;
        this.correo = "";
        this.usuario = new Usuario();
        this.usuarios = new ArrayList<>();
        this.affectedRows = 0;
        this.libros = new ArrayList<>();
        this.libro = new Libro();
        this.resenias = new ArrayList<>();
        this.autores = new ArrayList<>();
        this.autor = new Autor();
    }

    public ApiResponse(int code, boolean error, String message, String token, Usuario user, int otp, String correo, Usuario usuario, List<Usuario> usuarios, int affectedRows, List<Libro> libros, Libro libro, List<Resenia> resenias, List<Autor> autores, Autor autor) {
        this.code = code;
        this.error = error;
        this.message = message;
        this.token = token;
        this.user = user;
        this.otp = otp;
        this.correo = correo;
        this.usuario = usuario;
        this.usuarios = usuarios;
        this.affectedRows = affectedRows;
        this.libros = libros;
        this.libro = libro;
        this.resenias = resenias;
        this.autores = autores;
        this.autor = autor;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public int getAffectedRows() {
        return affectedRows;
    }

    public void setAffectedRows(int affectedRows) {
        this.affectedRows = affectedRows;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public List<Resenia> getResenias() {
        return resenias;
    }

    public void setResenias(List<Resenia> resenias) {
        this.resenias = resenias;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }
}
