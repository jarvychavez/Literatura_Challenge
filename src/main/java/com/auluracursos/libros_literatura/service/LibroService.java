package com.auluracursos.libros_literatura.service;

import com.auluracursos.libros_literatura.model.Autor;
import com.auluracursos.libros_literatura.model.Libro;
import com.auluracursos.libros_literatura.repository.AutorRepository;
import com.auluracursos.libros_literatura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    public List<Libro> obtenerLibrosPorIdioma(String idioma) {
        return libroRepository.findByIdiomasContaining(idioma);
    }

    public List<Autor> obtenerAutoresVivosEnAnio(int anio) {
        return autorRepository.findAutoresVivosEnAnio(anio);
    }

    public List<Autor> obtenerTodosLosAutores() {
        return autorRepository.findAll();
    }
}