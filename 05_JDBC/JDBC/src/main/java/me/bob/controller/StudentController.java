package me.bob.controller;

import me.bob.entity.StudentEntity;
import me.bob.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.UUID;

@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping(value = "insert")
    private void insert() throws SQLException {
        studentRepository.add(StudentEntity.builder().uuid(UUID.randomUUID().toString()).name("Bob").build());
    }

    @GetMapping(value = "update")
    private void update() throws SQLException {
        studentRepository.update(StudentEntity.builder().build());
    }
}
