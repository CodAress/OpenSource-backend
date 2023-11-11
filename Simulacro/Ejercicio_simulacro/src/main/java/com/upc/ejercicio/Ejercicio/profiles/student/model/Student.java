package com.upc.ejercicio.Ejercicio.profiles.student.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/*
El ecosistema de newschool.io requiere que la aplicación de backend cuente con Endpoints en el RESTful API,
para el manejo de la información de estudiantes (student)
conformadas por los atributos name (String), DNI (Long), gender (String), streetAddress (String),
 birthdate (LocalDate), specialty (String).
* */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "dni", nullable = false)
    private Long dni;
    @Column(name = "gender", nullable = false)
    private String gender;
    @Column(name = "street_address", nullable = false)
    private String streetAddress;
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;
    @Column(name = "specialty", nullable = false)
    private String specialty;
}
