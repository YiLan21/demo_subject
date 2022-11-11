package com.example.demo_subject.constants;

public enum StudentSubjectRtnCode {

	SUCCESSFUL("200", "成功"), 
	CREAT_REQIRED("400", "輸入格式錯誤"), 
	DELET_REQIRED("400", "刪除課程失敗"), 
	CLASS_REQIRED("400", "課程錯誤"),
	DUBCLASS_REQIRED("400", "課程重複/資料錯誤"),
	DUBCLASSADD_REQIRED("400", "衝堂"),
	DATE_REQIRED("400", "時間格式錯誤"), 
	UNITS_REQIRED("400", "學分錯誤"), 
	OVERUNITS_REQIRED("400", "學分超過"), 
	DOUSTUDENT_REQIRED("400", "學生資料重複/格式錯誤"), 
	HASCLASS_REQIRED("400", "課程已存在"), 
	NOCLASSINFO_REQIRED("400", "查無此課程"), 
	NOSTUDENTINFO_REQIRED("400", "查無此資訊"), 


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
