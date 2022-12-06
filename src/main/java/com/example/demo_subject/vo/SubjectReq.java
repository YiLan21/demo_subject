package com.example.demo_subject.vo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubjectReq {

	@JsonProperty("subNumber")
	private String subjectNumber;

	@JsonProperty("subName")
	private String subjectName;

	@JsonProperty("subDate")
	private int subjectDate;

	@JsonProperty("startTime")
	private int startTime;

	@JsonProperty("endTime")
	private int endTime;

	@JsonProperty("units")
	private int units;

	/* ================================================ */

	List<SubjectReq> list = new ArrayList<>();

	/* ================================================ */

	public SubjectReq() {

	}

	/* ================================================ */
	public List<SubjectReq> getList() {
		return list;
	}

	public void setList(List<SubjectReq> list) {
		this.list = list;
	}

	public String getSubjectNumber() {
		return subjectNumber;
	}

	public void setSubjectNumber(String subjectNumber) {
		this.subjectNumber = subjectNumber;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getSubjectDate() {
		return subjectDate;
	}

	public void setSubjectDate(int subjectDate) {
		this.subjectDate = subjectDate;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}
}
