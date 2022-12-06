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
	private StudentDao studentDao; // 連接兩個資料庫 (StudentDao /SubjectDao)
	@Autowired
	private SubjectDao subjectDao;

	// 1.建立課程 :輸入課程代碼,課程名稱,課程星期,課程開始時間,課程結束時間,課程學分
	@Override
	public SubjectRes creatSubject(String subjectNumber, String subjectName, Integer subjectDate, Integer startTime,
			Integer endTime, Integer units) {

		// 判斷(subjectNumber, subjectName )內容是否為空
		SubjectRes subjectInforeSult = checkSubjectNumberSubjectName(subjectNumber, subjectName);
		if (subjectInforeSult != null) {
			return subjectInforeSult;
		}

		// 判斷內容是否為空
		SubjectRes classInforeSult = checkSubjectDateStartTimeEanTimeUnits(subjectDate, startTime, endTime, units);
		if (classInforeSult != null) {
			return classInforeSult;
		}

		// 調閱資料庫課程代碼並且判斷是否重複,若有則回傳訊息並且跳出，
		SubjectRes res = new SubjectRes();
		Optional<Subject> subOp = subjectDao.findById(subjectNumber);

		if (subOp.isPresent()) {
			res.setMessage(StudentSubjectRtnCode.HASCLASS_REQIRED.getMessage());
			return res;
		}

		// 使用自訂建構方法代數參數 並存回資料庫.在結果顯示建立的課程
		// 當條件符合存入後回傳成功
		Subject subject = new Subject(subjectNumber, subjectName, subjectDate, startTime, endTime, units);
		subjectDao.save(subject);
		res.setSubject(subject);
		res.setMessage(StudentSubjectRtnCode.SUCCESSFUL.getMessage());
		return res;
	}

	/**
	 * 檢驗 課程代碼(subNumber) & 課程名稱subjectName(subName) 是否有值
	 */
	private SubjectRes checkSubjectNumberSubjectName(String subNumber, String subName) {

		if (!StringUtils.hasText(subNumber) || !StringUtils.hasText(subName)) {

			return new SubjectRes(null, StudentSubjectRtnCode.CREAT_REQIRED.getMessage());
		}
		return null;
	}

	/**
	 * 檢驗 課堂星期(subDate) & 上課時間(startTime) & 結束時間(endTime) & 學分數(units) 格式是否正確
	 */
	private SubjectRes checkSubjectDateStartTimeEanTimeUnits(Integer subjectDate, Integer startTime, Integer endTime,
			Integer units) {

		if (subjectDate > 6 || subjectDate < 1 || subjectDate == null) {
			return new SubjectRes(null, StudentSubjectRtnCode.DATE_REQIRED.getMessage());
		}

		if (startTime > 16 || startTime < 9 || startTime >= endTime || startTime == null) {
			return new SubjectRes(null, StudentSubjectRtnCode.DATE_REQIRED.getMessage());
		}

		if (endTime > 18 || endTime < 11 || endTime == null) {
			return new SubjectRes(null, StudentSubjectRtnCode.DATE_REQIRED.getMessage());
		}

		if (units > 3 || units < 1 || units == null) {
			return new SubjectRes(null, StudentSubjectRtnCode.UNITS_REQIRED.getMessage());
		}

		return null;
	}

	// 2.更改課程內容:變更課程代碼,課程名稱,課程星期,課程開始時間,課程結束時間,課程學分
	@Override
	public SubjectRes reviseSubject(String subjectNumber, String subjectName, Integer subjectDate, Integer startTime,
			Integer endTime, Integer units) {

		// 判斷內容是否為空
		SubjectRes subjectInforeSult = checkSubjectNumberSubjectName(subjectNumber, subjectName);
		if (subjectInforeSult != null) {
			return subjectInforeSult;
		}

		// 判斷內容是否為空
		SubjectRes classInforeSult = checkSubjectDateStartTimeEanTimeUnits(subjectDate, startTime, endTime, units);
		if (classInforeSult != null) {
			return classInforeSult;
		}

		// 判斷有無此課程，若無則回傳訊息並且跳出，
		SubjectRes res = new SubjectRes();
		Optional<Subject> subOp = subjectDao.findById(subjectNumber);

		if (!subOp.isPresent()) {
			res.setMessage(StudentSubjectRtnCode.NOCLASS_REQIRED.getMessage());
			return res;
		}

		// 使用建構方法帶入參數後儲存到資料庫。取得更改的資訊後 列出,當條件符合存入後回傳成功。
		Subject subject = new Subject(subjectNumber, subjectName, subjectDate, startTime, endTime, units);
		subjectDao.save(subject);
		res.setSubject(subOp.get());
		res.setMessage(subjectNumber + StudentSubjectRtnCode.SUCCESSFUL.getMessage());
		return res;

	}

	// 3.刪除課程: 移除已存在的課程
	@Override
	public SubjectRes deletSubject(String subjectNumber) {
		SubjectRes res = new SubjectRes();
		// 判斷內容是否為空
		if (!StringUtils.hasText(subjectNumber)) {
			res.setMessage(StudentSubjectRtnCode.CLASS_REQIRED.getMessage());
			return res;
		}
		// 如果資料庫裏面的資料跟輸入的資料沒有符合則回訊息並且跳出
		if (!subjectDao.existsById(subjectNumber)) {
			res.setMessage(StudentSubjectRtnCode.NOCLASS_REQIRED.getMessage());
			return res;
		}
		// 當條件符合刪除課程,存入後回傳成功。
		subjectDao.deleteById(subjectNumber);
		res.setMessage(StudentSubjectRtnCode.SUCCESSFUL.getMessage());
		return res;
	}

	// 4.建立學生資訊 :輸入學生學號,學生姓名 。
	@Override
	public StudentRes createStudent(String studentNumber, String studentName) {
		StudentRes res = new StudentRes();
		// 判斷內容是否為空
		SubjectRes checkStudentNumberstudentName = checkStudentNumberstudentName(studentNumber, studentName);

		if (checkStudentNumberstudentName != null) {
			res.setMessage(StudentSubjectRtnCode.CREAT_REQIRED.getMessage());
			return res;
		}
		Optional<Student> stuOp = studentDao.findById(studentNumber);
		// 當學生學號不存在資料庫
		if (!stuOp.isPresent()) {
			// 則建立此學生資訊。建立資料後並且回傳訊息成功。
			Student student = new Student(studentNumber, studentName);
			studentDao.save(student);
			res.setStudent(student);
			res.setMessage(StudentSubjectRtnCode.SUCCESSFUL.getMessage());
			return res;
		}
		// 當學生學號已經使用,顯示帳號已經存在，結束執行。
		res.setMessage(StudentSubjectRtnCode.EXISTSSTUDENT_REQIRED.getMessage());
		return res;
	}

	/**
	 * 顯示studentNumber /studentName 是否有值
	 */
	private SubjectRes checkStudentNumberstudentName(String studentNumber, String studentName) {

		if (!StringUtils.hasText(studentNumber) || !StringUtils.hasText(studentName)) {
			return new SubjectRes(null, StudentSubjectRtnCode.CREAT_REQIRED.getMessage());
		}

		return null;
	}

	// 5.選課 (初選 /加選): 建立學生選課, 找到學生資料判斷是否衝堂/重複/超過學分 ，並且寫入他的選課
	@Override
	public StudentRes reviseStudentSubject(String studentNumber, String subjectNumber) {
		StudentRes res = new StudentRes();

		SubjectRes checkStudentSubject = checkStudentNumberSubjectNumber(studentNumber, subjectNumber);
		// 判斷內容是否為空
		if (checkStudentSubject != null) {
			res.setMessage(StudentSubjectRtnCode.IMPORT_FAIL.getMessage());
			return res;
		}

		Optional<Student> studentOp = studentDao.findById(studentNumber);
		// 當學生學號不存在資料庫，回傳錯誤訊息並且跳出。
		if (!studentOp.isPresent()) {
			res.setMessage(StudentSubjectRtnCode.NOSTUDENTINFO_REQIRED.getMessage());
			return res;
		}
		// 資料確認後接取得資訊
		Student student = studentOp.get();
		// 從資料庫取得該學生原本的選課
		String studentSub = student.getSubNumber();

		// (原先學生選課)外部編輯私有方法 對字串做切割去空白並且存入LIST
		List<String> subjectOldList = getStringList(studentSub);
		// (新增學生選課)外部編輯私有方法 對字串做切割去空白並且存入LIST
		List<String> subjectNewList = getStringList(subjectNumber);

		// 當跟資料庫比對後無此課程,回傳錯誤訊息並且跳出。
		List<Subject> subjectNewCheckList = subjectDao.findAllById(subjectNewList);

		if (subjectNewCheckList.isEmpty()) {
			res.setMessage(StudentSubjectRtnCode.NOCLASS_REQIRED.getMessage());
			return res;
		}

		for (String item : subjectNewList) { // (原先學生選課)對比(新增學生選課)
			if (subjectOldList.contains(item)) { // 當有相同課程代碼,回傳錯誤訊息並且跳出。
				res.setMessage(StudentSubjectRtnCode.DUBCLASS_REQIRED.getMessage());
				return res;
			}
			subjectOldList.add(item); // 當條件符合則新增到subjectoldList。
		}
		// 在資料庫中取得資訊後續做對比
		List<Subject> subjectLastList = subjectDao.findAllById(subjectOldList);

		// 判斷課程名字是否相同 / 時間衝堂
		res = checkClassNameAndTime(subjectLastList);
		if (res != null) {
			return res;
		}

		res = new StudentRes();
		// 當條件符合，取出其學分數做加總。
		int totalUnit = 0;
		for (Subject item2 : subjectLastList) {
			totalUnit += item2.getUnits();
		}

		if (totalUnit > 10) { // 當學分總數超過10學分,回傳錯誤訊息並且跳出。
			res.setMessage(StudentSubjectRtnCode.OVERUNITS_REQIRED.getMessage());
			return res;
		}
		// 當條件符合存入後回傳成功
		student.setSubjectNumber(subjectOldList.toString().substring(1, (subjectOldList.toString().length() - 1)));
		studentDao.save(student);
		res.setMessage(StudentSubjectRtnCode.SUCCESSFUL.getMessage());
		return res;
	}

	/**
	 * 檢驗 學生學號 & 課程代碼是否符合格式
	 */
	private SubjectRes checkStudentNumberSubjectNumber(String studentNumber, String subjectNumber) {

		if (!StringUtils.hasText(studentNumber) || !StringUtils.hasText(subjectNumber)) {

			return new SubjectRes(null, StudentSubjectRtnCode.CREAT_REQIRED.getMessage());
		}
		return null;
	}

	/**
	 * 1. 將字串做切割 2. 去空白後存入LIST
	 */
	private List<String> getStringList(String stringArray) {

		List<String> stringList = new ArrayList<>(); // 建立一個list接取得的課程代碼
		if (StringUtils.hasText(stringArray)) {
			String[] subjectNumArray = stringArray.split(",");
			for (String item : subjectNumArray) {
				String str = item.trim();
				stringList.add(str);
			}
		}
		return stringList;
	}

	/**
	 * 判斷時間是否衝堂 1. A課堂上課的時間 介於 B課堂上課中間 -->衝堂 2. A課堂下課的時間 介於 B課堂上課中間 -->衝堂
	 */
	private boolean subjectTime(List<Subject> stusub) {

		for (int i = 0; i < (stusub.size() - 1); i++) {
			Subject subject = stusub.get(i);

			for (int j = i + 1; j < (stusub.size()); j++) {
				Subject subject1 = stusub.get(j);

				if (subject.getSubjectDate() == subject1.getSubjectDate()) {

					if (subject.getStartTime() >= subject1.getStartTime()
							&& subject.getStartTime() <= subject1.getEndTime()

							|| subject.getEndTime() >= subject1.getStartTime()
									&& subject.getEndTime() <= subject1.getEndTime()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 將判斷是否重複或者衝堂的方法拉出
	 */
	private StudentRes checkClassNameAndTime(List<Subject> subjectLastList) {
		StudentRes res = new StudentRes();

		boolean checkclass = checkclassName(subjectLastList);
		if (checkclass) {
			res.setMessage(StudentSubjectRtnCode.DUBCLASS_REQIRED.getMessage()); // 顯示課程名稱重複
			return res;
		}

		boolean timeCheck = subjectTime(subjectLastList);
		if (timeCheck) {
			res.setMessage(StudentSubjectRtnCode.DUBCLASSTIME_REQIRED.getMessage());
			return res;
		}
		return null;
	}

	/**
	 * 判斷課程名稱有沒有重複
	 */
	private boolean checkclassName(List<Subject> className) {

		for (int i = 0; i < (className.size() - 1); i++) {
			Subject subject = className.get(i);

			for (int j = i + 1; j < (className.size()); j++) {
				Subject subject1 = className.get(j);

				if (subject.getSubjectName().equalsIgnoreCase(subject1.getSubjectName())) {
					return true;
				}
			}
		}
		return false;
	}

	@Override // 6.學生退選課程 : 限定一次只刪除一堂課程
	public StudentRes deletStudentSubject(String studentNumber, String subjectNumber) {

		StudentRes res = new StudentRes();
		// 判斷內容是否為空
		SubjectRes checkStudentSubject = checkStudentNumberSubjectNumber(studentNumber, subjectNumber);
		if (checkStudentSubject != null) {
			res.setMessage(StudentSubjectRtnCode.IMPORT_FAIL.getMessage());
			return res;
		}
		// 當資料庫不存在學生資訊，回傳錯誤訊息並且跳出。
		Optional<Student> studentOp = studentDao.findByStudentNumber(studentNumber);
		if (!studentOp.isPresent()) {
			res.setMessage(StudentSubjectRtnCode.NOSTUDENTINFO_REQIRED.getMessage());
			return res;
		}

		Student student = studentOp.get();
		String studentSubject = student.getSubNumber(); // 取出學生的選課字串在私有法做切割動作帶入list中
		List<String> oldSubjectList = getStringList(studentSubject);

		// 將輸入的課程跟(原先選課)對比,若無此課則回傳錯誤訊息並且跳出
		if (!oldSubjectList.contains(subjectNumber)) {
			res.setMessage(StudentSubjectRtnCode.NOCLASS_REQIRED.getMessage());
			return res;
		}
		// 當資料符合，將其移除,字串存入資料庫並且回傳成功訊襑。
		oldSubjectList.remove(subjectNumber);
		student.setSubjectNumber(oldSubjectList.toString().substring(1, (oldSubjectList.toString().length() - 1)));
		studentDao.save(student);
		res.setMessage(StudentSubjectRtnCode.SUCCESSFUL.getMessage());
		return res;
	}

	// 7.用學號查詢學生選課
	@Override
	public StudentRes SearchStudentNumber(String studentNumber) {

		StudentRes res = new StudentRes();
		// 判斷內容是否為空
		if (!StringUtils.hasText(studentNumber)) {
			res.setMessage(StudentSubjectRtnCode.CREAT_REQIRED.getMessage());
			return res;
		}

		// 對比資料庫中,若無此學生訊息則回傳錯誤訊息並且跳出。
		Optional<Student> studentOp = studentDao.findById(studentNumber);
		if (!studentOp.isPresent()) {
			res.setMessage(StudentSubjectRtnCode.NOSTUDENTINFO_REQIRED.getMessage());
			return res;
		}
		// 當取得學生選課代碼字串做切割放入list中
		String subjectNumber = studentOp.get().getSubNumber();
		List<String> subjectNumberuList = getStringList(subjectNumber);

		// 對比資料庫取得課程資訊並且存入LIST
		List<Subject> getStudentSubjectNumber = new ArrayList<>();
		for (String item : subjectNumberuList) {
			Optional<Subject> subOp = subjectDao.findBySubjectNumber(item);
			Subject studentSubjectcheck = subOp.get();
			getStudentSubjectNumber.add(studentSubjectcheck);
		}

		// 當條件符合存入後回傳list & 成功
		res.setStudent(studentOp.get());
		res.setSubjectList(getStudentSubjectNumber);
		res.setMessage(StudentSubjectRtnCode.SUCCESSFUL.getMessage());
		return res;
	}

	// 8.透過課堂編碼查詢課堂資訊
	@Override
	public SubjectRes searchSubNumber(String subjectNumber) {
		// 判斷內容是否為空
		if (!StringUtils.hasText(subjectNumber)) {
			return new SubjectRes(null, StudentSubjectRtnCode.CLASS_REQIRED.getMessage());
		}
		// 對比資料庫中,若無此課程訊息則回傳錯誤訊息並且跳出。
		Optional<Subject> subjectOp = subjectDao.findById(subjectNumber);
		if (!subjectOp.isPresent()) {
			return new SubjectRes(null, StudentSubjectRtnCode.CLASS_REQIRED.getMessage());
		}
		// 當條件符合-->顯示課堂資訊&成功
		Subject subject = subjectOp.get();
		return new SubjectRes(subject, StudentSubjectRtnCode.SUCCESSFUL.getMessage());
	}

	// 9.透過課堂名稱找尋課堂資訊
	@Override
	public SubjectRes searchSubjectName(String subjectName) {
		SubjectRes res = new SubjectRes();
		// 當結果格式錯誤，回傳錯誤訊息並且跳出。
		if (!StringUtils.hasText(subjectName)) {
			return new SubjectRes(null, StudentSubjectRtnCode.CREAT_REQIRED.getMessage());
		}
		// 當條件符合顯示課堂資訊&成功
		List<Subject> subjectlist = subjectDao.findAllBySubjectName(subjectName);
		res.setList(subjectlist);
		return res;
	}

}
