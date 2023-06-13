package com.example.codewizard.api.model;

import java.util.Date;

public class Reporte {
    private int idReporte;
    private Resenia resenia;
    private Usuario usuario;
    private Date fechaReporte;

    public Reporte() {
        this.idReporte = 0;
        this.resenia = new Resenia();
        this.usuario = new Usuario();
        this.fechaReporte = new Date();
    }

    public Reporte(int idReporte, Resenia resenia, Usuario usuario, Date fechaReporte) {
        this.idReporte = idReporte;
        this.resenia = resenia;
        this.usuario = usuario;
        this.fechaReporte = fechaReporte;
    }

    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    public Resenia getResenia() {
        return resenia;
    }

    public void setResenia(Resenia resenia) {
        this.resenia = resenia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }
}

