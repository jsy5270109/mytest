package com.sm.exception;

public class BusinessException extends Exception {

	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public BusinessException(String msg) {
		super();
		this.msg = msg;
	}
	
	
	
	
	
}
