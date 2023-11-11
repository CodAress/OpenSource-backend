package com.upc.ejercicio.Ejercicio.profiles.student.repository;

import com.upc.ejercicio.Ejercicio.profiles.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

        boolean existsStudentByDni(Long dni);
        boolean existsStudentByStreetAddress(String streetAddress);
}
