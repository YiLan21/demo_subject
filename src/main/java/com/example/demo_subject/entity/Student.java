package com.example.demo_subject.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
	
	@Id
	@Column(name = "stu_number")
	private String studentNumber; //�ǥ;Ǹ�
	
	@Column(name = "sub_number")
	private String subjectNumber; //�ҵ{�N�X

	@Column(name = "stu_name")   
	private String studentName;   //�ǥͩm�W

	public Student() {

	}

	public Student(String studentName, String stdentNumber, String subjectNumber) {
		this.studentName = studentName;
		this.studentNumber = stdentNumber;
		this.subjectNumber = subjectNumber;

	}

	public Student( String studentNumber,String studentName) {
		this.studentNumber = studentNumber;
		this.studentName = studentName;
	}

	public String getStuName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStuNumber(String stuNumber) {
		this.studentNumber = stuNumber;
	}

	public String getSubNumber() {
		return subjectNumber;
	}

	public void setSubjectNumber(String subjectNumber) {
		this.subjectNumber = subjectNumber;
	}

}
