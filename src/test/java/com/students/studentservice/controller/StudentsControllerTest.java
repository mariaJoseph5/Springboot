package com.students.studentservice.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.students.model.Student;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


public class StudentsControllerTest extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
    @Test
    public void getStudentsList() throws Exception {
        String uri = "/students/list";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Student[] studentList = super.mapFromJson(content, Student[].class);
        assertTrue(studentList.length > 0);
    }
    @Test
    public void createStudent() throws Exception {
        String uri = "/students/add";
        Student student = new Student();
        student.setName("Ginger");
        student.setMarks(76);
        String inputJson = super.mapToJson(student);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "{\"usn\":108,\"name\":\"Ginger\",\"marks\":76}");
    }
    @Test
    public void updateStudent() throws Exception {
        String uri = "/students/update/62";
        Student student = new Student();
        student.setName("Aria");
        String inputJson = super.mapToJson(student);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "{\"usn\":62,\"name\":\"Aria\",\"marks\":0}");
    }
    @Test
    public void deleteStudent() throws Exception {
        String uri = "/students/delete/104";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Student deleted");
    }
}
