package com.example.codewizard.api.model;

public class Editorial {
    private int idEditorial;
    private String nombre;

    public Editorial() {
        this.idEditorial = 0;
        this.nombre = "";
    }

    public Editorial(int idEditorial, String nombre) {
        this.idEditorial = idEditorial;
        this.nombre = nombre;
    }

    public int getIdEditorial() {
        return idEditorial;
    }

    public void setIdEditorial(int idEditorial) {
        this.idEditorial = idEditorial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() { return nombre; }
}
