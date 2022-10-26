package com.students.controller;

import com.students.StudentApplication;
import com.students.model.Student;
import com.students.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/list")
    public List<Student> getStudentsList(){
        return studentService.getStudentsList();
    }
    @GetMapping("/{id}")
    public Optional<Student> getStudentByUsn(@PathVariable("id") int usn){
        return studentService.getStudentByUsn(usn);
    }
    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }
    @PutMapping("/update/{id}")
    public Student updateStudent(@RequestBody Student student, @PathVariable("id") int usn){
        return studentService.updateStudent(student, usn);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteStudentByUsn(@PathVariable("id") int usn){
        return studentService.deleteStudentByUsn(usn);
    }
}
