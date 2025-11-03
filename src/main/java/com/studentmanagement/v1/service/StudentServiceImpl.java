package com.studentmanagement.v1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.studentmanagement.v1.entity.Student;
import com.studentmanagement.v1.repository.StudentRepository;
import com.studentmanagement.v1.exception.*;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Student addStudent(Student student) {
        return repository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Student existing = getStudentById(id);
        existing.setName(student.getName());
        existing.setEmail(student.getEmail());
        existing.setCourse(student.getCourse());
        existing.setAge(student.getAge());
        return repository.save(existing);
    }

    @Override
    public void deleteStudent(Long id) {
        Student existing = getStudentById(id);
        repository.delete(existing);
    }
}