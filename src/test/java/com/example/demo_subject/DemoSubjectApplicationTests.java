package com.example.demo_subject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo_subject.service.StudentSubjectService;
import com.example.demo_subject.vo.StudentRes;

//@SpringBootTest
class DemoSubjectApplicationTests {

	@Autowired
	private StudentSubjectService studentSubjectService;
	
	@Test
	void contextLoads() {
//		StudentRes res = studentSubjectService.creatStudentSubject("ZZ001", "a011,a012");
		String str = "A, B, C";
		System.out.println(str.contains("A"));
	}

}
