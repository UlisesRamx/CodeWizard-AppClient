package com.example.codewizard.api.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Libro {
    private int idLibro;
    private Editorial editorial;
    private String isbn;
    private Date fechaPublicacion;
    private String titulo;
    private String edicion;
    private int numeroDePaginas;
    private String idioma;
    private String sipnosis;
    private List<Autor> autores;
    private  List<Resenia> resenias;
    private int idEditorial;
    private int idAutor;

    public Libro() {
        this.idLibro = 0;
        this.editorial = new Editorial();
        this.isbn = "";
        this.fechaPublicacion = new Date();
        this.titulo = "";
        this.edicion = "";
        this.numeroDePaginas = 0;
        this.idioma = "";
        this.autores = new ArrayList<>();
        this.resenias = new ArrayList<>();
        this.sipnosis = "";
        this.idEditorial = 0;
        this.idAutor = 0;
    }

    public Libro(int idLibro, Editorial editorial, String isbn, Date fechaPublicacion, String titulo, String edicion, int numeroDePaginas, String idioma, List<Autor> autores, List<Resenia> resenias, String sipnosis, int idEditorial, int idAutor) {
        this.idLibro = idLibro;
        this.editorial = editorial;
        this.isbn = isbn;
        this.fechaPublicacion = fechaPublicacion;
        this.titulo = titulo;
        this.edicion = edicion;
        this.numeroDePaginas = numeroDePaginas;
        this.idioma = idioma;
        this.sipnosis = sipnosis;
        this.autores = autores;
        this.resenias = resenias;
        this.idEditorial = idEditorial;
        this.idAutor = idLibro;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public int getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public void setNumeroDePaginas(int numeroDePaginas) {
        this.numeroDePaginas = numeroDePaginas;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
    public String getSipnosis() {
        return sipnosis;
    }

    public void setSipnosis(String sipnosis) {
        this.sipnosis = sipnosis;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<Resenia> getResenias() {
        return resenias;
    }

    public void setResenias(List<Resenia> resenias) {
        this.resenias = resenias;
    }
    //public void set

    public String getDetails(){
        return "Editorial: "+editorial.getIdEditorial()+"\n\nISBN: "+isbn+"\n\nFecha Publicacion :"+fechaPublicacion
                +"\n\nEdicion: "+edicion+"\n\nNumero De Paginas :"+numeroDePaginas+"\n\nIdioma: "+idioma;}

    public int getIdEditorial() {return idEditorial;}

    public void setIdEditorial(int idEditorial) {this.idEditorial = idEditorial;}

    public int getIdAutor() {return idAutor;}

    public void setIdAutor(int idAutor) {this.idAutor = idAutor;}
}
