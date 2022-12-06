package com.example.demo_subject.vo;

import java.util.List;

import com.example.demo_subject.entity.Subject;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class SubjectRes {

	private String subNumber;
	private String subName;
	private Integer subDate;
	private Integer startTime;
	private Integer endTime;
	private Integer units;
	private String Message;
	private Subject subject;

	/* =================================================== */

	private List<Subject> list;

	/* ==================================================== */
	public SubjectRes() {

	}

	public SubjectRes(Subject subject) {

		this.subject = subject;
	}

	public SubjectRes(Subject subject, String Message) {

		this.subject = subject;
		this.Message = Message;
	}

	public SubjectRes(String subNumber, String subName, Integer subDate, Integer startTime, Integer endTime,
			Integer units) {

		this.subNumber = subNumber;
		this.subName = subName;
		this.subDate = subDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.units = units;
	}

	/* ================================================ */

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public String getSubNumber() {
		return subNumber;
	}

	public void setSubNumber(String subNumber) {
		this.subNumber = subNumber;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public Integer getSubDate() {
		return subDate;
	}

	public void setSubDate(Integer subDate) {
		this.subDate = subDate;
	}

	public Integer getStartTime() {
		return startTime;
	}

	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	public Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}

	public Integer getUnits() {
		return units;
	}

	public void setUnits(Integer units) {
		this.units = units;
	}

	public List<Subject> getList() {
		return list;
	}

	public void setList(List<Subject> list) {
		this.list = list;
	}

}
