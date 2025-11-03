package com.studentmanagement.v1.service;

import java.util.List;

import com.studentmanagement.v1.entity.Student;

public interface StudentService {
    Student addStudent(Student student);
    List<Student> getAllStudents();
    Student getStudentById(Long id);
    Student updateStudent(Long id, Student student);
    void deleteStudent(Long id);
}