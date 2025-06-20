package com.auluracursos.libros_literatura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Llave autoincremental
    private Long id;

    @Column(unique = true)
    private String nombre;

    private boolean vivo;

    private String fechaDeNacimiento;
    private String fechaDeMuerte;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    // Constructor vacío
    public Autor() {}

    // Constructor con parámetros
    public Autor(String nombre, boolean vivo, String fechaDeNacimiento, String fechaDeMuerte) {
        this.nombre = nombre;
        this.vivo = vivo;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.fechaDeMuerte = fechaDeMuerte;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", vivo=" + vivo +
                ", fechaDeNacimiento='" + fechaDeNacimiento + '\'' +
                ", fechaDeMuerte='" + fechaDeMuerte + '\'' +
                '}';

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getFechaDeMuerte() {
        return fechaDeMuerte;
    }

    public void setFechaDeMuerte(String fechaDeMuerte) {
        this.fechaDeMuerte = fechaDeMuerte;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }


}



