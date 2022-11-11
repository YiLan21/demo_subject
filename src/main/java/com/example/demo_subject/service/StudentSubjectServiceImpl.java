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

	// 1.���o�����ҵ{��T
	@Override
	public List<Subject> getSubjectInfo() {
		List<Subject> list = subjectDao.findAll();
		return list;
	}

	// 2.�إ߽ҵ{
	@Override
	public SubjectRes creatSubject(String subNumber, String subName, Integer subDate, Integer startTime,
			Integer endTime, Integer units) {
		SubjectRes res = new SubjectRes();
		if (!StringUtils.hasText(subNumber) || !StringUtils.hasText(subName)) {
//			res.setMessage("��J��T���~");
			res.setMessage(StudentSubjectRtnCode.CREAT_REQIRED.getMessage());
			return res;
		}
		if (!StringUtils.hasText(subName)) {
//			res.setMessage("�ҵ{�W�ٿ��~");
			res.setMessage(StudentSubjectRtnCode.CLASS_REQIRED.getMessage());
			return res;
		}
		if (subDate > 6 || subDate < 1) {
//			res.setMessage("��J�P���榡���~");
			res.setMessage(StudentSubjectRtnCode.DATE_REQIRED.getMessage());
			return res;
		}
		if (startTime > 16 || startTime < 9 || startTime >= endTime || startTime == null) {
//			res.setMessage("�W�Үɶ����~");
			res.setMessage(StudentSubjectRtnCode.DATE_REQIRED.getMessage());
			return res;
		}
		if (endTime > 18 || endTime < 11 || endTime == null) {
//			res.setMessage("�����ɶ����~");
			res.setMessage(StudentSubjectRtnCode.DATE_REQIRED.getMessage());
			return res;
		}

		if (units > 3 || units < 1 || units == null) {
//			res.setMessage("�Ǥ����~");
			res.setMessage(StudentSubjectRtnCode.UNITS_REQIRED.getMessage());
			return res;
		}
		Optional<Subject> subOp = subjectDao.findById(subNumber); //
		if (subOp.isPresent()) {
//			res.setMessage("���ҵ{�w�s�b");
			res.setMessage(StudentSubjectRtnCode.HASCLASS_REQIRED.getMessage());
			return res;
		}
		Subject subject = new Subject(subNumber, subName, subDate, startTime, endTime, units);
		subjectDao.save(subject);
		res.setSubject(subject);
//		res.setMessage("���\�إ�");
		res.setMessage(StudentSubjectRtnCode.SUCCESSFUL.getMessage());

		return res;
	}

	// 3.���ҵ{���e //
	public SubjectRes reviseSubject(String subNumber, String subName, Integer subDate, Integer startTime,
			Integer endTime, Integer units) {

		SubjectRes res = new SubjectRes();
		if (!StringUtils.hasText(subNumber) || !StringUtils.hasText(subName)) {
//			res.setMessage("��J��T���~");
			res.setMessage(StudentSubjectRtnCode.CREAT_REQIRED.getMessage());
			return res;
		}

		if (!StringUtils.hasText(subNumber)) {
//			res.setMessage("�ҵ{�N�X�榡���~");
			res.setMessage(StudentSubjectRtnCode.DUBCLASS_REQIRED.getMessage());
			return res;
		}
		Optional<Subject> subOp = subjectDao.findById(subNumber); //
		if (!subOp.isPresent()) {// ���ǥ͸��.if(!)�ǥͪ���T=���Xif(true)�ǥͪ���Tkeep going
//			res.setMessage("���ҵ{���s�b");
			res.setMessage(StudentSubjectRtnCode.NOSTUDENTINFO_REQIRED.getMessage());
			return res;
		}
		if (!StringUtils.hasText(subName)) {
//			res.setMessage("�ҵ{�W�ٿ��~");
			res.setMessage(StudentSubjectRtnCode.DUBCLASS_REQIRED.getMessage());
			return res;
		}
		if (subDate >= 5 || subDate < 1 || units == null) {
//			res.setMessage("��J�P���榡���~");
			res.setMessage(StudentSubjectRtnCode.DATE_REQIRED.getMessage());
			return res;
		}
		if (startTime > 16 || startTime < 9 || startTime >= endTime || startTime == null) {
//			res.setMessage("�W�Үɶ����~");
			res.setMessage(StudentSubjectRtnCode.DATE_REQIRED.getMessage());
			return res;
		}
		if (endTime > 18 || endTime < 11 || endTime == null) {
//			res.setMessage("�����ɶ����~");
			res.setMessage(StudentSubjectRtnCode.DATE_REQIRED.getMessage());
			return res;
		}
		if (units > 3 || units < 1 || units == null) {
//			res.setMessage("�Ǥ����~");
			res.setMessage(StudentSubjectRtnCode.UNITS_REQIRED.getMessage());
			return res;
		}

		Subject subject1 = subOp.get();// �n�A�P�_�S�S���Ȥ���Aget
		Subject subject = new Subject(subNumber, subName, subDate, startTime, endTime, units);

		subjectDao.save(subject);
		res.setSubject(subject1);
		res.setMessage(subNumber + "�ק令�\!!!");
		return res;
	}

	// 4.�R���ҵ{
	@Override
	public SubjectRes deletSubject(String subNumber) {
		SubjectRes res = new SubjectRes();

		if (!StringUtils.hasText(subNumber)) {
//			res.setMessage("�ҵ{�N�X��J���~");
			res.setMessage(StudentSubjectRtnCode.CLASS_REQIRED.getMessage());
			return res;
		} // �p�G�g�J����ƨS���F��N��ܸ�ƿ��� (���b1.)
		if (!subjectDao.existsById(subNumber)) { // �p�G��Ʈw�ح�����Ƹ�ڿ�J����Ʀ��ŦX�h�^�Ǧ�
//			res.setMessage("�d�L���ҵ{~~");
			res.setMessage(StudentSubjectRtnCode.NOCLASSINFO_REQIRED.getMessage());
			return res;
		}
		subjectDao.deleteById(subNumber);
		res.setMessage("�R���ҵ{" + subNumber + "���\");
		return res;
	}

	// 5.�إ߾ǥ͸�T
	@Override
	public StudentRes createStudent(String stuNumber, String stuName) {
		StudentRes res = new StudentRes();

		if (!StringUtils.hasText(stuNumber)) {
//			res.setMessage("�b���榡���~");
			res.setMessage(StudentSubjectRtnCode.DOUSTUDENT_REQIRED.getMessage());
			return res;
		}

		if (!StringUtils.hasText(stuName)) {
//			res.setMessage("�W�r��J���~");
			res.setMessage(StudentSubjectRtnCode.DOUSTUDENT_REQIRED.getMessage());
			return res;
		}
		Optional<Student> stuOp = studentDao.findById(stuNumber);
		if (!stuOp.isPresent()) { // �p�G��Ʈw�ح�����Ƹ�ڿ�J����Ʀ��ŦX�h�^�Ǧ�
			Student student = new Student(stuNumber, stuName);
			res.setStudent(student);
//		res.setMessage("��ƫإߦ��\");
			res.setMessage(StudentSubjectRtnCode.SUCCESSFUL.getMessage());
			studentDao.save(student);
			return res;
		}
//		res.setMessage("���b���w�g�s�b");
		res.setMessage(StudentSubjectRtnCode.DOUSTUDENT_REQIRED.getMessage());
		return res;

	}

	// 7.��� (�[��)
	// ���D 1.�L�k�g�J�ⶵ�ҥ� //�ѨM
	// ���D 2.�����л\�쥻���
	//
	@Override
	public StudentRes reviseStudentSubject(String stuNumber, String subNumber) {// String subNumber �令Set<>()
		// �إ߾ǥͿ��, ���ǥ͸�ƨåB�g�J�L����� (�Ĥ@�B�����}���)
		StudentRes res = new StudentRes();

		if (!StringUtils.hasText(stuNumber)) {
//			res.setMessage("��J�榡���~");
			res.setMessage(StudentSubjectRtnCode.CREAT_REQIRED.getMessage());
			return res;
		}
		if (!StringUtils.hasText(subNumber)) {// .if(!)subNumber=null or -->���X,if(true)keep going
//			res.setMessage("�Ұ��J���~");
			res.setMessage(StudentSubjectRtnCode.CLASS_REQIRED.getMessage());
			return res;
		}

		Optional<Student> stuOp = studentDao.findById(stuNumber);//
		if (!stuOp.isPresent()) {// ���ǥ͸��.if(!)�ǥͪ���T=���Xif(true)�ǥͪ���Tkeep going
//			res.setMessage("���ǥͤ��s�b");
			res.setMessage(StudentSubjectRtnCode.NOSTUDENTINFO_REQIRED.getMessage());
			return res;
		}

		Student student = stuOp.get();// �����o��T
		String stuSub = student.getSubNumber(); // ���X�Ӿǥͭ쥻�����
		String[] stusubArray = null; // �p�G�쥻�ǥ͸�T�Onull
		List<String> suboldList = new ArrayList<>(); // �إߤ@��set�������|���o���ҵ{�N�X
		if (StringUtils.hasText(stuSub)) {
			stusubArray = stuSub.split(","); // ��","����
			for (String item : stusubArray) {// ��foreach�h�� �åB�[�J��set�̭�
				item = item.trim();
				suboldList.add(item); // set�x�s��k add
			}
		}
		List<String> subNewList = new ArrayList<>();
		String[] subNumArray = subNumber.split(",");// �ǥͷs�[�諸��
		for (String item : subNumArray) {
			String str = item.trim();
			subNewList.add(str); // �@�˥h�ťյM����εM����set
		}
		// findaLL ���X�Ҧ��ҵ{��ƥh����J���ҵ{�W�٬O�_�ۦP
		// ��֤�隸��
		List<String> subCheckList = new ArrayList<>();
		for (String item : subNewList) {
			if (suboldList.contains(item)) {
//				res.setMessage("��ҭ��� / ���~ ");
				res.setMessage(StudentSubjectRtnCode.DUBCLASS_REQIRED.getMessage());
				return res;
			}
			subCheckList.add(item);

		}

		List<Subject> subjectd = subjectDao.findAllById(subCheckList);//
		List<String> strSubList = new ArrayList<>();// ��Ʈw��SubNumber
		for (Subject str : subjectd) {
			strSubList.add(str.getSubNumber());
		}
		for (String item : subCheckList) {
			if (!strSubList.contains(item)) {
//				res.setMessage("�d�L����~~");
				res.setMessage(StudentSubjectRtnCode.NOCLASSINFO_REQIRED.getMessage());
				return res;
			}
			suboldList.add(item);

		}

//		List<Subject> stusub = subjectDao.findAllById(subCheckList);// �쥻��
		boolean checkclass = checkclassName(subjectd);
		if (checkclass) {
//			res.setMessage("�ҵ{�W�٭���!!!!!");
			res.setMessage(StudentSubjectRtnCode.DUBCLASS_REQIRED.getMessage());
			return res;
		}

		boolean timeCheck = subjectTime(subjectd);
		if (timeCheck) {
//			res.setMessage("�ɶ��İ�!!!!!");
			res.setMessage(StudentSubjectRtnCode.DUBCLASSADD_REQIRED.getMessage());
			return res;
		}

		int totunit = 0;
		for (Subject item2 : subjectd) {
			totunit += item2.getUnits();

		}

		if (totunit > 10) {
//			res.setMessage("�w�g�W�L10�Ǥ����o���");
			res.setMessage(StudentSubjectRtnCode.OVERUNITS_REQIRED.getMessage());
			return res;

		} else {
			res.setMessage("�`�Ǥ�: " + totunit);
		}

		// Subject subject = subOp.get();

		student.setSubNumber(suboldList.toString().substring(1, (suboldList.toString().length() - 1)));
		studentDao.save(student);
//		res.setMessage("��Ҧ��\!");
		res.setMessage(StudentSubjectRtnCode.SUCCESSFUL.getMessage());
		return res;
	}

	// 8.�R���ǥͿ��
	// ���D1. �u��R���Ĥ@��
	@Override
	public StudentRes deletStudentSubject(String stuNumber, String subNumber) {
		// TODO Auto-generated method stub
		StudentRes res = new StudentRes();
		if (!StringUtils.hasText(stuNumber)) {
//			res.setMessage("��J��T���~ /�u��R�����ҵ{");
			res.setMessage(StudentSubjectRtnCode.DELET_REQIRED.getMessage());
		}
		if (!StringUtils.hasText(subNumber)) {// .if(!)subNumber=null or -->���X,if(true)keep going
//			res.setMessage("�Ұ��J���~");
			res.setMessage(StudentSubjectRtnCode.CLASS_REQIRED.getMessage());
			return res;
		}

		Optional<Student> stuOp = studentDao.findByStuNumber(stuNumber);
		if (!stuOp.isPresent()) {// ���ǥ͸��.if(!)�ǥͪ���T=���Xif(true)�ǥͪ���Tkeep going
//			res.setMessage("���ǥͤ��s�b");
			res.setMessage(StudentSubjectRtnCode.NOSTUDENTINFO_REQIRED.getMessage());
			return res;
		}
		List<String> oldSubjectList = new ArrayList<>();
		Student student = stuOp.get();// �����o��T
		String stuSub = student.getSubNumber(); // ���X�Ӿǥͭ쥻�����
		String[] deletSubjectArray = stuSub.split(","); // ���Φr�� �M����}�a�JSet
		for (String delet : deletSubjectArray) {
			String deletStr = delet.trim();// �[�J���r��h�ť�
			oldSubjectList.add(deletStr);
		}
		if (oldSubjectList.isEmpty()) {
//	     	res.setMessage("�R������");
//		    return res;
			res.setMessage(StudentSubjectRtnCode.DELET_REQIRED.getMessage());
			return res;
		}
		List<String> reSubjectlist = new ArrayList<>();
		for (String deletsub : oldSubjectList) {
			if (!deletsub.equalsIgnoreCase(subNumber)) {

				reSubjectlist.add(subNumber);
//				res.setMessage("�R�����\");

			}
		}
		res.setMessage(StudentSubjectRtnCode.SUCCESSFUL.getMessage());
		student.setSubNumber(reSubjectlist.toString().substring(1, (reSubjectlist.toString().length() - 1)));
		studentDao.save(student);
		return res;
	}

	// 9.�ǥ͸�T
	@Override
	public List<Student> getStudentInfo() {
//		List<Student> list = studentDao.findAll();
		return studentDao.findAll();
	}

	// 10.�ξǸ��d�߾ǥͿ��
	@Override // �ݭק���{ res.setList1(list1);
	public StudentRes findByStuNumber(String stuNumber) {
		StudentRes res = new StudentRes();

		if (!StringUtils.hasText(stuNumber)) {
//			res.setMessage("��J�榡���~");
//			return res;
			res.setMessage(StudentSubjectRtnCode.CREAT_REQIRED.getMessage());
			return res;
		}
		Optional<Student> studentOp = studentDao.findById(stuNumber);
		if (!studentOp.isPresent()) {
//			res.setMessage("�d�L���ǥ͸�T");
//			return res;
			res.setMessage(StudentSubjectRtnCode.NOSTUDENTINFO_REQIRED.getMessage());
			return res;
		}
		String subNumber = studentOp.get().getSubNumber();// �q��Ʈw���X�ǥͭ쥻�����
		List<String> subnuList = new ArrayList<>();
		String[] subNumArray = subNumber.split(",");
		for (String item : subNumArray) {
			String str = item.trim();
			subnuList.add(str); // �@�˥h�ťյM����εM����set
		} // -->�{�bgetStudentSubNumber �̭��w�g�s�FstuNumber�����o��subjectNumber
		List<Subject> getStudentSubNumber = new ArrayList<>();
		// �W���k�ǳ��x�s�������ΥX�Ӫ�String
		for (String item : subnuList) {
			Optional<Subject> subop = subjectDao.findBySubNumber(item);
			Subject stusubcheck = subop.get();
			getStudentSubNumber.add(stusubcheck);
		}
		res.setStudent(studentOp.get());
		res.setSubjectList(getStudentSubNumber);
//		res.setMessage("�d�ߦ��\");
//		return res;
		res.setMessage(StudentSubjectRtnCode.SUCCESSFUL.getMessage());
		return res;

	}

	// 11.�z�L�Ұ�s�X�d�߽Ұ��T
	@Override
	public SubjectRes findBySubNumber(String subNumber) {
		SubjectRes res = new SubjectRes();
		if (!StringUtils.hasText(subNumber)) {
//			res.setMessage("��J�榡���~");
//			return res;
			return res = new SubjectRes(null, StudentSubjectRtnCode.CREAT_REQIRED.getMessage());

		}
		Optional<Subject> subOp = subjectDao.findById(subNumber);

		if (!subOp.isPresent()) {
//			res.setMessage("�d�L���ҵ{");
//			return res;
			return res = new SubjectRes(null, StudentSubjectRtnCode.CLASS_REQIRED.getMessage());

		}
//		
		res.setSubject(subOp.get());
//		res.setMessage("�d�ߦ��\");
		return res = new SubjectRes(null, StudentSubjectRtnCode.SUCCESSFUL.getMessage());

	}

	// 12.�z�L�Ұ�W�٧�M�Ұ��T
	@Override
	public SubjectRes findBySubName(String subName) {
		SubjectRes res = new SubjectRes();
		if (!StringUtils.hasText(subName)) {
//			res.setMessage("��J�榡���~");
//			return res;
			return new SubjectRes(null, StudentSubjectRtnCode.CREAT_REQIRED.getMessage());
		}
		List<Subject> sublist = subjectDao.findAllBySubName(subName);
		res.setList(sublist);
		return res;
	}

	/* ===========================�P�_�ɶ�============================ */
	private boolean subjectTime(List<Subject> stusub) {
//		List<Subject> stusub = subjectDao.findAllById(subNumber);
// 		��Ҧ�����ƥ��list
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

	
	/* ===================�P�_�ҵ{���S������==================== */
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
