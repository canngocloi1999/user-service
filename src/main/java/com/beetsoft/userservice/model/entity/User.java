package com.beetsoft.userservice.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.beetsoft.userservice.model.enums.RoleEnum;
import com.beetsoft.userservice.model.enums.StatusEnum;

import lombok.Data;

@Data
@Entity
@Table(name = "user", schema = "public")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 50, nullable = false)
	private String fullname;
	
	@Column(length = 50, nullable = false)
	private String login_id;
	
	@Column(length = 100, nullable = false)
	private String email;
	
	@Column(length = 20, nullable = true)
	private String mobileNO;
	
	@Column(length = 20, nullable = false)
	private String phoneNO;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private RoleEnum role;

}
