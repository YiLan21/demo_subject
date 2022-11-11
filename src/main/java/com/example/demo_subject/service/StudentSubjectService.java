package com.example.demo_subject.service;

import java.util.List;
import java.util.Optional;

import com.example.demo_subject.entity.Student;
import com.example.demo_subject.entity.Subject;
import com.example.demo_subject.vo.StudentRes;
import com.example.demo_subject.vo.SubjectRes;

public interface StudentSubjectService {

	public List<Subject> getSubjectInfo();
	// 1.���o�����ҵ{��T
	// api: /api/SubjectInfo

	public SubjectRes creatSubject(String subNumber, String subName, Integer subDate,Integer startTime, Integer endTime,
			Integer units);
	// 2.�إ߽ҵ{ �ҵ{��T�A�åB�إߨ��b
	// ���D1.�i�H�ק�A���O�O�����л\�쥻���ҵ{��� (���T�w�o�ˬO�_��O�ק�)
	// ���D2.�}�l���ɶ��򵲧����ɶ��O�ϥ�int�h�w�q�L�Ӥ��O��DATE �ѨM :���Integer 
	//
	// api: /api/creatSubject

	public SubjectRes reviseSubject(String subNumber, String subName, Integer subDate, Integer startTime, Integer endTime,
			Integer units);
	// 3.���ҵ{���e
	//    /api/reviseSubject
	//�ק�list /Set /Map ������ƨϥ�remove

	public SubjectRes deletSubject(String subNumber);
	// 4.�R���ҵ{
	// ���D1. �ʧ@������subNumber�@�˦s�b ���O��L����k�s
	// A:�л������w�g�ѨM �A�N�᭱��save�����Y�i
	//
	// api: /api/deletSubject

	public StudentRes createStudent(String stuNumber, String stuName);
	// 5.�إ߾ǥ͸��
	// ���b�d��
	// api: /api/createStudent


	public StudentRes reviseStudentSubject(String stuNumber, String subNumber);
	// 7.�ǥͷs�W�ҵ{ && �ק�ǥͿ��
	// �Q�k--> �إ߾ǥͿ�� -->��J�� �ǥ͸��X ��ҿ諸�ҵ{ ��ӿ�J -->�^�Ƿ|�O Res���ҥ]�t�� �T�� ��list
	// �䤤���b1.��J�ȭn�ŦX��Ʈw�����]�t���ҵ{�N�X 2.�W�Үɶ����i�İ� 3.�Ǥ��Ƥ��o�W�L�Q�Ǥ� 4.���i�ﭫ�ƪ��ҵ{
	// ���D1.�L�k��������Ʈw //�ѨM:Dao �إ�findbyStudent(String subNumber ) -->subNumber
	// �n��by�᭱���@��
	// ���D2.�L�k�g�J�ĤG���� //�ثe�ѨM��k -> String subNumber �令 List<String> subNumberList
	// ���D3.�L�k���W�r�O�_�ۦP �|�@�����L //�ѨM :�]���r����n��.equalsIgnoreCase �쥻�ϥ� ==
	// ���D4.�p�����b���B��J�ҵ{ �A���g�J�ҵ{��b��Ʈw�|��� {" ,a01"} //�ثe�ѨM�Q�k �W�]�@�ӭ�����J��api �j���Ʈw������ơA�ѨM
	// ���D5.�ϥ� -->if (!subjectDao.existsById(subNumber))�i�H�`�٦A�j��
	// ���D
	// api: /api/creatStudentSubject

	public StudentRes deletStudentSubject(String stuNumber, String subNumber);
	// 8.�ǥͧR����� 
	// api: /api/deletStudentSubject

	public List<Student> getStudentInfo();
	// 9.�d�߾ǥͿ�Ҹ��
	// ���D1.�L�k�������ʧ@ ���O�b�����h���O�S������ �ѨM
	// api: /api/studentInfo
	
	public StudentRes findByStuNumber(String stuNumber);
	// 10.�ξǥ�number�M���T
	// ���D1. ���P��Ʈw��class���P �L�k�g�J//�ѨM:�b�ǥͪ�entity�إߤ@��list<subject>
	// ���D2. �u����ܤ@��list //�ثe�Q�k :","���Ϊ���k
	// ���D3.
	// api: /api/findByStuNumber

	public SubjectRes findBySubNumber(String subNumber);
//	public List<Subject>  findBySubNumber(String subNumber);
	// 11.�νҵ{�N�X�d�߽ҵ{��T
	// api: /api/findBySubNumber

	public SubjectRes findBySubName(String subName);
//	public List<Subject>  findBySubName(String subName);
	// 12.�νҵ{�W�٬d�߽ҵ{��T
	// �P�W�٪��ҵ{�|���h�өҥH�^�ǭn��list
	// SubjectRes ���إ�list<Subject>
	// api: /api/findBySubName
	
	

	
	/*�`��Ƶ� :
	 * 1. �bRes ���μg�J�Ҧ���T �i�H�u�a�J private Student student ;
	 * 2. �bget��Ʈw�e �A�N�n���P�_Optional ���S���a�J�� �A(.isPresert )
	 * 3. �R��Set List Map ���O�� remove 
	 * 4. 
	 * */
}
