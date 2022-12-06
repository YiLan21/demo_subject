package com.example.demo_subject.service;


import com.example.demo_subject.vo.StudentRes;
import com.example.demo_subject.vo.SubjectRes;

public interface StudentSubjectService {

	//�إ�(9)�Ӥ�k����M�� 

	// 1.�إ߽ҵ{ �ҵ{��T 	
	public SubjectRes creatSubject(String subNumber, String subName, Integer subDate,Integer startTime,Integer endTime,Integer units);

	// 2.���ҵ{��T         	
	public SubjectRes reviseSubject(String subNumber, String subName, Integer subDate,Integer startTime, Integer endTime,	Integer units);

	// 3.�R���ҵ{          
	public SubjectRes deletSubject(String subNumber);

	// 4.�إ߾ǥ͸��         	
	public StudentRes createStudent(String stuNumber, String stuName);

	// 5.��ҷs�W && �ק�     
	//   ������Ҥ]�i�H����   
	public StudentRes reviseStudentSubject(String stuNumber, String subNumber);

	// 6.�ǥͧR�����         	
	//   ���w�@���u�R���@��ҵ{ 
	public StudentRes deletStudentSubject(String stuNumber, String subNumber);
	
	// 7.�Ǹ��j�M�ǥ͸�T    
	public StudentRes SearchStudentNumber(String stuNumber);

	// 8.�ҵ{�N�X�j�M�ҵ{��T // api: /api/findBySubNumber
	public SubjectRes searchSubNumber(String subNumber);

	// 9.�ҵ{�W�ٷj�M�ҵ{��T // api: /api/findBySubName
	public SubjectRes searchSubjectName(String subName);
	
	

	
	/*�`��Ƶ� :
	 * 1. �bRes ���μg�J�Ҧ���T �i�H�u�a�J private Student student ;
	 * 2. �bget��Ʈw�e �A�N�n���P�_Optional ���S���a�J�� �A(.isPresert )
	 * 3. �R��Set List Map ���O�� remove 
	 * 4. 
	 * */
}
