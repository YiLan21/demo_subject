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
	private StudentDao studentDao; // �s����Ӹ�Ʈw (StudentDao /SubjectDao)
	@Autowired
	private SubjectDao subjectDao;

	// 1.�إ߽ҵ{ :��J�ҵ{�N�X,�ҵ{�W��,�ҵ{�P��,�ҵ{�}�l�ɶ�,�ҵ{�����ɶ�,�ҵ{�Ǥ�
	@Override
	public SubjectRes creatSubject(String subjectNumber, String subjectName, Integer subjectDate, Integer startTime,
			Integer endTime, Integer units) {

		// �P�_(subjectNumber, subjectName )���e�O�_����
		SubjectRes subjectInforeSult = checkSubjectNumberSubjectName(subjectNumber, subjectName);
		if (subjectInforeSult != null) {
			return subjectInforeSult;
		}

		// �P�_���e�O�_����
		SubjectRes classInforeSult = checkSubjectDateStartTimeEanTimeUnits(subjectDate, startTime, endTime, units);
		if (classInforeSult != null) {
			return classInforeSult;
		}

		// �վ\��Ʈw�ҵ{�N�X�åB�P�_�O�_����,�Y���h�^�ǰT���åB���X�A
		SubjectRes res = new SubjectRes();
		Optional<Subject> subOp = subjectDao.findById(subjectNumber);

		if (subOp.isPresent()) {
			res.setMessage(StudentSubjectRtnCode.HASCLASS_REQIRED.getMessage());
			return res;
		}

		// �ϥΦۭq�غc��k�N�ưѼ� �æs�^��Ʈw.�b���G��ܫإߪ��ҵ{
		// �����ŦX�s�J��^�Ǧ��\
		Subject subject = new Subject(subjectNumber, subjectName, subjectDate, startTime, endTime, units);
		subjectDao.save(subject);
		res.setSubject(subject);
		res.setMessage(StudentSubjectRtnCode.SUCCESSFUL.getMessage());
		return res;
	}

	/**
	 * ���� �ҵ{�N�X(subNumber) & �ҵ{�W��subjectName(subName) �O�_����
	 */
	private SubjectRes checkSubjectNumberSubjectName(String subNumber, String subName) {

		if (!StringUtils.hasText(subNumber) || !StringUtils.hasText(subName)) {

			return new SubjectRes(null, StudentSubjectRtnCode.CREAT_REQIRED.getMessage());
		}
		return null;
	}

	/**
	 * ���� �Ұ�P��(subDate) & �W�Үɶ�(startTime) & �����ɶ�(endTime) & �Ǥ���(units) �榡�O�_���T
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

	// 2.���ҵ{���e:�ܧ�ҵ{�N�X,�ҵ{�W��,�ҵ{�P��,�ҵ{�}�l�ɶ�,�ҵ{�����ɶ�,�ҵ{�Ǥ�
	@Override
	public SubjectRes reviseSubject(String subjectNumber, String subjectName, Integer subjectDate, Integer startTime,
			Integer endTime, Integer units) {

		// �P�_���e�O�_����
		SubjectRes subjectInforeSult = checkSubjectNumberSubjectName(subjectNumber, subjectName);
		if (subjectInforeSult != null) {
			return subjectInforeSult;
		}

		// �P�_���e�O�_����
		SubjectRes classInforeSult = checkSubjectDateStartTimeEanTimeUnits(subjectDate, startTime, endTime, units);
		if (classInforeSult != null) {
			return classInforeSult;
		}

		// �P�_���L���ҵ{�A�Y�L�h�^�ǰT���åB���X�A
		SubjectRes res = new SubjectRes();
		Optional<Subject> subOp = subjectDao.findById(subjectNumber);

		if (!subOp.isPresent()) {
			res.setMessage(StudentSubjectRtnCode.NOCLASS_REQIRED.getMessage());
			return res;
		}

		// �ϥΫغc��k�a�J�Ѽƫ��x�s���Ʈw�C���o��諸��T�� �C�X,�����ŦX�s�J��^�Ǧ��\�C
		Subject subject = new Subject(subjectNumber, subjectName, subjectDate, startTime, endTime, units);
		subjectDao.save(subject);
		res.setSubject(subOp.get());
		res.setMessage(subjectNumber + StudentSubjectRtnCode.SUCCESSFUL.getMessage());
		return res;

	}

	// 3.�R���ҵ{: �����w�s�b���ҵ{
	@Override
	public SubjectRes deletSubject(String subjectNumber) {
		SubjectRes res = new SubjectRes();
		// �P�_���e�O�_����
		if (!StringUtils.hasText(subjectNumber)) {
			res.setMessage(StudentSubjectRtnCode.CLASS_REQIRED.getMessage());
			return res;
		}
		// �p�G��Ʈw�ح�����Ƹ��J����ƨS���ŦX�h�^�T���åB���X
		if (!subjectDao.existsById(subjectNumber)) {
			res.setMessage(StudentSubjectRtnCode.NOCLASS_REQIRED.getMessage());
			return res;
		}
		// �����ŦX�R���ҵ{,�s�J��^�Ǧ��\�C
		subjectDao.deleteById(subjectNumber);
		res.setMessage(StudentSubjectRtnCode.SUCCESSFUL.getMessage());
		return res;
	}

	// 4.�إ߾ǥ͸�T :��J�ǥ;Ǹ�,�ǥͩm�W �C
	@Override
	public StudentRes createStudent(String studentNumber, String studentName) {
		StudentRes res = new StudentRes();
		// �P�_���e�O�_����
		SubjectRes checkStudentNumberstudentName = checkStudentNumberstudentName(studentNumber, studentName);

		if (checkStudentNumberstudentName != null) {
			res.setMessage(StudentSubjectRtnCode.CREAT_REQIRED.getMessage());
			return res;
		}
		Optional<Student> stuOp = studentDao.findById(studentNumber);
		// ��ǥ;Ǹ����s�b��Ʈw
		if (!stuOp.isPresent()) {
			// �h�إߦ��ǥ͸�T�C�إ߸�ƫ�åB�^�ǰT�����\�C
			Student student = new Student(studentNumber, studentName);
			studentDao.save(student);
			res.setStudent(student);
			res.setMessage(StudentSubjectRtnCode.SUCCESSFUL.getMessage());
			return res;
		}
		// ��ǥ;Ǹ��w�g�ϥ�,��ܱb���w�g�s�b�A��������C
		res.setMessage(StudentSubjectRtnCode.EXISTSSTUDENT_REQIRED.getMessage());
		return res;
	}

	/**
	 * ���studentNumber /studentName �O�_����
	 */
	private SubjectRes checkStudentNumberstudentName(String studentNumber, String studentName) {

		if (!StringUtils.hasText(studentNumber) || !StringUtils.hasText(studentName)) {
			return new SubjectRes(null, StudentSubjectRtnCode.CREAT_REQIRED.getMessage());
		}

		return null;
	}

	// 5.��� (��� /�[��): �إ߾ǥͿ��, ���ǥ͸�ƧP�_�O�_�İ�/����/�W�L�Ǥ� �A�åB�g�J�L�����
	@Override
	public StudentRes reviseStudentSubject(String studentNumber, String subjectNumber) {
		StudentRes res = new StudentRes();

		SubjectRes checkStudentSubject = checkStudentNumberSubjectNumber(studentNumber, subjectNumber);
		// �P�_���e�O�_����
		if (checkStudentSubject != null) {
			res.setMessage(StudentSubjectRtnCode.IMPORT_FAIL.getMessage());
			return res;
		}

		Optional<Student> studentOp = studentDao.findById(studentNumber);
		// ��ǥ;Ǹ����s�b��Ʈw�A�^�ǿ��~�T���åB���X�C
		if (!studentOp.isPresent()) {
			res.setMessage(StudentSubjectRtnCode.NOSTUDENTINFO_REQIRED.getMessage());
			return res;
		}
		// ��ƽT�{�ᱵ���o��T
		Student student = studentOp.get();
		// �q��Ʈw���o�Ӿǥͭ쥻�����
		String studentSub = student.getSubNumber();

		// (����ǥͿ��)�~���s��p����k ��r�갵���Υh�ťըåB�s�JLIST
		List<String> subjectOldList = getStringList(studentSub);
		// (�s�W�ǥͿ��)�~���s��p����k ��r�갵���Υh�ťըåB�s�JLIST
		List<String> subjectNewList = getStringList(subjectNumber);

		// ����Ʈw����L���ҵ{,�^�ǿ��~�T���åB���X�C
		List<Subject> subjectNewCheckList = subjectDao.findAllById(subjectNewList);

		if (subjectNewCheckList.isEmpty()) {
			res.setMessage(StudentSubjectRtnCode.NOCLASS_REQIRED.getMessage());
			return res;
		}

		for (String item : subjectNewList) { // (����ǥͿ��)���(�s�W�ǥͿ��)
			if (subjectOldList.contains(item)) { // ���ۦP�ҵ{�N�X,�^�ǿ��~�T���åB���X�C
				res.setMessage(StudentSubjectRtnCode.DUBCLASS_REQIRED.getMessage());
				return res;
			}
			subjectOldList.add(item); // �����ŦX�h�s�W��subjectoldList�C
		}
		// �b��Ʈw�����o��T���򰵹��
		List<Subject> subjectLastList = subjectDao.findAllById(subjectOldList);

		// �P�_�ҵ{�W�r�O�_�ۦP / �ɶ��İ�
		res = checkClassNameAndTime(subjectLastList);
		if (res != null) {
			return res;
		}

		res = new StudentRes();
		// �����ŦX�A���X��Ǥ��ư��[�`�C
		int totalUnit = 0;
		for (Subject item2 : subjectLastList) {
			totalUnit += item2.getUnits();
		}

		if (totalUnit > 10) { // ��Ǥ��`�ƶW�L10�Ǥ�,�^�ǿ��~�T���åB���X�C
			res.setMessage(StudentSubjectRtnCode.OVERUNITS_REQIRED.getMessage());
			return res;
		}
		// �����ŦX�s�J��^�Ǧ��\
		student.setSubjectNumber(subjectOldList.toString().substring(1, (subjectOldList.toString().length() - 1)));
		studentDao.save(student);
		res.setMessage(StudentSubjectRtnCode.SUCCESSFUL.getMessage());
		return res;
	}

	/**
	 * ���� �ǥ;Ǹ� & �ҵ{�N�X�O�_�ŦX�榡
	 */
	private SubjectRes checkStudentNumberSubjectNumber(String studentNumber, String subjectNumber) {

		if (!StringUtils.hasText(studentNumber) || !StringUtils.hasText(subjectNumber)) {

			return new SubjectRes(null, StudentSubjectRtnCode.CREAT_REQIRED.getMessage());
		}
		return null;
	}

	/**
	 * 1. �N�r�갵���� 2. �h�ťի�s�JLIST
	 */
	private List<String> getStringList(String stringArray) {

		List<String> stringList = new ArrayList<>(); // �إߤ@��list�����o���ҵ{�N�X
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
	 * �P�_�ɶ��O�_�İ� 1. A�Ұ�W�Ҫ��ɶ� ���� B�Ұ�W�Ҥ��� -->�İ� 2. A�Ұ�U�Ҫ��ɶ� ���� B�Ұ�W�Ҥ��� -->�İ�
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
	 * �N�P�_�O�_���ƩΪ̽İ󪺤�k�ԥX
	 */
	private StudentRes checkClassNameAndTime(List<Subject> subjectLastList) {
		StudentRes res = new StudentRes();

		boolean checkclass = checkclassName(subjectLastList);
		if (checkclass) {
			res.setMessage(StudentSubjectRtnCode.DUBCLASS_REQIRED.getMessage()); // ��ܽҵ{�W�٭���
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
	 * �P�_�ҵ{�W�٦��S������
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

	@Override // 6.�ǥͰh��ҵ{ : ���w�@���u�R���@��ҵ{
	public StudentRes deletStudentSubject(String studentNumber, String subjectNumber) {

		StudentRes res = new StudentRes();
		// �P�_���e�O�_����
		SubjectRes checkStudentSubject = checkStudentNumberSubjectNumber(studentNumber, subjectNumber);
		if (checkStudentSubject != null) {
			res.setMessage(StudentSubjectRtnCode.IMPORT_FAIL.getMessage());
			return res;
		}
		// ���Ʈw���s�b�ǥ͸�T�A�^�ǿ��~�T���åB���X�C
		Optional<Student> studentOp = studentDao.findByStudentNumber(studentNumber);
		if (!studentOp.isPresent()) {
			res.setMessage(StudentSubjectRtnCode.NOSTUDENTINFO_REQIRED.getMessage());
			return res;
		}

		Student student = studentOp.get();
		String studentSubject = student.getSubNumber(); // ���X�ǥͪ���Ҧr��b�p���k�����ΰʧ@�a�Jlist��
		List<String> oldSubjectList = getStringList(studentSubject);

		// �N��J���ҵ{��(������)���,�Y�L���ҫh�^�ǿ��~�T���åB���X
		if (!oldSubjectList.contains(subjectNumber)) {
			res.setMessage(StudentSubjectRtnCode.NOCLASS_REQIRED.getMessage());
			return res;
		}
		// ���ƲŦX�A�N�䲾��,�r��s�J��Ʈw�åB�^�Ǧ��\�T�ءC
		oldSubjectList.remove(subjectNumber);
		student.setSubjectNumber(oldSubjectList.toString().substring(1, (oldSubjectList.toString().length() - 1)));
		studentDao.save(student);
		res.setMessage(StudentSubjectRtnCode.SUCCESSFUL.getMessage());
		return res;
	}

	// 7.�ξǸ��d�߾ǥͿ��
	@Override
	public StudentRes SearchStudentNumber(String studentNumber) {

		StudentRes res = new StudentRes();
		// �P�_���e�O�_����
		if (!StringUtils.hasText(studentNumber)) {
			res.setMessage(StudentSubjectRtnCode.CREAT_REQIRED.getMessage());
			return res;
		}

		// ����Ʈw��,�Y�L���ǥͰT���h�^�ǿ��~�T���åB���X�C
		Optional<Student> studentOp = studentDao.findById(studentNumber);
		if (!studentOp.isPresent()) {
			res.setMessage(StudentSubjectRtnCode.NOSTUDENTINFO_REQIRED.getMessage());
			return res;
		}
		// ����o�ǥͿ�ҥN�X�r�갵���Ω�Jlist��
		String subjectNumber = studentOp.get().getSubNumber();
		List<String> subjectNumberuList = getStringList(subjectNumber);

		// ����Ʈw���o�ҵ{��T�åB�s�JLIST
		List<Subject> getStudentSubjectNumber = new ArrayList<>();
		for (String item : subjectNumberuList) {
			Optional<Subject> subOp = subjectDao.findBySubjectNumber(item);
			Subject studentSubjectcheck = subOp.get();
			getStudentSubjectNumber.add(studentSubjectcheck);
		}

		// �����ŦX�s�J��^��list & ���\
		res.setStudent(studentOp.get());
		res.setSubjectList(getStudentSubjectNumber);
		res.setMessage(StudentSubjectRtnCode.SUCCESSFUL.getMessage());
		return res;
	}

	// 8.�z�L�Ұ�s�X�d�߽Ұ��T
	@Override
	public SubjectRes searchSubNumber(String subjectNumber) {
		// �P�_���e�O�_����
		if (!StringUtils.hasText(subjectNumber)) {
			return new SubjectRes(null, StudentSubjectRtnCode.CLASS_REQIRED.getMessage());
		}
		// ����Ʈw��,�Y�L���ҵ{�T���h�^�ǿ��~�T���åB���X�C
		Optional<Subject> subjectOp = subjectDao.findById(subjectNumber);
		if (!subjectOp.isPresent()) {
			return new SubjectRes(null, StudentSubjectRtnCode.CLASS_REQIRED.getMessage());
		}
		// �����ŦX-->��ܽҰ��T&���\
		Subject subject = subjectOp.get();
		return new SubjectRes(subject, StudentSubjectRtnCode.SUCCESSFUL.getMessage());
	}

	// 9.�z�L�Ұ�W�٧�M�Ұ��T
	@Override
	public SubjectRes searchSubjectName(String subjectName) {
		SubjectRes res = new SubjectRes();
		// ���G�榡���~�A�^�ǿ��~�T���åB���X�C
		if (!StringUtils.hasText(subjectName)) {
			return new SubjectRes(null, StudentSubjectRtnCode.CREAT_REQIRED.getMessage());
		}
		// �����ŦX��ܽҰ��T&���\
		List<Subject> subjectlist = subjectDao.findAllBySubjectName(subjectName);
		res.setList(subjectlist);
		return res;
	}

}
