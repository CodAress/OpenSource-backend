package com.upc.ejercicio.Ejercicio.profiles.student.service.impl;

import com.upc.ejercicio.Ejercicio.profiles.shared.excetion.ValidationException;
import com.upc.ejercicio.Ejercicio.profiles.student.model.Student;
import com.upc.ejercicio.Ejercicio.profiles.student.repository.StudentRepository;
import com.upc.ejercicio.Ejercicio.profiles.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class StudentServiceImpl implements StudentService {

    /*
    Como reglas de negocio, travelers.io:
    No permite que se registre un student con el mismo DNI.
    No permite que se registre un student que sea menor de edad.
    No permite que se registre dos student con el mismo streetAddress.
    No permite que se registre un student que no tenga gender (Male or Female).
    * */
    @Autowired
    StudentRepository studentRepository;
    @Override
    public Student createStudent(Student student) {
        //existsStudentByDni(student);
        //validateStudent(student);
        //existsStudentByStreetAddress(student);
        return studentRepository.save(student);
    }
    private void existsStudentByDni(Student student) {
        if (studentRepository.existsStudentByDni(student.getDni())){
            throw new ValidationException("The student could not be registered because there is already one with the same DNI");
        }
    }
    private void validateStudent(Student student){
        //determinar si es mayor de edad
        if(Period.between(student.getBirthdate(), LocalDate.now()).getYears() < 18)
        {
            throw  new ValidationException("the student must be of legal age (over 18)");
        }
        //No permite que se registre un student que no tenga gender (Male or Female).
        if(!student.getGender().equals("Male") && !student.getGender().equals("Female"))
        {
            throw new ValidationException("the student gender must be Male or Female");
        }
    }
    private void existsStudentByStreetAddress(Student student) {
        if (studentRepository.existsStudentByStreetAddress(student.getStreetAddress())){
            throw new ValidationException("The student could not be registered because there is already one with the same street address");
        }
    }
}
