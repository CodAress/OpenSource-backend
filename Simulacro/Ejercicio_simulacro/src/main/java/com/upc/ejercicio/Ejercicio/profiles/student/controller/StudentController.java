package com.upc.ejercicio.Ejercicio.profiles.student.controller;

import com.upc.ejercicio.Ejercicio.profiles.student.dto.StudentRequest;
import com.upc.ejercicio.Ejercicio.profiles.student.dto.StudentResponse;
import com.upc.ejercicio.Ejercicio.profiles.student.dto.mapper.StudentMapper;
import com.upc.ejercicio.Ejercicio.profiles.student.service.StudentService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
    @Autowired
    StudentService studentService;

    //URL: http://localhost:8080/api/v1/students
    //Method: POST
    @Transactional
    @PostMapping("/students")
    public ResponseEntity<StudentResponse> createStudent(@RequestBody StudentRequest studentRequest){
        var student = StudentMapper.INSTANCE.StudentRequestToStudent(studentRequest);
        var studentCreated = studentService.createStudent(student);
        return new ResponseEntity<StudentResponse>(StudentMapper.INSTANCE.StudentToStudentResponse(studentCreated), HttpStatus.CREATED);
    }
}
