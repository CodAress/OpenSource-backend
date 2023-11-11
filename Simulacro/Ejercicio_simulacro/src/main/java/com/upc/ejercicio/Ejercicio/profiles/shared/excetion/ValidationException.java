package com.upc.ejercicio.Ejercicio.profiles.shared.excetion;

public class ValidationException extends RuntimeException{

    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
    }
}
