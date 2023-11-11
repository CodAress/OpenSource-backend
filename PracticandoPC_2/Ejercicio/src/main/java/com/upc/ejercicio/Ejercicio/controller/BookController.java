package com.upc.ejercicio.Ejercicio.controller;

import com.upc.ejercicio.Ejercicio.exeption.ValidationException;
import com.upc.ejercicio.Ejercicio.model.Book;
import com.upc.ejercicio.Ejercicio.repository.BookRepository;
import com.upc.ejercicio.Ejercicio.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
* Controlador REST para la gestión de libros dentro de la API
* Proporciona los metodos para gestionar libros
* @Author: Aldo Baldeon
* @Version: 1.0, 5/06/2021
* */
@Tag(name = "Book Controller", description = "Conrtroller for gestions books")
@RestController
@RequestMapping("/api/library/v1")
public class BookController {
    //Inyección de dependencias para el uso de los métodos de la interfaz BookRepository
    @Autowired
    private BookService bookService;
    //Repositorio para operaciones con la base de datos de libros
    private final BookRepository bookRepository;
    /**
     * Constructor de la clase BookController
     * @param bookRepository El repositorio para operaciones relacionadas con la base de datos de libros
     */

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Obtiene una lisata de todos los libros registrados en la base de datos
     * @return Una entidad de respuesta que contiene la lista de libros y el estado de la operación
     */

    //URL: http://localhost:8080/api/library/v1/books
    //Method: GET
    @Operation(summary = "Get all books")
    @ApiResponse(responseCode = "200", description = "Operation executed successfully",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Book.class)))
    @Transactional(readOnly = true)
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(){
        return new ResponseEntity<List<Book>>(bookRepository.findAll(), HttpStatus.OK);
    }
    /**
     * Crea un nuevo libro en la base de datos
     * @param book El libro que se desea crear
     * @return Una entidad de respuesta que contiene el libro creado y el estado de la operación
     */
    //URL: http://localhost:8080/api/library/v1/books
    //Method: POST
    @Operation(summary = "Create a new book")
    @ApiResponse(responseCode = "201", description = "Book created",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Book.class)))
    @Transactional
    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        validateBook(book);
        existBookByTitleAndEditorial(book);
        return new ResponseEntity<Book>(bookService.createBook(book), HttpStatus.CREATED);
    }

    /**
     * Obtiene una lista de libros por editorial
     * @param editorial La editorial por la que se desea filtrar los libros
     * @return Una entidad de respuesta que contiene la lista de libros y el estado de la operación
    * */
    //Listado de libros por editorial
    //URL: http://localhost:8080/api/library/v1/books/filterByEditorial
    //Method: GET
    @Operation(summary = "Get all books by editorial")
    @ApiResponse(responseCode = "200", description = "Operation executed successfully",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Book.class)))
    @Transactional(readOnly = true)
    @GetMapping("/books/filterByEditorial")
    public ResponseEntity<List<Book>> getBooksByEditorial(@RequestParam(name = "editorial") String editorial){
        return new ResponseEntity<List<Book>>(bookRepository.findByEditorial(editorial), HttpStatus.OK);
    }

    /**
     * Valida que la información del libro cumpla con las restricciones de negocio
     * @param book El libro que se desea validar
     * @throws ValidationException si el libro no cumple con las validaciones de negocio
     */

    private void validateBook(Book book){
        // Cuando no se ingrese un title se debe mostrar el mensaje “El título del
        //libro debe ser obligatorio”
        if(book.getTitle() == null || book.getTitle().isEmpty()){
            throw new ValidationException("The title of the book should be required");
        }
        /*
         Cuando se ingrese un title que excede los 22 caracteres se debe mostrar
           el mensaje “El título del libro no debe exceder los 22 caracteres”
        */
        if(book.getTitle().length() > 22){
            throw new ValidationException("The title of the book should not exceed 22 characters");
        }
        //Cuando no se ingrese una editorial se debe mostrar el mensaje “La
        //editorial del libro debe ser obligatorio”
        if(book.getEditorial() == null || book.getEditorial().isEmpty()){
            throw new ValidationException("The editorial of the book should be required");
        }
        //Cuando se ingrese una editorial que excede los 14 caracteres se debe
        //mostrar el mensaje “La editorial del libro no debe exceder los 14
        //caracteres”.
        if(book.getEditorial().length() > 14){
            throw new ValidationException("The editorial of the book should not exceed 14 characters");
        }
    }
    /**
     * Verifica si existe un libro con el mismo título y editorial
     * @param book El libro que se desea verificar
     * @throws ValidationException si el libro ya existe
     */
    private void existBookByTitleAndEditorial(Book book){

        /*
         No se debe permitir el registro de un book con el mismo title y editorial
         o Cuando se trate de registrar un book con un title y editorial que ya existe
         se debe mostrar el siguiente mensaje “No se puede registrar el libro
         porque existe uno con el mismo título y editorial”
         */
        if(bookRepository.existsByTitleAndEditorial(book.getTitle(), book.getEditorial())){
            throw new ValidationException("Dont register book title: " + book.getTitle() + " and editorial: " + book.getEditorial() + " because already exist");
        }
    }
}
