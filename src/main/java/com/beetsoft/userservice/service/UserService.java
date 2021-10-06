package com.beetsoft.userservice.service;

import com.beetsoft.userservice.exception.ValidateException;
import com.beetsoft.userservice.model.request.UserRequest;
import com.beetsoft.userservice.model.response.UserResponse;

public interface UserService {
	
	UserResponse getList(String role, String name, Integer offset, Integer limit);
	
	UserResponse getDetail(Long id);
	
	UserResponse create(UserRequest user) throws ValidateException;
	
	UserResponse update(Long id, UserRequest userReq) throws ValidateException;
	
	UserResponse delete(Long[] id);

}
