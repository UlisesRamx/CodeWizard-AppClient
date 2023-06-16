package com.example.codewizard.api.model;

public class Resenia {
    private int idResenia;
    private Usuario usuario;
    private String descripcion;
    private int valoracion;
    private boolean activa;
    private int idUsuario;
    private int idLibro;

    public Resenia() {
        this.idResenia = 0;
        this.usuario = new Usuario();
        this.descripcion = "";
        this.valoracion = 0;
        this.activa = true;
        this.idUsuario = 0;
    }

    public Resenia(int idResenia, Usuario usuario, String descripcion, int valoracion, boolean activa, int idUsuario) {
        this.idResenia = idResenia;
        this.usuario = usuario;
        this.descripcion = descripcion;
        this.valoracion = valoracion;
        this.activa = activa;
        this.idUsuario = idUsuario;
    }

    public int getIdResenia() {
        return idResenia;
    }

    public void setIdResenia(int idResenia) {
        this.idResenia = idResenia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int getIdLibro() {
        return idLibro;
    }
}