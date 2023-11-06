package com.example.controller;

import com.example.entity.Student;
import com.example.model.StudentDaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentService {
    StudentDaoImpl studentDao = new StudentDaoImpl();

    public StudentService() throws SQLException {
    }

    public void createStudent(Student student) {
        studentDao.createStudent(student);
    }

    public ArrayList<Student> getAllStudent() {
        return studentDao.getAllStudent();
    }
}
