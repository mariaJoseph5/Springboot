package com.students.studentservice;

import com.students.dao.StudentRepository;
import com.students.model.Student;
import com.students.service.StudentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@SpringBootTest

class StudentServiceApplicationTests {
	@Autowired
	StudentRepository studentRepository;


	@Test
	@Order(1)
	@Rollback(value = false)
	public void saveStudentTest(){

		Student student = new Student( "Mandi", 27);

		studentRepository.save(student);

		Assertions.assertThat(student.getUsn()).isGreaterThan(0);
	}
	@Test
	@Order(2)
	public void getStudentTest(){

		Student student = studentRepository.findById(3).get();

		Assertions.assertThat(student.getUsn()).isEqualTo(3);

	}

	@Test
	@Order(3)
	public void getListOfStudentsTest(){

		List<Student> students = (List<Student>) studentRepository.findAll();

		Assertions.assertThat(students.size()).isGreaterThan(0);

	}

	@Test
	@Order(4)
	@Rollback(value = false)
	public void updateStudentTest(){

		Student student = studentRepository.findById(2).get();

		student.setMarks(82);

		Student studentUpdated =  studentRepository.save(student);

		Assertions.assertThat(studentUpdated.getMarks()).isEqualTo(82);

	}

	@Test
	@Order(5)
	@Rollback(value = false)
	public void deleteStudentTest(){

		Student student = studentRepository.findById(9).get();

		studentRepository.delete(student);


		Student student1 = null;

		Optional<Student> optionalStudent = studentRepository.findById(1);

		if(optionalStudent.isPresent()){
			student1 = optionalStudent.get();
		}

		Assertions.assertThat(student1).isNull();
	}

}
