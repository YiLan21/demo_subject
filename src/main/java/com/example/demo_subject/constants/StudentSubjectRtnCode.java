package com.example.demo_subject.constants;

public enum StudentSubjectRtnCode {

	SUCCESSFUL("200", "SUCCESSFUL"),               // 動作成功

	CREAT_REQIRED("400", "CREAT FAIL"),           // 建立失敗

	IMPORT_FAIL("400", "Student / Subject INFO FAIL"),           // 輸入學生 / 選課訊息錯誤

	STUDENT_SUBJECT_NULL("400", "STUDENT SUBJECT INFO FAIL"),  // 建立選課失敗

	DELET_REQIRED("400", "DELET FAIL"),           // 刪除失敗

	CLASS_REQIRED("400", "CLASS FAIL"),           // 課程資訊錯誤

	DUBCLASS_REQIRED("400", "DOUBLE CLASS"),       // 重複選課

	DUBCLASSTIME_REQIRED("400", "IN CLASS"),       // 衝堂

	DATE_REQIRED("400", "TIME FAIL"),              // 時間格式錯誤

	UNITS_REQIRED("400", "CLASS UNITS FAIL"),      // 學分錯誤

	OVERUNITS_REQIRED("400", "OVER UNITS"),         // 超額選課

	EXISTSSTUDENT_REQIRED("400", "STUDENT EXISTS"),    // 此學號使用中，無法建立新的學生資訊

	HASCLASS_REQIRED("400", "SUBJECT EXISTS"),      // 此課程使用中，無法建立新的學生資訊

	NOCLASS_REQIRED("400", "SUBJECT NOT EXISTS"),   //不存在此課程
	
	NOSTUDENTINFO_REQIRED("400","NOT EXISTS"),     //不存在此學生

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
