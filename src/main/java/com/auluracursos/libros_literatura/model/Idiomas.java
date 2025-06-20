package com.auluracursos.libros_literatura.model;

public enum Idiomas {
    desconocido("nd"),
    español("es"),
    inglés("en"),
    francés("fr"),
    portugés("pt");

    private String idiomasLibro;

    Idiomas (String idiomasLibro){
        this.idiomasLibro = idiomasLibro;
    }

    public static Idiomas fromString(String text) {
        for (Idiomas idiomas : Idiomas.values()){
            if(idiomas.idiomasLibro.endsWith(text)){
                return idiomas;
            }
        }
        throw new IllegalArgumentException("No se encontraron idiomas: " + text);
    }
}
