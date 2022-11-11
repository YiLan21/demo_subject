package com.example.demo_subject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo_subject.constants.StudentSubjectRtnCode;
import com.example.demo_subject.entity.Student;
import com.example.demo_subject.entity.Subject;
import com.example.demo_subject.respository.StudentDao;
import com.example.demo_subject.respository.SubjectDao;
import com.example.demo_subject.vo.StudentRes;
import com.example.demo_subject.vo.SubjectRes;

@Service
public class StudentSubjectServiceImpl implements StudentSubjectService {

	@Autowired
	private StudentDao studentDao;
	@Autowired
	private SubjectDao subjectDao;

	// 1.取得全部課程資訊
	@Override
	public List<Subject> getSubjectInfo() {
		List<Subject> list = subjectDao.findAll();
		return list;
	}

	// 2.建立課程
	@Override
	public SubjectRes creatSubject(String subNumber, String subName, Integer subDate, Integer startTime,
			Integer endTime, Integer units) {
		SubjectRes res = new SubjectRes();
		if (!StringUtils.hasText(subNumber) || !StringUtils.hasText(subName)) {
//			res.setMessage("輸入資訊錯誤");
			res.setMessage(StudentSubjectRtnCode.CREAT_REQIRED.getMessage());
			return res;
		}
		if (!StringUtils.hasText(subName)) {
//			res.setMessage("課程名稱錯誤");
			res.setMessage(StudentSubjectRtnCode.CLASS_REQIRED.getMessage());
			return res;
		}
		if (subDate > 6 || subDate < 1) {
//			res.setMessage("輸入星期格式錯誤");
			res.setMessage(StudentSubjectRtnCode.DATE_REQIRED.getMessage());
			return res;
		}
		if (startTime > 16 || startTime < 9 || startTime >= endTime || startTime == null) {
//			res.setMessage("上課時間錯誤");
			res.setMessage(StudentSubjectRtnCode.DATE_REQIRED.getMessage());
			return res;
		}
		if (endTime > 18 || endTime < 11 || endTime == null) {
//			res.setMessage("結束時間錯誤");
			res.setMessage(StudentSubjectRtnCode.DATE_REQIRED.getMessage());
			return res;
		}

		if (units > 3 || units < 1 || units == null) {
//			res.setMessage("學分錯誤");
			res.setMessage(StudentSubjectRtnCode.UNITS_REQIRED.getMessage());
			return res;
		}
		Optional<Subject> subOp = subjectDao.findById(subNumber); //
		if (subOp.isPresent()) {
//			res.setMessage("此課程已存在");
			res.setMessage(StudentSubjectRtnCode.HASCLASS_REQIRED.getMessage());
			return res;
		}
		Subject subject = new Subject(subNumber, subName, subDate, startTime, endTime, units);
		subjectDao.save(subject);
		res.setSubject(subject);
//		res.setMessage("成功建立");
		res.setMessage(StudentSubjectRtnCode.SUCCESSFUL.getMessage());

		return res;
	}

	// 3.更改課程內容 //
	public SubjectRes reviseSubject(String subNumber, String subName, Integer subDate, Integer startTime,
			Integer endTime, Integer units) {

		SubjectRes res = new SubjectRes();
		if (!StringUtils.hasText(subNumber) || !StringUtils.hasText(subName)) {
//			res.setMessage("輸入資訊錯誤");
			res.setMessage(StudentSubjectRtnCode.CREAT_REQIRED.getMessage());
			return res;
		}

		if (!StringUtils.hasText(subNumber)) {
//			res.setMessage("課程代碼格式錯誤");
			res.setMessage(StudentSubjectRtnCode.DUBCLASS_REQIRED.getMessage());
			return res;
		}
		Optional<Subject> subOp = subjectDao.findById(subNumber); //
		if (!subOp.isPresent()) {// 找到學生資料.if(!)學生的資訊=跳出if(true)學生的資訊keep going
//			res.setMessage("此課程不存在");
			res.setMessage(StudentSubjectRtnCode.NOSTUDENTINFO_REQIRED.getMessage());
			return res;
		}
		if (!StringUtils.hasText(subName)) {
//			res.setMessage("課程名稱錯誤");
			res.setMessage(StudentSubjectRtnCode.DUBCLASS_REQIRED.getMessage());
			return res;
		}
		if (subDate >= 5 || subDate < 1 || units == null) {
//			res.setMessage("輸入星期格式錯誤");
			res.setMessage(StudentSubjectRtnCode.DATE_REQIRED.getMessage());
			return res;
		}
		if (startTime > 16 || startTime < 9 || startTime >= endTime || startTime == null) {
//			res.setMessage("上課時間錯誤");
			res.setMessage(StudentSubjectRtnCode.DATE_REQIRED.getMessage());
			return res;
		}
		if (endTime > 18 || endTime < 11 || endTime == null) {
//			res.setMessage("結束時間錯誤");
			res.setMessage(StudentSubjectRtnCode.DATE_REQIRED.getMessage());
			return res;
		}
		if (units > 3 || units < 1 || units == null) {
//			res.setMessage("學分錯誤");
			res.setMessage(StudentSubjectRtnCode.UNITS_REQIRED.getMessage());
			return res;
		}

		Subject subject1 = subOp.get();// 要再判斷又沒有值之後再get
		Subject subject = new Subject(subNumber, subName, subDate, startTime, endTime, units);

		subjectDao.save(subject);
		res.setSubject(subject1);
		res.setMessage(subNumber + "修改成功!!!");
		return res;
	}

	// 4.刪除課程
	@Override
	public SubjectRes deletSubject(String subNumber) {
		SubjectRes res = new SubjectRes();

		if (!StringUtils.hasText(subNumber)) {
//			res.setMessage("課程代碼輸入錯誤");
			res.setMessage(StudentSubjectRtnCode.CLASS_REQIRED.getMessage());
			return res;
		} // 如果寫入的資料沒有東西就顯示資料錯ˋ (防呆1.)
		if (!subjectDao.existsById(subNumber)) { // 如果資料庫裏面的資料跟我輸入的資料有符合則回傳有
//			res.setMessage("查無此課程~~");
			res.setMessage(StudentSubjectRtnCode.NOCLASSINFO_REQIRED.getMessage());
			return res;
		}
		subjectDao.deleteById(subNumber);
		res.setMessage("刪除課程" + subNumber + "成功");
		return res;
	}

	// 5.建立學生資訊
	@Override
	public StudentRes createStudent(String stuNumber, String stuName) {
		StudentRes res = new StudentRes();

		if (!StringUtils.hasText(stuNumber)) {
//			res.setMessage("帳號格式錯誤");
			res.setMessage(StudentSubjectRtnCode.DOUSTUDENT_REQIRED.getMessage());
			return res;
		}

		if (!StringUtils.hasText(stuName)) {
//			res.setMessage("名字輸入錯誤");
			res.setMessage(StudentSubjectRtnCode.DOUSTUDENT_REQIRED.getMessage());
			return res;
		}
		Optional<Student> stuOp = studentDao.findById(stuNumber);
		if (!stuOp.isPresent()) { // 如果資料庫裏面的資料跟我輸入的資料有符合則回傳有
			Student student = new Student(stuNumber, stuName);
			res.setStudent(student);
//		res.setMessage("資料建立成功");
			res.setMessage(StudentSubjectRtnCode.SUCCESSFUL.getMessage());
			studentDao.save(student);
			return res;
		}
//		res.setMessage("此帳號已經存在");
		res.setMessage(StudentSubjectRtnCode.DOUSTUDENT_REQIRED.getMessage());
		return res;

	}

	// 7.選課 (加選)
	// 問題 1.無法寫入兩項課目 //解決
	// 問題 2.直接覆蓋原本選課
	//
	@Override
	public StudentRes reviseStudentSubject(String stuNumber, String subNumber) {// String subNumber 改成Set<>()
		// 建立學生選課, 找到學生資料並且寫入他的選課 (第一步先分開選課)
		StudentRes res = new StudentRes();

		if (!StringUtils.hasText(stuNumber)) {
//			res.setMessage("輸入格式錯誤");
			res.setMessage(StudentSubjectRtnCode.CREAT_REQIRED.getMessage());
			return res;
		}
		if (!StringUtils.hasText(subNumber)) {// .if(!)subNumber=null or -->跳出,if(true)keep going
//			res.setMessage("課堂輸入錯誤");
			res.setMessage(StudentSubjectRtnCode.CLASS_REQIRED.getMessage());
			return res;
		}

		Optional<Student> stuOp = studentDao.findById(stuNumber);//
		if (!stuOp.isPresent()) {// 找到學生資料.if(!)學生的資訊=跳出if(true)學生的資訊keep going
//			res.setMessage("此學生不存在");
			res.setMessage(StudentSubjectRtnCode.NOSTUDENTINFO_REQIRED.getMessage());
			return res;
		}

		Student student = stuOp.get();// 接取得資訊
		String stuSub = student.getSubNumber(); // 撈出該學生原本的選課
		String[] stusubArray = null; // 如果原本學生資訊是null
		List<String> suboldList = new ArrayList<>(); // 建立一個set接等等會取得的課程代碼
		if (StringUtils.hasText(stuSub)) {
			stusubArray = stuSub.split(","); // 用","切割
			for (String item : stusubArray) {// 用foreach去接 並且加入倒set裡面
				item = item.trim();
				suboldList.add(item); // set儲存方法 add
			}
		}
		List<String> subNewList = new ArrayList<>();
		String[] subNumArray = subNumber.split(",");// 學生新加選的課
		for (String item : subNumArray) {
			String str = item.trim();
			subNewList.add(str); // 一樣去空白然後切割然後丟到set
		}
		// findaLL 撈出所有課程資料去比對輸入的課程名稱是否相同
		// 減少比對次數
		List<String> subCheckList = new ArrayList<>();
		for (String item : subNewList) {
			if (suboldList.contains(item)) {
//				res.setMessage("選課重複 / 錯誤 ");
				res.setMessage(StudentSubjectRtnCode.DUBCLASS_REQIRED.getMessage());
				return res;
			}
			subCheckList.add(item);

		}

		List<Subject> subjectd = subjectDao.findAllById(subCheckList);//
		List<String> strSubList = new ArrayList<>();// 資料庫的SubNumber
		for (Subject str : subjectd) {
			strSubList.add(str.getSubNumber());
		}
		for (String item : subCheckList) {
			if (!strSubList.contains(item)) {
//				res.setMessage("查無此課~~");
				res.setMessage(StudentSubjectRtnCode.NOCLASSINFO_REQIRED.getMessage());
				return res;
			}
			suboldList.add(item);

		}

//		List<Subject> stusub = subjectDao.findAllById(subCheckList);// 原本的
		boolean checkclass = checkclassName(subjectd);
		if (checkclass) {
//			res.setMessage("課程名稱重覆!!!!!");
			res.setMessage(StudentSubjectRtnCode.DUBCLASS_REQIRED.getMessage());
			return res;
		}

		boolean timeCheck = subjectTime(subjectd);
		if (timeCheck) {
//			res.setMessage("時間衝堂!!!!!");
			res.setMessage(StudentSubjectRtnCode.DUBCLASSADD_REQIRED.getMessage());
			return res;
		}

		int totunit = 0;
		for (Subject item2 : subjectd) {
			totunit += item2.getUnits();

		}

		if (totunit > 10) {
//			res.setMessage("已經超過10學分不得選課");
			res.setMessage(StudentSubjectRtnCode.OVERUNITS_REQIRED.getMessage());
			return res;

		} else {
			res.setMessage("總學分: " + totunit);
		}

		// Subject subject = subOp.get();

		student.setSubNumber(suboldList.toString().substring(1, (suboldList.toString().length() - 1)));
		studentDao.save(student);
//		res.setMessage("選課成功!");
		res.setMessage(StudentSubjectRtnCode.SUCCESSFUL.getMessage());
		return res;
	}

	// 8.刪除學生選課
	// 問題1. 只能刪除第一筆
	@Override
	public StudentRes deletStudentSubject(String stuNumber, String subNumber) {
		// TODO Auto-generated method stub
		StudentRes res = new StudentRes();
		if (!StringUtils.hasText(stuNumber)) {
//			res.setMessage("輸入資訊錯誤 /只能刪除單堂課程");
			res.setMessage(StudentSubjectRtnCode.DELET_REQIRED.getMessage());
		}
		if (!StringUtils.hasText(subNumber)) {// .if(!)subNumber=null or -->跳出,if(true)keep going
//			res.setMessage("課堂輸入錯誤");
			res.setMessage(StudentSubjectRtnCode.CLASS_REQIRED.getMessage());
			return res;
		}

		Optional<Student> stuOp = studentDao.findByStuNumber(stuNumber);
		if (!stuOp.isPresent()) {// 找到學生資料.if(!)學生的資訊=跳出if(true)學生的資訊keep going
//			res.setMessage("此學生不存在");
			res.setMessage(StudentSubjectRtnCode.NOSTUDENTINFO_REQIRED.getMessage());
			return res;
		}
		List<String> oldSubjectList = new ArrayList<>();
		Student student = stuOp.get();// 接取得資訊
		String stuSub = student.getSubNumber(); // 撈出該學生原本的選課
		String[] deletSubjectArray = stuSub.split(","); // 切割字串 然後分開帶入Set
		for (String delet : deletSubjectArray) {
			String deletStr = delet.trim();// 加入的字串去空白
			oldSubjectList.add(deletStr);
		}
		if (oldSubjectList.isEmpty()) {
//	     	res.setMessage("刪除失敗");
//		    return res;
			res.setMessage(StudentSubjectRtnCode.DELET_REQIRED.getMessage());
			return res;
		}
		List<String> reSubjectlist = new ArrayList<>();
		for (String deletsub : oldSubjectList) {
			if (!deletsub.equalsIgnoreCase(subNumber)) {

				reSubjectlist.add(subNumber);
//				res.setMessage("刪除成功");

			}
		}
		res.setMessage(StudentSubjectRtnCode.SUCCESSFUL.getMessage());
		student.setSubNumber(reSubjectlist.toString().substring(1, (reSubjectlist.toString().length() - 1)));
		studentDao.save(student);
		return res;
	}

	// 9.學生資訊
	@Override
	public List<Student> getStudentInfo() {
//		List<Student> list = studentDao.findAll();
		return studentDao.findAll();
	}

	// 10.用學號查詢學生選課
	@Override // 需修改顯現 res.setList1(list1);
	public StudentRes findByStuNumber(String stuNumber) {
		StudentRes res = new StudentRes();

		if (!StringUtils.hasText(stuNumber)) {
//			res.setMessage("輸入格式錯誤");
//			return res;
			res.setMessage(StudentSubjectRtnCode.CREAT_REQIRED.getMessage());
			return res;
		}
		Optional<Student> studentOp = studentDao.findById(stuNumber);
		if (!studentOp.isPresent()) {
//			res.setMessage("查無此學生資訊");
//			return res;
			res.setMessage(StudentSubjectRtnCode.NOSTUDENTINFO_REQIRED.getMessage());
			return res;
		}
		String subNumber = studentOp.get().getSubNumber();// 從資料庫撈出學生原本的選課
		List<String> subnuList = new ArrayList<>();
		String[] subNumArray = subNumber.split(",");
		for (String item : subNumArray) {
			String str = item.trim();
			subnuList.add(str); // 一樣去空白然後切割然後丟到set
		} // -->現在getStudentSubNumber 裡面已經存了stuNumber中取得的subjectNumber
		List<Subject> getStudentSubNumber = new ArrayList<>();
		// 上方方法準備儲存等等切割出來的String
		for (String item : subnuList) {
			Optional<Subject> subop = subjectDao.findBySubNumber(item);
			Subject stusubcheck = subop.get();
			getStudentSubNumber.add(stusubcheck);
		}
		res.setStudent(studentOp.get());
		res.setSubjectList(getStudentSubNumber);
//		res.setMessage("查詢成功");
//		return res;
		res.setMessage(StudentSubjectRtnCode.SUCCESSFUL.getMessage());
		return res;

	}

	// 11.透過課堂編碼查詢課堂資訊
	@Override
	public SubjectRes findBySubNumber(String subNumber) {
		SubjectRes res = new SubjectRes();
		if (!StringUtils.hasText(subNumber)) {
//			res.setMessage("輸入格式錯誤");
//			return res;
			return res = new SubjectRes(null, StudentSubjectRtnCode.CREAT_REQIRED.getMessage());

		}
		Optional<Subject> subOp = subjectDao.findById(subNumber);

		if (!subOp.isPresent()) {
//			res.setMessage("查無此課程");
//			return res;
			return res = new SubjectRes(null, StudentSubjectRtnCode.CLASS_REQIRED.getMessage());

		}
//		
		res.setSubject(subOp.get());
//		res.setMessage("查詢成功");
		return res = new SubjectRes(null, StudentSubjectRtnCode.SUCCESSFUL.getMessage());

	}

	// 12.透過課堂名稱找尋課堂資訊
	@Override
	public SubjectRes findBySubName(String subName) {
		SubjectRes res = new SubjectRes();
		if (!StringUtils.hasText(subName)) {
//			res.setMessage("輸入格式錯誤");
//			return res;
			return new SubjectRes(null, StudentSubjectRtnCode.CREAT_REQIRED.getMessage());
		}
		List<Subject> sublist = subjectDao.findAllBySubName(subName);
		res.setList(sublist);
		return res;
	}

	/* ===========================判斷時間============================ */
	private boolean subjectTime(List<Subject> stusub) {
//		List<Subject> stusub = subjectDao.findAllById(subNumber);
// 		把所有的資料丟到list
//		boolean test = false;
		for (int i = 0; i < (stusub.size() - 1); i++) {
			Subject subject = stusub.get(i);
			for (int j = i + 1; j < (stusub.size()); j++) {
				Subject Subjectj = stusub.get(j);
				if (subject.getSubDate() == Subjectj.getSubDate()) {
					if (subject.getStartTime() >= Subjectj.getStartTime()
							&& subject.getStartTime() <= Subjectj.getEndTime()
							|| subject.getEndTime() >= Subjectj.getStartTime()
									&& subject.getEndTime() <= Subjectj.getEndTime()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	
	/* ===================判斷課程有沒有重複==================== */
	private boolean checkclassName(List<Subject> className) {
//		List<Subject> className = subjectDao.findAllById(subNumber);
		for (int i = 0; i < (className.size() - 1); i++) {
			Subject Subject = className.get(i);
			for (int j = i + 1; j < (className.size()); j++) {
				Subject Subjectj = className.get(j);
				if (Subject.getSubName().equalsIgnoreCase(Subjectj.getSubName())) {
					return true;
				}
			}
		}
		return false;
	}

	/* ======================================================= */
}
