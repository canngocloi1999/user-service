package com.beetsoft.userservice.model.response;

import lombok.Data;

@Data
public abstract class Response<T> {

	private int status;
	private String message;
	private int quantity;
	private T responseBody;
	private String error;

	public Response(int status, String message, int quantity, T responseBody, String error) {
		this.status = status;
		this.message = message;
		this.quantity = quantity;
		this.responseBody = responseBody;
		this.error = error;
	}

}
