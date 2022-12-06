package com.example.demo_subject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "subject")
public class Subject {
	
	@Id
	@Column(name = "sub_number")
	private String subjectNumber;  //課程代碼
	
	@Column(name = "sub_name")
	private String subjectName;   //課程名稱
	
	@Column(name = "sub_date")
	private int subjectDate ;     //課程日期
	
	@Column(name = "start_time")
	private int startTime ;      //課程開始時間
	
	@Column(name = "end_time")
	private int endTime ;        //課程結束時間
	
	@Column(name = "units")
	private int units ;         //課程學分
	
	
	public Subject() {
		
	}
	
	public Subject(String subjectNumber,String subjectName,Integer subjectDate ,Integer startTime, Integer endTime ,Integer units) {
		this.subjectNumber = subjectNumber;
		this.subjectName =subjectName;
		this.subjectDate=subjectDate;
		this.startTime=startTime;
		this.endTime=endTime;
		this.units=units;
		
	}
	public Subject(String subjectName,Integer subjectDate ,Integer startTime, Integer endTime ,Integer units) {
		this.subjectName =subjectName;
		this.subjectDate=subjectDate;
		this.startTime=startTime;
		this.endTime=endTime;
		this.units=units;
		
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
