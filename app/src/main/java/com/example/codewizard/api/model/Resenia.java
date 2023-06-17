package com.example.codewizard.api.model;

import java.util.Date;

public class Resenia {
    private int idResenia;
    private Usuario usuario;
    private String descripcion;
    private int valoracion;
    private int activa;
    private int idUsuario;
    private int idReporte;
    private Date fechaReporte;
    private int idLibro;

    public Resenia() {
        this.idResenia = 0;
        this.usuario = new Usuario();
        this.descripcion = "";
        this.valoracion = 0;
        this.activa = 0;
        this.idUsuario = 0;
        this.idReporte = 0;
        this.fechaReporte = new Date();
    }

    public Resenia(int idResenia, Usuario usuario, String descripcion, int valoracion, int activa, int idUsuario, int idReporte, Date fechaReporte) {
        this.idResenia = idResenia;
        this.usuario = usuario;
        this.descripcion = descripcion;
        this.valoracion = valoracion;
        this.activa = 0;
        this.idUsuario = idUsuario;
        this.idReporte = idReporte;
        this.fechaReporte = fechaReporte;
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

    public int isActiva() {
        return activa;
    }

    public void setActiva(int activa) {
        this.activa = activa;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int getIdLibro() {
        return idLibro;
    }
}