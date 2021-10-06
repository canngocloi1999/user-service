package com.beetsoft.userservice.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.beetsoft.userservice.model.enums.RoleEnum;
import com.beetsoft.userservice.model.enums.StatusEnum;

import lombok.Data;

@Data
public class UserRequest {
	
	@NotBlank
	@Size(min = 2, max = 30)
	private String fullname;
	
	@NotBlank
	@Email
	@Size(min = 10, max = 30)
	private String login_id;
	
	@NotBlank
	@Email
	@Size(min = 10, max = 30)
	private String email;
	
	@Size(min = 10, max = 20)
	private String mobileNO;
	
	@NotNull
	@Size(min = 10, max = 20)
	private String phoneNO;
	
	@NotNull
	private RoleEnum role;
	
	@NotNull
	private StatusEnum status;

}
