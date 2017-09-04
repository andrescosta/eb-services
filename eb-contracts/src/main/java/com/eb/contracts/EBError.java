package com.eb.contracts;

public class EBError {
	String message;
	String code;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public EBError(String message, String code) {
		super();
		this.message = message;
		this.code = code;
	}
	
}
