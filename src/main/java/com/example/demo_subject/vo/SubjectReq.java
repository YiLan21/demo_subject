package com.example.demo_subject.vo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;


public class SubjectReq {

	@JsonProperty("subNumber")
	private String subNumber;
	@JsonProperty("subName")
	private String subName;
	@JsonProperty("subDate")
	private int subDate;
	@JsonProperty("startTime")
	private int startTime;
	@JsonProperty("endTime")
	private int endTime;
	@JsonProperty("units")
	private int units;
	
    List<SubjectReq>list = new ArrayList<>();
    
	public SubjectReq() {

	}

	public List<SubjectReq> getList() {
		return list;
	}

	public void setList(List<SubjectReq> list) {
		this.list = list;
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
