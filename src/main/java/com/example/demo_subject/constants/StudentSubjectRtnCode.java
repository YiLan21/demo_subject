package com.example.demo_subject.constants;

public enum StudentSubjectRtnCode {

	SUCCESSFUL("200", "SUCCESSFUL"),               // �ʧ@���\

	CREAT_REQIRED("400", "CREAT FAIL"),           // �إߥ���

	IMPORT_FAIL("400", "Student / Subject INFO FAIL"),           // ��J�ǥ� / ��ҰT�����~

	STUDENT_SUBJECT_NULL("400", "STUDENT SUBJECT INFO FAIL"),  // �إ߿�ҥ���

	DELET_REQIRED("400", "DELET FAIL"),           // �R������

	CLASS_REQIRED("400", "CLASS FAIL"),           // �ҵ{��T���~

	DUBCLASS_REQIRED("400", "DOUBLE CLASS"),       // ���ƿ��

	DUBCLASSTIME_REQIRED("400", "IN CLASS"),       // �İ�

	DATE_REQIRED("400", "TIME FAIL"),              // �ɶ��榡���~

	UNITS_REQIRED("400", "CLASS UNITS FAIL"),      // �Ǥ����~

	OVERUNITS_REQIRED("400", "OVER UNITS"),         // �W�B���

	EXISTSSTUDENT_REQIRED("400", "STUDENT EXISTS"),    // ���Ǹ��ϥΤ��A�L�k�إ߷s���ǥ͸�T

	HASCLASS_REQIRED("400", "SUBJECT EXISTS"),      // ���ҵ{�ϥΤ��A�L�k�إ߷s���ǥ͸�T

	NOCLASS_REQIRED("400", "SUBJECT NOT EXISTS"),   //���s�b���ҵ{
	
	NOSTUDENTINFO_REQIRED("400","NOT EXISTS"),     //���s�b���ǥ�

	;

	private String code;
	private String message;

	private StudentSubjectRtnCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
