package com.students.service;

import com.students.dao.StudentRepository;
import com.students.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getStudentsList() {
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Student student, int usn) {
        Student student1 = studentRepository.findById(usn).get();
        student1.setMarks(student.getMarks());
        student1.setName(student.getName());
        return studentRepository.save(student1);
    }

    @Override
    public String deleteStudentByUsn(int usn) {
        studentRepository.deleteById(usn);
        return "Student deleted";
    }

    @Override
    public Optional<Student> getStudentByUsn(int usn) {
        return studentRepository.findById(usn);
    }
}
