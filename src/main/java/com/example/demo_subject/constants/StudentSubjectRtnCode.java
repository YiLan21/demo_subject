package com.example.demo_subject.constants;

public enum StudentSubjectRtnCode {

	SUCCESSFUL("200", "���\"), 
	CREAT_REQIRED("400", "��J�榡���~"), 
	DELET_REQIRED("400", "�R���ҵ{����"), 
	CLASS_REQIRED("400", "�ҵ{���~"),
	DUBCLASS_REQIRED("400", "�ҵ{����/��ƿ��~"),
	DUBCLASSADD_REQIRED("400", "�İ�"),
	DATE_REQIRED("400", "�ɶ��榡���~"), 
	UNITS_REQIRED("400", "�Ǥ����~"), 
	OVERUNITS_REQIRED("400", "�Ǥ��W�L"), 
	DOUSTUDENT_REQIRED("400", "�ǥ͸�ƭ���/�榡���~"), 
	HASCLASS_REQIRED("400", "�ҵ{�w�s�b"), 
	NOCLASSINFO_REQIRED("400", "�d�L���ҵ{"), 
	NOSTUDENTINFO_REQIRED("400", "�d�L����T"), 


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
