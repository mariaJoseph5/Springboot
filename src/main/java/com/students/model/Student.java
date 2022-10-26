package com.students.model;

import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int usn;
    private String name;

    private int marks;


    public Student(){

    }

    @Override
    public String toString() {
        return "Student{" +
                "usn=" + usn +
                ", name='" + name + '\'' +
                ", marks=" + marks +
                '}';
    }

    public int getUsn() {
        return usn;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public void setUsn(int usn) {
        this.usn = usn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student(String name, int marks){
        this.name = name;
        this.marks = marks;
    }
    public Student(int usn, String name, int marks){
        this.name = name;
        this.marks = marks;
        this.usn = usn;
    }
}
