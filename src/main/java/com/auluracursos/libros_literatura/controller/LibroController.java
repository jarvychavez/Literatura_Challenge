//package com.auluracursos.libros_literatura.controller;
//
//
//@RestController
//@RequestMapping("/api/libros")
//public class LibroController {
//
//    @Autowired
//    private LibroService libroService;
//
//    @GetMapping("/idioma/{idioma}")
//    public List<Libro> getLibrosPorIdioma(@PathVariable String idioma) {
//        return libroService.obtenerLibrosPorIdioma(idioma);
//    }
//
//    @GetMapping("/autores/vivos/{anio}")
//    public List<Autor> getAutoresVivosEnAnio(@PathVariable int anio) {
//        return libroService.obtenerAutoresVivosEnAnio(anio);
//    }
//
//    @GetMapping("/autores")
//    public List<Autor> getTodosLosAutores() {
//        return libroService.obtenerTodosLosAutores();
//    }
//
//
//
//}
