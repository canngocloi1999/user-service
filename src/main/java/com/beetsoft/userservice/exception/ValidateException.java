package com.beetsoft.userservice.exception;

import java.util.Map;

public class ValidateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code;
	private Map<String, String> errors;

	public ValidateException(int code, Map<String, String> errors) {
		this.code = code;
		this.errors = errors;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

}
