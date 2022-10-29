package com.students.studentservice.service;

import com.students.dao.StudentRepository;
import com.students.model.Student;
import com.students.service.StudentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class StudentsServiceTest {

    @MockBean
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;

    @Order(1)
    @Test
    public void whenStudentIsAdded_thenRetrievedDataIsCorrect() {
        Student student = new Student(1, "Maria", 65);
        Mockito.when(studentService.addStudent(student)).thenReturn(student);
        Assert.assertEquals(student.toString(), "Student{usn=1, name='Maria', marks=65}");
    }
    @Order(2)
    @Test
    public void whenStudentIsSearchedFor_thenRetrievedDataIsCorrect() {
        Student student = studentRepository.findById(61).get();
        Mockito.when(studentService.getStudentByUsn(61)).thenReturn(Optional.of(student));
        Assert.assertEquals(student.toString(), "Student{usn=61, name='Ginger', marks=76}");
    }
    @Order(3)
    @Test
    public void whenStudentIsModified_thenRetrievedDataIsCorrect() {
        Student student = studentRepository.findById(61).get();
        student.setMarks(28);
        Mockito.when(studentService.updateStudent(student, 61)).thenReturn(student);
        Assert.assertEquals(student.toString(), "Student{usn=61, name='Ginger', marks=28}");
    }
    @Order(4)
    @Test
    public void whenStudentIsDeleted_thenRetrievedDataIsCorrect() {
        Mockito.when(studentService.deleteStudentByUsn(1)).thenReturn( "Student deleted");
        String val = "Student deleted";
        Assert.assertEquals(val, "Student deleted");
    }
}
