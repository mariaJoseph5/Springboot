package com.students.service;

import com.students.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Student addStudent(Student student);
    List<Student> getStudentsList();
    Student updateStudent(Student student, int usn);
    String deleteStudentByUsn(int usn);
    Optional<Student> getStudentByUsn(int usn);
}
