package com.students.studentservice.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import com.students.dao.StudentRepository;
import com.students.model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    private Student student;

    @BeforeEach
    public void setUp()
    {
        student = new Student(6,"Val",67);
    }

    @Test
    public void givenStudentObject_whenSave_thenReturnSavedStudent() {
        Student savedStudent = studentRepository.save(student);
        assertThat(savedStudent).isNotNull();
        assertThat(savedStudent.getUsn()).isGreaterThan(0);
    }

    @Test
    public void givenStudentsList_whenFindAll_thenReturnStudentsList() {
        Student student1 = new Student(1, "Pansy", 56);
        Student student2 = new Student(2, "Lily", 12);
        Student student3 = new Student(3, "Monica", 78);
        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
        List<Student> students = (List<Student>) studentRepository.findAll();
        assertThat(students).isNotNull();
        assertThat(students.size()).isEqualTo(13);
    }

    @Test
    public void givenStudent_whenFindById_thenReturnStudent() {
        studentRepository.save(student);
        Student foundStudent = studentRepository.findById(61).get();
        assertThat(foundStudent).isNotNull();
        assertThat(foundStudent.getUsn()).isEqualTo(61);
    }

    @Test
    public void givenStudent_whenUpdate_thenReturnStudent() {
        studentRepository.save(student);
        Student foundStudent = studentRepository.findById(61).get();
        foundStudent.setMarks(12);
        Student updatedStudent = studentRepository.save(foundStudent);
        assertThat(updatedStudent).isNotNull();
        assertThat(updatedStudent.getMarks()).isEqualTo(12);
    }

    @Test
    public void givenStudent_whenDeleteById_thenRemoveStudent() {
        Student savedStudent = studentRepository.save(student);
        studentRepository.deleteById(savedStudent.getUsn());
        Optional<Student> optionalStudent = studentRepository.findById(savedStudent.getUsn());
        assertThat(optionalStudent).isEmpty();
    }

    @AfterEach
    public void tearDown()
    {
        student = null;
    }

}
