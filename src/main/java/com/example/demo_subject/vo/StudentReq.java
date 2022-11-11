package com.example.demo_subject.vo;

import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentReq {

	@JsonProperty("stuName")
	private String stuName;
	
	@JsonProperty("stuNumber")
	private String stuNumber;
	@JsonProperty("subNumber")
	private String subNumber;
	

	public StudentReq() {

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