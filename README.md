# 📚 API de Libros en Java

Este proyecto es una aplicación Java que consume una API externa de libros y permite realizar diversas consultas sobre los datos obtenidos.(Challenge_literatura) Implementado con Spring Boot, con Alura Latam / Oracle en el programa ONE.
Los resultados se almacenan en una base de datos PostgreSQL para su posterior análisis y visualización. 
El proyecto hace uso de tecnologías modernas como JPA y Jackson para el manejo de datos y persistencia.

## 🛠️ Tecnologías utilizadas

- **Java**
- **Spring Boot**
- **JPA (Java Persistence API)**
- **Jackson** (para deserializar los datos JSON de la API)
- **PostgreSQL** (como sistema gestor de base de datos)
- **Maven** (para la gestión de dependencias)

## 🔧 Funcionalidades principales

- 🔍 **Buscar libro por título**  
  Consulta a la API externa para obtener información de un libro según su título.

- 📚 **Listar libros registrados**  
  Muestra los libros almacenados en la base de datos.

- ✍️ **Listar autores registrados**  
  Muestra todos los autores guardados en la base de datos.

- 🧓 **Listar autores vivos en un año específico**  
  Permite consultar qué autores estaban vivos en un determinado año.

- 🌍 **Listar libros por idioma**  
  Filtra los libros almacenados en la base de datos según el idioma.

## 🗃️ Almacenamiento

Todos los datos obtenidos desde la API externa son transformados con Jackson y persistidos en una base de datos PostgreSQL utilizando JPA. Se realizan queries personalizadas para permitir consultas eficientes sobre los datos guardados.

## ▶️ Cómo ejecutar el proyecto

1. Clona el repositorio:
   ```bash
 https://github.com/jarvychavez/Literatura_Challenge
