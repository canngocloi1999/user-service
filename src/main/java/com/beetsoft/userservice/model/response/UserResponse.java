package com.beetsoft.userservice.model.response;

import java.util.List;

import com.beetsoft.userservice.model.entity.User;

public class UserResponse extends Response<List<User>>{

	public UserResponse(int status, String message, int quantity, List<User> responseBody, String error) {
		super(status, message, quantity, responseBody, error);
		// TODO Auto-generated constructor stub
	}

	

}
