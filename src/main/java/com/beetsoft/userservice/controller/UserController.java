package com.beetsoft.userservice.controller;


import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.beetsoft.userservice.exception.ValidateException;
import com.beetsoft.userservice.model.request.UserRequest;
import com.beetsoft.userservice.model.response.UserResponse;

public interface UserController {
	
	@GetMapping(value = "/user")
	ResponseEntity<UserResponse> getListUser(@RequestParam(required = true) String role , @RequestParam(required = false) String name, @RequestParam(required = false) Integer offset, @RequestParam(required = false) Integer limit);
	
	@GetMapping(value = "/user/{id}")
	ResponseEntity<UserResponse> getUserDetail(@PathVariable("id") Long id);
	
	@PostMapping(value = "/user")
	ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest requestUser) throws ValidateException;
	
	@PatchMapping(value = "/user/{id}")
	ResponseEntity<UserResponse> updateUser(@PathVariable("id") Long id, @RequestBody UserRequest requestUser) throws ValidateException;
	
	@DeleteMapping(value = "/user")
	ResponseEntity<UserResponse> deleteUser(@RequestBody Long[] id);

}
