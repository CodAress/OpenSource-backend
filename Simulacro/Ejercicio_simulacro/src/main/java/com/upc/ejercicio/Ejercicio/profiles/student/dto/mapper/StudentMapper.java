package com.upc.ejercicio.Ejercicio.profiles.student.dto.mapper;

import com.upc.ejercicio.Ejercicio.profiles.student.dto.StudentRequest;
import com.upc.ejercicio.Ejercicio.profiles.student.dto.StudentResponse;
import com.upc.ejercicio.Ejercicio.profiles.student.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
    Student StudentRequestToStudent(StudentRequest studentRequest);
    StudentResponse StudentToStudentResponse(Student student);
}
