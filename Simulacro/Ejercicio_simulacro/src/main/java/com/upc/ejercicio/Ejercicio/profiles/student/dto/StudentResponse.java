package com.upc.ejercicio.Ejercicio.profiles.student.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentResponse {

    private Long id;
    private String name;
    private Long dni;
    private String streetAddress;
    private String gender;
    private LocalDate birthdate;
    private String specialty;

}

