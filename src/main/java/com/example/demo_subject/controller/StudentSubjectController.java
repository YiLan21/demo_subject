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

	@Autowired // �ĤG�B @Autowired Service
	private StudentSubjectService studentSubjectService;

	
	@PostMapping(value = "/api/SubjectInfo")// 1.���o�����ҵ{��T
	public List<Subject> getSubjectInfo() {
//		List<Subject> list = new ArrayList<>();
//		list = studentSubjectService.getSubjectInfo();
//		return list;
		return studentSubjectService.getSubjectInfo();
	}

	@PostMapping(value = "/api/creatSubject")// 2.�إ߽ҵ{
	public SubjectRes creatSubject(@RequestBody SubjectReq req) {
//		SubjectRes sbres = new SubjectRes();
//		sbres = studentSubjectService.creatSubject(req.getSubNumber(), req.getSubName(), req.getSubDate(),
//				req.getStartTime(), req.getEndTime(), req.getUnits());
//		return sbres;
		return studentSubjectService.creatSubject(req.getSubNumber(), req.getSubName(), req.getSubDate(),
				req.getStartTime(), req.getEndTime(), req.getUnits());

	}
	@PostMapping(value = "/api/reviseSubject")// 3.���ҵ{���e 
	public SubjectRes reviseSubject(@RequestBody SubjectReq req) {
		return studentSubjectService.reviseSubject(req.getSubNumber(), req.getSubName(), req.getSubDate(),
				req.getStartTime(), req.getEndTime(), req.getUnits());

	}
	@PostMapping(value = "/api/deletSubject")//4.�R���ҵ{
	public SubjectRes deletSubject(@RequestBody SubjectReq req) {

//		SubjectRes sbres = new SubjectRes();
//		sbres = studentSubjectService.deletSubject(req.getSubNumber());
//		return sbres;
		return studentSubjectService.deletSubject(req.getSubNumber());

	}

	@PostMapping(value = "/api/createStudent")// 5.�إ߾ǥ͸��
	public StudentRes createStudent(@RequestBody StudentReq req) {
//		StudentRes res = studentSubjectService.createStudent(req.getStuName(), req.getStuNumber());
		return studentSubjectService.createStudent(req.getStuNumber(), req.getStuName());
	}
	
	@PostMapping(value = "/api/reviseStudentSubject")//7.�ǥͷs�W�ҵ{ && �ק�ǥͿ��
	public StudentRes reviseStudentSubject(@RequestBody StudentReq req) {
//		StudentRes res = studentSubjectService.creatStudentSubject(req.getStuNumber(), req.getSubNumber());
//		return res;
		return studentSubjectService.reviseStudentSubject(req.getStuNumber(), req.getSubNumber());

	}
	@PostMapping(value = "/api/deletStudentSubject")// 8.�ǥͧR�����
	public StudentRes deletStudentSubject(@RequestBody StudentReq req) {

//		StudentRes res = new StudentRes();
//		res = studentSubjectService.deletStudentSubject(req.getStuNumber(), req.getSubNumber());
//		return res;
		return studentSubjectService.deletStudentSubject(req.getStuNumber(), req.getSubNumber());

	}
	@PostMapping(value = "/api/studentInfo")//9.�d�߾ǥͿ�Ҹ��
	public List<Student> getstudentInfo() {
//		List<Student> list = studentSubjectService.getStudentInfo();
//		return list;
		return studentSubjectService.getStudentInfo();

	}

	@PostMapping(value = "/api/findByStuNumber")	// 10.�ξǥ�number�M���T
	public StudentRes findByStuNumber(@RequestBody StudentReq req) {
		StudentRes sbres = studentSubjectService.findByStuNumber(req.getStuNumber());
		return sbres;

	}
	@PostMapping(value = "/api/findBySubNumber") //11.�νҵ{�N�X�d�߽ҵ{��T
	public SubjectRes findBySubNumber(@RequestBody SubjectReq req) {
//		SubjectRes sbres = studentSubjectService.findBySubNumber(req.getSubNumber());
//		return sbres;
		return studentSubjectService.findBySubNumber(req.getSubNumber());

	}

	

	@PostMapping(value = "/api/findBySubName")	// 12.�νҵ{�W�٬d�߽ҵ{��T
	public SubjectRes findBySubName(@RequestBody SubjectReq req) {

		SubjectRes sbres = studentSubjectService.findBySubName(req.getSubName());
		return sbres;

	}

	
}
