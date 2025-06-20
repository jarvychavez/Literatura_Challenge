package com.auluracursos.libros_literatura.repository;

import com.auluracursos.libros_literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    @Query(value = "SELECT * FROM libros WHERE ? = ANY(string_to_array(idiomas, ','))", nativeQuery = true)
    List<Libro> buscarPorIdioma(String idioma);

    Optional<Libro> findByTituloContainingIgnoreCase(String tituloDelLibro);

    List<Libro> findByIdiomasContaining(String idioma);
}