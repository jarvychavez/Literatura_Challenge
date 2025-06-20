package com.auluracursos.libros_literatura;

import com.auluracursos.libros_literatura.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibrosLiteraturaApplication implements CommandLineRunner {

	@Autowired
	private Principal principal;

	public static void main(String[] args) {
		SpringApplication.run(LibrosLiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		principal.muestraElMenu(); // Llama al método de Principal
	}
}
