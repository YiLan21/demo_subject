package com.example.demo_subject.vo;

import java.util.List;
import java.util.Optional;

import com.example.demo_subject.entity.Student;
import com.example.demo_subject.entity.Subject;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentRes {

	private String stuName;
	private String stuNumber;
	private String subNumber;
	private String units;
	private String Message;
	private Student student;

	/* ================================================ */
	private List<Student> studentList;
	private List<Subject> subjectList;

	/* ================================================ */

	public StudentRes() {

	}

	public StudentRes(Student student, String Message) {

		this.student = student;
		this.Message = Message;
	}

	public StudentRes(String stuName, String stuNumber) {

		this.stuName = stuNumber;
		this.stuNumber = stuNumber;
	}

	public StudentRes(String stuName, String stuNumber, String subNumber) {
		
		this.stuName = stuNumber;
		this.stuNumber = stuNumber;
		this.subNumber = subNumber;
	}

	public StudentRes(String stuName, String stuNumber, String subNumber, String units) {
		
		this.stuName = stuNumber;
		this.stuNumber = stuNumber;
		this.subNumber = subNumber;
		this.units = units;
	}

	/* ================================================ */

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public void setSubNumber(String subNumber) {
		this.subNumber = subNumber;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getStuNumber() {
		return stuNumber;
	}

	public void setStuNumber(String stuNumber) {
		this.stuNumber = stuNumber;
	}

	public String getSubNumber() {
		return subNumber;
	}

	public void SubNumber(String subNumber) {
		this.subNumber = subNumber;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public List<Student> getList() {
		return studentList;
	}

	public void setList(List<Student> list) {
		this.studentList = list;
	}

	public void setList(Optional<Subject> subjectOp) {
		// TODO Auto-generated method stub

	}

	/* =====================¥H¤WGET SET=========================== */

}
