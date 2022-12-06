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

	@Autowired // 第二步 @Autowired Service
	private StudentSubjectService studentSubjectService;

	

	@PostMapping(value = "/api/creatSubject")
	// 1.建立課程
	public SubjectRes creatSubject(@RequestBody SubjectReq req) {
		
		return studentSubjectService.creatSubject(req.getSubjectNumber(), req.getSubjectName(), req.getSubjectDate(),
				                                  req.getStartTime(), req.getEndTime(), req.getUnits());

	}
	
	
	@PostMapping(value = "/api/reviseSubject")
	// 2.更改課程內容 
	public SubjectRes reviseSubject(@RequestBody SubjectReq req) {
		
		return studentSubjectService.reviseSubject(req.getSubjectNumber(), req.getSubjectName(), req.getSubjectDate(),
			                       	               req.getStartTime(), req.getEndTime(), req.getUnits());

	}
	
	
	@PostMapping(value = "/api/deletSubject")
	//3.刪除課程
	public SubjectRes deletSubject(@RequestBody SubjectReq req) {
	
		return studentSubjectService.deletSubject(req.getSubjectNumber());

	}
	

	@PostMapping(value = "/api/createStudent")
	// 4.建立學生資料
	public StudentRes createStudent(@RequestBody StudentReq req) {
		
		return studentSubjectService.createStudent(req.getStudentNumber(), req.getStudentName());
	}
	
	
	@PostMapping(value = "/api/reviseStudentSubject")
	//5.學生新增課程 && 修改學生選課
	public StudentRes reviseStudentSubject(@RequestBody StudentReq req) {
		
		return studentSubjectService.reviseStudentSubject(req.getStudentNumber(), req.getSubjectNumber());

	}
	
	
	@PostMapping(value = "/api/deletStudentSubject")
	// 6.學生刪除選課
	public StudentRes deletStudentSubject(@RequestBody StudentReq req) {
		
		return studentSubjectService.deletStudentSubject(req.getStudentNumber(), req.getSubjectNumber());

	}

	
	@PostMapping(value = "/api/findByStuNumber")	
	// 7.用學生number尋找資訊
	public StudentRes findByStuNumber(@RequestBody StudentReq req) {
		
		return studentSubjectService.SearchStudentNumber(req.getStudentNumber());

	}
	
	
	@PostMapping(value = "/api/findBySubNumber") 
	//8.用課程代碼查詢課程資訊
	public SubjectRes findBySubNumber(@RequestBody SubjectReq req) {
	
		return studentSubjectService.searchSubNumber(req.getSubjectNumber());

	}


	@PostMapping(value = "/api/findBySubName")	
	// 9.用課程名稱查詢課程資訊
	public SubjectRes findBySubName(@RequestBody SubjectReq req) {

		return studentSubjectService.searchSubjectName(req.getSubjectName());

	}

	
}
