package com.beetsoft.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beetsoft.userservice.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
