package com.example.demo_subject.service;


import com.example.demo_subject.vo.StudentRes;
import com.example.demo_subject.vo.SubjectRes;

public interface StudentSubjectService {

	//建立(9)個方法執行專案 

	// 1.建立課程 課程資訊 	
	public SubjectRes creatSubject(String subNumber, String subName, Integer subDate,Integer startTime,Integer endTime,Integer units);

	// 2.更改課程資訊         	
	public SubjectRes reviseSubject(String subNumber, String subName, Integer subDate,Integer startTime, Integer endTime,	Integer units);

	// 3.刪除課程          
	public SubjectRes deletSubject(String subNumber);

	// 4.建立學生資料         	
	public StudentRes createStudent(String stuNumber, String stuName);

	// 5.選課新增 && 修改     
	//   首次選課也可以執行   
	public StudentRes reviseStudentSubject(String stuNumber, String subNumber);

	// 6.學生刪除選課         	
	//   限定一次只刪除一堂課程 
	public StudentRes deletStudentSubject(String stuNumber, String subNumber);
	
	// 7.學號搜尋學生資訊    
	public StudentRes SearchStudentNumber(String stuNumber);

	// 8.課程代碼搜尋課程資訊 // api: /api/findBySubNumber
	public SubjectRes searchSubNumber(String subNumber);

	// 9.課程名稱搜尋課程資訊 // api: /api/findBySubName
	public SubjectRes searchSubjectName(String subName);
	
	

	
	/*總體備註 :
	 * 1. 在Res 不用寫入所有資訊 可以只帶入 private Student student ;
	 * 2. 在get資料庫前 ，就要先判斷Optional 有沒有帶入值 ，(.isPresert )
	 * 3. 刪除Set List Map 都是用 remove 
	 * 4. 
	 * */
}
