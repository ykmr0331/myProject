package com.fifa.user.exception.member;

public class PasswordMismatchException extends Exception {
	private Object data;
	public PasswordMismatchException(String msg) {
		super(msg);
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}
