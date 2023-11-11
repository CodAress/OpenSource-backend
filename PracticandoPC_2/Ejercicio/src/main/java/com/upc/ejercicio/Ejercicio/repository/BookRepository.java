package com.upc.ejercicio.Ejercicio.repository;

import com.upc.ejercicio.Ejercicio.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//Uso de JpaRepository para la persistencia de datos
//Se crea una interfaz que extiende de JpaRepository y se le pasa como parámetros la entidad y el tipo de dato de la llave primaria
//Se crean los métodos que se necesitan para la persistencia de datos como findByEditorial y existsByTitleAndEditorial
//JpaRepository tiene métodos como save, findAll, findById, deleteById, etc.
public interface BookRepository extends JpaRepository<Book, Long>{
    List<Book> findByEditorial(String editorial);
    Boolean existsByTitleAndEditorial(String title, String editorial);
}
