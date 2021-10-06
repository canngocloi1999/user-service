package com.beetsoft.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.beetsoft.userservice.exception.ValidateException;
import com.beetsoft.userservice.model.request.UserRequest;
import com.beetsoft.userservice.model.response.UserResponse;
import com.beetsoft.userservice.service.UserService;

@RestController
public class UsercontrollerImpl implements UserController{
	
	@Autowired
	private UserService userService;

	@Override
	public ResponseEntity<UserResponse> getListUser(String role, String name, Integer offset, Integer limit) {
		UserResponse user = userService.getList(role, name, offset, limit);
		if (user.getError() == null) {
			return new ResponseEntity<UserResponse>(user,HttpStatus.OK);
		}
		return new ResponseEntity<UserResponse>(user,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<UserResponse> getUserDetail(Long id) {
		UserResponse user = userService.getDetail(id);
		if (user.getStatus() == 200) {
			return new ResponseEntity<UserResponse>(user,HttpStatus.OK);
		}
		return new ResponseEntity<UserResponse>(user,HttpStatus.valueOf(user.getStatus()));
	}

	@Override
	public ResponseEntity<UserResponse> createUser(UserRequest requestUser) throws ValidateException {
		UserResponse user = userService.create(requestUser);
		if (user.getError() == null) {
			return new ResponseEntity<UserResponse>(user,HttpStatus.OK);
		}
		return new ResponseEntity<UserResponse>(user,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<UserResponse> updateUser(Long id, UserRequest requestUser) throws ValidateException {
		UserResponse user = userService.update(id ,requestUser);
		if (user.getStatus() == 200) {
			return new ResponseEntity<UserResponse>(user,HttpStatus.OK);
		}
		return new ResponseEntity<UserResponse>(user,HttpStatus.valueOf(user.getStatus()));
	}

	@Override
	public ResponseEntity<UserResponse> deleteUser(Long[] id) {
		UserResponse user = userService.delete(id);
		if (user.getStatus() == 200) {
			return new ResponseEntity<UserResponse>(user,HttpStatus.OK);
		}
		return new ResponseEntity<UserResponse>(user,HttpStatus.valueOf(user.getStatus()));
//		return null;
	}
	
	

}
