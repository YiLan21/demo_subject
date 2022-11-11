package com.example.demo_subject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
public class StudentSubjectController {

	@Autowired // 第二步 @Autowired Service
	private StudentSubjectService studentSubjectService;

	
	@PostMapping(value = "/api/SubjectInfo")// 1.取得全部課程資訊
	public List<Subject> getSubjectInfo() {
//		List<Subject> list = new ArrayList<>();
//		list = studentSubjectService.getSubjectInfo();
//		return list;
		return studentSubjectService.getSubjectInfo();
	}

	@PostMapping(value = "/api/creatSubject")// 2.建立課程
	public SubjectRes creatSubject(@RequestBody SubjectReq req) {
//		SubjectRes sbres = new SubjectRes();
//		sbres = studentSubjectService.creatSubject(req.getSubNumber(), req.getSubName(), req.getSubDate(),
//				req.getStartTime(), req.getEndTime(), req.getUnits());
//		return sbres;
		return studentSubjectService.creatSubject(req.getSubNumber(), req.getSubName(), req.getSubDate(),
				req.getStartTime(), req.getEndTime(), req.getUnits());

	}
	@PostMapping(value = "/api/reviseSubject")// 3.更改課程內容 
	public SubjectRes reviseSubject(@RequestBody SubjectReq req) {
		return studentSubjectService.reviseSubject(req.getSubNumber(), req.getSubName(), req.getSubDate(),
				req.getStartTime(), req.getEndTime(), req.getUnits());

	}
	@PostMapping(value = "/api/deletSubject")//4.刪除課程
	public SubjectRes deletSubject(@RequestBody SubjectReq req) {

//		SubjectRes sbres = new SubjectRes();
//		sbres = studentSubjectService.deletSubject(req.getSubNumber());
//		return sbres;
		return studentSubjectService.deletSubject(req.getSubNumber());

	}

	@PostMapping(value = "/api/createStudent")// 5.建立學生資料
	public StudentRes createStudent(@RequestBody StudentReq req) {
//		StudentRes res = studentSubjectService.createStudent(req.getStuName(), req.getStuNumber());
		return studentSubjectService.createStudent(req.getStuNumber(), req.getStuName());
	}
	
	@PostMapping(value = "/api/reviseStudentSubject")//7.學生新增課程 && 修改學生選課
	public StudentRes reviseStudentSubject(@RequestBody StudentReq req) {
//		StudentRes res = studentSubjectService.creatStudentSubject(req.getStuNumber(), req.getSubNumber());
//		return res;
		return studentSubjectService.reviseStudentSubject(req.getStuNumber(), req.getSubNumber());

	}
	@PostMapping(value = "/api/deletStudentSubject")// 8.學生刪除選課
	public StudentRes deletStudentSubject(@RequestBody StudentReq req) {

//		StudentRes res = new StudentRes();
//		res = studentSubjectService.deletStudentSubject(req.getStuNumber(), req.getSubNumber());
//		return res;
		return studentSubjectService.deletStudentSubject(req.getStuNumber(), req.getSubNumber());

	}
	@PostMapping(value = "/api/studentInfo")//9.查詢學生選課資料
	public List<Student> getstudentInfo() {
//		List<Student> list = studentSubjectService.getStudentInfo();
//		return list;
		return studentSubjectService.getStudentInfo();

	}

	@PostMapping(value = "/api/findByStuNumber")	// 10.用學生number尋找資訊
	public StudentRes findByStuNumber(@RequestBody StudentReq req) {
		StudentRes sbres = studentSubjectService.findByStuNumber(req.getStuNumber());
		return sbres;

	}
	@PostMapping(value = "/api/findBySubNumber") //11.用課程代碼查詢課程資訊
	public SubjectRes findBySubNumber(@RequestBody SubjectReq req) {
//		SubjectRes sbres = studentSubjectService.findBySubNumber(req.getSubNumber());
//		return sbres;
		return studentSubjectService.findBySubNumber(req.getSubNumber());

	}

	

	@PostMapping(value = "/api/findBySubName")	// 12.用課程名稱查詢課程資訊
	public SubjectRes findBySubName(@RequestBody SubjectReq req) {

		SubjectRes sbres = studentSubjectService.findBySubName(req.getSubName());
		return sbres;

	}

	
}
