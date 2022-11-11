package com.example.demo_subject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
	/*
	 * ��Ʈw�p�G�S����Ӫ����ؤ��i�b���B�s�W
	 * �Ϊ��٨S�Ψ�e�����ѱ�
	 * 
	 * */
	@Id
	@Column(name = "stu_number")
	private String stuNumber;
	@Column(name = "sub_number")
	private String subNumber;

	@Column(name = "stu_name")
	private String stuName;

	public Student() {

	}

	public Student(String stuName, String stuNumber, String subNumber) {
		this.stuName = stuName;
		this.stuNumber = stuNumber;
		this.subNumber = subNumber;

	}

	public Student( String stuNumber,String stuName) {
		this.stuNumber = stuNumber;
		this.stuName = stuName;
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

	public void setSubNumber(String subNumber) {
		this.subNumber = subNumber;
	}

}
