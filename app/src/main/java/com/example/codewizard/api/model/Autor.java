package com.example.codewizard.api.model;

public class Autor {
    private int idAutor;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;

    public Autor() {
        this.idAutor = 0;
        this.nombre = "";
        this.apellidoPaterno = "";
        this.apellidoMaterno = "";
    }

    public Autor(int idAutor, String nombre, String apellidoPaterno, String apellidoMaterno) {
        this.idAutor = idAutor;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
}

