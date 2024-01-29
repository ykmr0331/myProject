package com.fifa.user.exception;

public class EmailMismatchException extends Exception {
	private Object data;
	public EmailMismatchException(String msg) {
		super(msg);
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}