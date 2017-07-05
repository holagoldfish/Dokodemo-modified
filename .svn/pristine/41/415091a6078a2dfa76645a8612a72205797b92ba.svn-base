package com.zendaimoney.Dokodemo.exception;

public class ExceptionCode {

	private int code = -1;// 未设置
	private String description;

	public String getDescription() {
		return description;
	}

	public int getCode() {
		return code;
	}

	private ExceptionCode(int code, String desc) {
		this.code = code;
		this.description = desc;
	}

	public static final ExceptionCode XML_PARSE_ERROR = new ExceptionCode(1,
			"解析XML PageModel异常");
}
