package com.auluracursos.libros_literatura.principal;

import com.auluracursos.libros_literatura.model.*;
import com.auluracursos.libros_literatura.repository.AutorRepository;
import com.auluracursos.libros_literatura.repository.LibroRepository;
import com.auluracursos.libros_literatura.service.ConsumoAPI;
import com.auluracursos.libros_literatura.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component

public class Principal {

    @Autowired
    private AutorRepository autorRepositorio;

    @Autowired
    private LibroRepository libroRepositorio;

    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private List<DatosLibros> librosRegistrados = new ArrayList<>(); // Lista para almacenar libros encontrados
//    private AutorRepository autorRepositorio;
//    private LibroRepository libroRepositorio;
    public void muestraElMenu() {
        boolean continuar = true;

        while (continuar) {
            System.out.println("Menú:");
            System.out.println("1. Buscar libro por título");
            System.out.println("2. Listar libros registrados");
            System.out.println("3. Listar autores registrados");
            System.out.println("4. Listar autores vivos en un determinado año");
            System.out.println("5. Listar libros por idiomas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                int opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion) {
                    case 1:
                        buscarLibroPorTitulo();
                        break;
                    case 2:
                        listarLibrosRegistrados();
                        break;
                    case 3:
                        listarAutoresRegistrados();
                        break;
                    case 4:
                        listarAutoresVivosEnAnio();
                        break;
                    case 5:
                        listarLibrosPorIdioma();
                        break;
                    case 0:
                        continuar = false;
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número válido.");
                teclado.nextLine(); // Limpiar el buffer en caso de error
            }
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Buscar libro por título");
        var tituloLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);

        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();

        if (libroBuscado.isPresent()) {
            System.out.println("Libro Encontrado:");
            System.out.println(libroBuscado.get());

            // Obtener el autor
            String nombreAutor = libroBuscado.get().autor().get(0).nombre();
            String fechaMuerte = libroBuscado.get().autor().get(0).fechaDeMuerte();
            String fechaNacimiento = libroBuscado.get().autor().get(0).fechaDeNacimiento();
            String[] langu = libroBuscado.get().idiomas().toArray(new String[0]);
            String lang = new ArrayList<>(Arrays.asList(langu)).toString();
            Boolean vivo;


            System.out.println("Buscando autor: " + nombreAutor); // Depuración
            Autor autor = autorRepositorio.findByNombreIgnoreCase(nombreAutor).orElse(null);

            Libro lib = new Libro(libroBuscado.get().titulo(), autor, lang, libroBuscado.get().numeroDeDescargas());
            System.out.println(lib);
             //Si el autor no se encuentra, puedes crear uno nuevo o manejarlo de otra manera
            if (autor == null) {
                System.out.println("Autor no encontrado para el libro: " + libroBuscado.get().titulo());
                if(fechaMuerte == null){
                    vivo = true;
                }else{
                    vivo = false;
                }
                autor = new Autor(nombreAutor, vivo, fechaNacimiento, fechaMuerte); // Ajusta según sea necesario
                autorRepositorio.save(autor);
                System.out.println("Nuevo autor creado y guardado en la base de datos.");
            }
            System.out.println(libroBuscado.get().idiomas().stream().toList());
            Optional<Libro> librof = libroRepositorio.findByTituloContainingIgnoreCase(libroBuscado.get().titulo());


            if(librof.isEmpty()){
                // Crear el objeto Libro
                Libro libro = new Libro(libroBuscado.get().titulo(), autor, lang, libroBuscado.get().numeroDeDescargas());

                // Guardar el libro en la base de datos
                libroRepositorio.save(libro);
                System.out.println("Libro guardado en la base de datos.");
            }else{
                System.out.println("Libro ya existe");
            }

        } else {
            System.out.println("Libro No Encontrado");
        }
    }

    private void listarLibrosRegistrados() {
        List<Libro> libros = libroRepositorio.findAll();
        System.out.println("\nLista de libros registrados");
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }

        for (Libro libro : libros) {
            System.out.println("---- LIBRO ----");
            System.out.println("Título: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutor());
            System.out.println("Idioma: " + String.join(", ", libro.getIdiomas()));
            System.out.println("Número de descargas: " + libro.getNumeroDeDescargas());
            System.out.println("----------------\n");
        }
    }


    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepositorio.findAll();

        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
            return;
        }

        for(Autor autor : autores) {
            System.out.println("Autor: " + autor.getNombre());
            System.out.println("Fecha de nacimiento: " + (autor.getFechaDeNacimiento() != null ? autor.getFechaDeNacimiento() : "Desconocido"));
            System.out.println("Fecha de fallecimiento: " + (autor.getFechaDeMuerte() != null ? autor.getFechaDeMuerte() : "Desconocido"));
            System.out.println("Libros: " + autor.getLibros().stream().map(Libro::getTitulo).collect(Collectors.joining(", ")) + "\n");
        }
    }

    private void listarAutoresVivosEnAnio() {
        System.out.print("Ingrese el año: ");
        int anio = teclado.nextInt();
        teclado.nextLine();

        List<Autor> autoresVivos = autorRepositorio.findAutoresVivosEnAnio(anio); // Método en el repositorio

        if (autoresVivos.isEmpty()) {
            System.out.println("No hay autores vivos en el año " + anio + ".");
        } else {
            System.out.println("Autores Vivos en el año " + anio + ":");
            for (Autor autor : autoresVivos) {
                System.out.println(autor); // Asegúrate de que la clase Autor tenga un método toString() adecuado
            }
        }
    }

    private void listarLibrosPorIdioma() {
        var seleccion = -1;
        var idiomaABuscar = "en";
        while(seleccion != 0) {
            var menuIdioma = """
                    Ingresa el idioma para buscar los libros
                    1 - Español (es).
                    2 - Inglés (en).
                    3 - Francés (fr).
                    4 - Portugués (pt).
                    5 - Tagalo (tl).
                    6 - Nulo (null).
                    0 - Salir de la búsqueda por idioma.
                    """;

            System.out.println(menuIdioma);
            seleccion = teclado.nextInt();
            teclado.nextLine();

            switch (seleccion) {
                case 1:
                    idiomaABuscar = "[es]";
                    break;
                case 2:
                    idiomaABuscar = "[en]";
                    break;
                case 3:
                    idiomaABuscar = "[fr]";
                    break;
                case 4:
                    idiomaABuscar = "[pt]";
                    break;
                case 5:
                    idiomaABuscar = "[tl]";
                    break;
                case 6:
                    idiomaABuscar = "[null]";
                    break;
                case 0:
                    System.out.println("Saliendo de la búsqueda por idioma.");
                    return;
                default:
                    System.out.println("Error, seleccione una opción válida.");
                    continue;
            }

            List<Libro> libros = libroRepositorio.buscarPorIdioma(idiomaABuscar);

            if (libros.isEmpty()) {
                System.out.println("Aún no hay libros registrados en ese idioma.");
                continue;
            }

            for (Libro libro : libros) {
                System.out.println("--------------");
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAutor());
                System.out.println("Idioma: " + String.join(", ", libro.getIdiomas()));
                System.out.println("Número de descargas: " + libro.getNumeroDeDescargas() + "\n");
            }
        }
    }
}