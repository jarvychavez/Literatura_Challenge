package com.auluracursos.libros_literatura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Llave autoincremental
    private Long id;

    private String titulo;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    private String idiomas;

    private Double numeroDeDescargas; // Nuevo campo

    // Constructor vacío
    public Libro() {}

    // Constructor con parámetros
    public Libro(String titulo, Autor autor, String idiomas, Double numeroDeDescargas) {
        this.titulo = titulo;
        this.autor = autor;
        this.idiomas = idiomas;
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor=" + (autor != null ? autor.getNombre() : "null") + // Mostrar solo el nombre del autor
                ", idiomas=" + idiomas + // Mostrar lista de idiomas
                ", numeroDeDescargas=" + numeroDeDescargas + // Mostrar número de descargas
                '}';
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }
}

