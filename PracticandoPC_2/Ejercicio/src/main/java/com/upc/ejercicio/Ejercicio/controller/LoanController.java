package com.upc.ejercicio.Ejercicio.controller;

import com.upc.ejercicio.Ejercicio.exeption.ValidationException;
import com.upc.ejercicio.Ejercicio.model.Loan;
import com.upc.ejercicio.Ejercicio.repository.BookRepository;
import com.upc.ejercicio.Ejercicio.repository.LoanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/library/v1")
public class LoanController {
    private LoanRepository loanRepository;
    private BookRepository bookRepository;

    public LoanController(LoanRepository loanRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
    }

    //Listado de préstamos por código de alumno (GET)
    //URL: http://localhost:8080/api/library/v1/loans/filterByCodeStudent
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/loans/filterByCodeStudent")
    public ResponseEntity<List<Loan>> getLoansByCodeStudent(@RequestParam(name = "codeStudent") String codeStudent){
        return new ResponseEntity<List<Loan>>(loanRepository.findByCodeStudent(codeStudent), HttpStatus.OK);
    }

    /*
    - Para realizar el registro de préstamo de libros solo se debe enviar el codeStudent y
    book (id). El valor de loanDate y devolutionDate se obtiene del sistema. En el caso
    de bookLoan debe tener por defecto un valor de true.

    - No se debe permitir el registro de un préstamo de libro con el mismo codeStudent
    book(id) y que bookLoan sea true. En caso se intente registrar un prestamos que
    no cumpla esta regla de negocio se debe mostrar el mensaje “El préstamo del
    libro con código XXXX no es posible porque ya fue prestado por el alumno YYYY”.
    Donde XXXX debe reemplazarlo por el title del libro y YYYY por el código del
    alumno.

    - Validar que los atributos del Loan cumplan las siguientes restricciones
    o Cuando no se ingrese un codeStudent se debe mostrar el mensaje “El
    código del alumno debe ser obligatorio”
    o Cuando se ingrese un codeStudent menor de 10 caracteres se debe
    mostrar el mensaje “El código del alumno debe tener 10 caracteres”

    */

    //URL: http://localhost:8080/api/library/v1/books/1/loans
    //Method: POST
    @Transactional
    @PostMapping("/books/{id}/loans")
    public ResponseEntity<Loan> createLoan(@PathVariable(value = "id") Long bookId, @RequestBody Loan loan){
        validateLoan(loan);
        existLoanByCodeStudentAndBookAndBookLoan(loan);
        loan.setLoanDate(LocalDate.now());
        loan.setDevolutionDate(LocalDate.now().plusDays(3));
        loan.setBookLoan(true);
        return new ResponseEntity<Loan>(loanRepository.save(loan), HttpStatus.CREATED);
    }
    private void validateLoan(Loan loan){
        if(loan.getCodeStudent() == null || loan.getCodeStudent().isEmpty()){
            throw new ValidationException("The code of the student should be required");
        }
        if(loan.getCodeStudent().length() < 10){
            throw new ValidationException("The code of the student should be 10 characters");
        }
    }
    private void existLoanByCodeStudentAndBookAndBookLoan(Loan loan){
        if(loanRepository.existsByCodeStudentAndBookAndBookLoan(loan.getCodeStudent(), loan.getBook(), true)){
            throw new ValidationException("The loan of the book with code: " + loan.getBook().getTitle() + " is not possible because it has already been borrowed by the student: " + loan.getCodeStudent());
        }
    }

}
