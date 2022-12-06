package com.example.demo_subject.vo;

//import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentReq {

	@JsonProperty("stuName")
	private String studentName;
	@JsonProperty("stuNumber")
	private String studentNumber;
	@JsonProperty("subNumber")
	private String subjectNumber;
	
	/* ================================================ */
	
	public StudentReq() {

	}
	/* ================================================ */
	
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getSubjectNumber() {
		return subjectNumber;
	}

	public void setSubjectNumber(String subjectNumber) {
		this.subjectNumber = subjectNumber;
	}
}