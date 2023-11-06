package com.example.model;

import com.example.entity.Student;

import java.util.ArrayList;

public interface StudentDao {

    public void createStudent(Student student);

    public ArrayList<Student> getAllStudent();
}
