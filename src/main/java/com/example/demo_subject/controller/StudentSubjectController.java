package com.example.demo_subject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_subject.entity.Student;
import com.example.demo_subject.entity.Subject;
import com.example.demo_subject.service.StudentSubjectService;
import com.example.demo_subject.vo.StudentReq;
import com.example.demo_subject.vo.StudentRes;
import com.example.demo_subject.vo.SubjectReq;
import com.example.demo_subject.vo.SubjectRes;

@CrossOrigin
@RestController
public class StudentSubjectController {

	@Autowired // �ĤG�B @Autowired Service
	private StudentSubjectService studentSubjectService;

	

	@PostMapping(value = "/api/creatSubject")
	// 1.�إ߽ҵ{
	public SubjectRes creatSubject(@RequestBody SubjectReq req) {
		
		return studentSubjectService.creatSubject(req.getSubjectNumber(), req.getSubjectName(), req.getSubjectDate(),
				                                  req.getStartTime(), req.getEndTime(), req.getUnits());

	}
	
	
	@PostMapping(value = "/api/reviseSubject")
	// 2.���ҵ{���e 
	public SubjectRes reviseSubject(@RequestBody SubjectReq req) {
		
		return studentSubjectService.reviseSubject(req.getSubjectNumber(), req.getSubjectName(), req.getSubjectDate(),
			                       	               req.getStartTime(), req.getEndTime(), req.getUnits());

	}
	
	
	@PostMapping(value = "/api/deletSubject")
	//3.�R���ҵ{
	public SubjectRes deletSubject(@RequestBody SubjectReq req) {
	
		return studentSubjectService.deletSubject(req.getSubjectNumber());

	}
	

	@PostMapping(value = "/api/createStudent")
	// 4.�إ߾ǥ͸��
	public StudentRes createStudent(@RequestBody StudentReq req) {
		
		return studentSubjectService.createStudent(req.getStudentNumber(), req.getStudentName());
	}
	
	
	@PostMapping(value = "/api/reviseStudentSubject")
	//5.�ǥͷs�W�ҵ{ && �ק�ǥͿ��
	public StudentRes reviseStudentSubject(@RequestBody StudentReq req) {
		
		return studentSubjectService.reviseStudentSubject(req.getStudentNumber(), req.getSubjectNumber());

	}
	
	
	@PostMapping(value = "/api/deletStudentSubject")
	// 6.�ǥͧR�����
	public StudentRes deletStudentSubject(@RequestBody StudentReq req) {
		
		return studentSubjectService.deletStudentSubject(req.getStudentNumber(), req.getSubjectNumber());

	}

	
	@PostMapping(value = "/api/findByStuNumber")	
	// 7.�ξǥ�number�M���T
	public StudentRes findByStuNumber(@RequestBody StudentReq req) {
		
		return studentSubjectService.SearchStudentNumber(req.getStudentNumber());

	}
	
	
	@PostMapping(value = "/api/findBySubNumber") 
	//8.�νҵ{�N�X�d�߽ҵ{��T
	public SubjectRes findBySubNumber(@RequestBody SubjectReq req) {
	
		return studentSubjectService.searchSubNumber(req.getSubjectNumber());

	}


	@PostMapping(value = "/api/findBySubName")	
	// 9.�νҵ{�W�٬d�߽ҵ{��T
	public SubjectRes findBySubName(@RequestBody SubjectReq req) {

		return studentSubjectService.searchSubjectName(req.getSubjectName());

	}

	
}
