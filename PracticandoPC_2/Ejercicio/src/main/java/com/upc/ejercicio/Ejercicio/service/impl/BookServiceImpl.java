package com.upc.ejercicio.Ejercicio.service.impl;

import com.upc.ejercicio.Ejercicio.model.Book;
import com.upc.ejercicio.Ejercicio.repository.BookRepository;
import com.upc.ejercicio.Ejercicio.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/*
Esta anotación de Spring se utiliza para indicar que esta clase es un componente de servicio.
Los componentes de servicio se utilizan para contener la lógica de negocio de la aplicación.
Spring escaneará y registrará esta clase durante la inicialización de la aplicación.
*/
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

}
