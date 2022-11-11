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
	private String subNumber;
	@Column(name = "sub_name")
	private String subName;
	@Column(name = "sub_date")
	private int subDate ;
	@Column(name = "start_time")
	private int startTime ;
	@Column(name = "end_time")
	private int endTime ;
	@Column(name = "units")
	private int units ;
	
	public Subject() {
		
	}
	
	public Subject(String subNumber,String subName,Integer subDate ,Integer startTime, Integer endTime ,Integer units) {
		this.subNumber = subNumber;
		this.subName =subName;
		this.subDate=subDate;
		this.startTime=startTime;
		this.endTime=endTime;
		this.units=units;
		
	}
	public Subject(String subName,Integer subDate ,Integer startTime, Integer endTime ,Integer units) {
		this.subName =subName;
		this.subDate=subDate;
		this.startTime=startTime;
		this.endTime=endTime;
		this.units=units;
		
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

	public int getSubDate() {
		return subDate;
	}

	public void setSubDate(int subDate) {
		this.subDate = subDate;
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
