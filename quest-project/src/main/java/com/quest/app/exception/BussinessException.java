package com.quest.app.exception;

public class BussinessException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private String code;
	private String message;
	
	
	public BussinessException(String code, String message) {
		this.code = code;
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}
