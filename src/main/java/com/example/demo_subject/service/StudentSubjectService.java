package com.example.demo_subject.service;

import java.util.List;
import java.util.Optional;

import com.example.demo_subject.entity.Student;
import com.example.demo_subject.entity.Subject;
import com.example.demo_subject.vo.StudentRes;
import com.example.demo_subject.vo.SubjectRes;

public interface StudentSubjectService {

	public List<Subject> getSubjectInfo();
	// 1.取得全部課程資訊
	// api: /api/SubjectInfo

	public SubjectRes creatSubject(String subNumber, String subName, Integer subDate,Integer startTime, Integer endTime,
			Integer units);
	// 2.建立課程 課程資訊，並且建立防呆
	// 問題1.可以修改，但是是直接覆蓋原本的課程資料 (不確定這樣是否算是修改)
	// 問題2.開始的時間跟結束的時間是使用int去定義他而不是用DATE 解決 :改用Integer 
	//
	// api: /api/creatSubject

	public SubjectRes reviseSubject(String subNumber, String subName, Integer subDate, Integer startTime, Integer endTime,
			Integer units);
	// 3.更改課程內容
	//    /api/reviseSubject
	//修改list /Set /Map 移除資料使用remove

	public SubjectRes deletSubject(String subNumber);
	// 4.刪除課程
	// 問題1. 動作完成後subNumber一樣存在 但是其他資料歸零
	// A:紹豪幫忙已經解決 ，將後面的save關掉即可
	//
	// api: /api/deletSubject

	public StudentRes createStudent(String stuNumber, String stuName);
	// 5.建立學生資料
	// 防呆查詢
	// api: /api/createStudent


	public StudentRes reviseStudentSubject(String stuNumber, String subNumber);
	// 7.學生新增課程 && 修改學生選課
	// 想法--> 建立學生選課 -->輸入值 學生號碼 跟所選的課程 兩個輸入 -->回傳會是 Res中所包含的 訊息 跟list
	// 其中防呆1.輸入值要符合資料庫中有包含的課程代碼 2.上課時間不可衝堂 3.學分數不得超過十學分 4.不可選重複的課程
	// 問題1.無法找到對應資料庫 //解決:Dao 建立findbyStudent(String subNumber ) -->subNumber
	// 要跟by後面的一樣
	// 問題2.無法寫入第二門課 //目前解決方法 -> String subNumber 改成 List<String> subNumberList
	// 問題3.無法對比名字是否相同 會一直跳過 //解決 :因為字串對比要使.equalsIgnoreCase 原本使用 ==
	// 問題4.如首次在此處輸入課程 ，那寫入課程後在資料庫會顯示 {" ,a01"} //目前解決想法 增設一個首次輸入的api 強制資料庫中有資料，解決
	// 問題5.使用 -->if (!subjectDao.existsById(subNumber))可以節省再迴圈
	// 問題
	// api: /api/creatStudentSubject

	public StudentRes deletStudentSubject(String stuNumber, String subNumber);
	// 8.學生刪除選課 
	// api: /api/deletStudentSubject

	public List<Student> getStudentInfo();
	// 9.查詢學生選課資料
	// 問題1.無法完成此動作 但是在介面層面是沒有報錯 解決
	// api: /api/studentInfo
	
	public StudentRes findByStuNumber(String stuNumber);
	// 10.用學生number尋找資訊
	// 問題1. 不同資料庫的class不同 無法寫入//解決:在學生的entity建立一個list<subject>
	// 問題2. 只能顯示一筆list //目前想法 :","切割的方法
	// 問題3.
	// api: /api/findByStuNumber

	public SubjectRes findBySubNumber(String subNumber);
//	public List<Subject>  findBySubNumber(String subNumber);
	// 11.用課程代碼查詢課程資訊
	// api: /api/findBySubNumber

	public SubjectRes findBySubName(String subName);
//	public List<Subject>  findBySubName(String subName);
	// 12.用課程名稱查詢課程資訊
	// 同名稱的課程會有多個所以回傳要用list
	// SubjectRes 中建立list<Subject>
	// api: /api/findBySubName
	
	

	
	/*總體備註 :
	 * 1. 在Res 不用寫入所有資訊 可以只帶入 private Student student ;
	 * 2. 在get資料庫前 ，就要先判斷Optional 有沒有帶入值 ，(.isPresert )
	 * 3. 刪除Set List Map 都是用 remove 
	 * 4. 
	 * */
}
