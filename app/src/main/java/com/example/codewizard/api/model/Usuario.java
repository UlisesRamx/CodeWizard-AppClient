package com.example.codewizard.api.model;

public class Usuario {
    private int idUsuario;
    private String username;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String email;
    private String password;
    private int tipoUsuario;
    private  int otp;
    private int eliminado;

    public Usuario() {
        this.idUsuario = 0;
        this.username = "";
        this.nombre = "";
        this.apellidoPaterno = "";
        this.apellidoMaterno = "";
        this.email = "";
        this.password = "";
        this.tipoUsuario = 0;
        this.otp = 0;
        this.eliminado = 0;
    }

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Usuario(int idUsuario, String username, String nombre, String apellidoPaterno, String apellidoMaterno, String email, String password, int tipoUsuario, int otp, int eliminado) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.email = email;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
        this.otp = otp;
        this.eliminado = eliminado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public int getEliminado() {
        return eliminado;
    }

    public void setEliminado(int eliminado) {
        this.eliminado = eliminado;
    }
}

