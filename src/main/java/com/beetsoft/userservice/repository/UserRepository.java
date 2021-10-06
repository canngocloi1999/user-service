package com.beetsoft.userservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.beetsoft.userservice.model.entity.User;
import com.beetsoft.userservice.model.enums.RoleEnum;

public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query(" from User e where (:role is null or e.role = :role) and (:name is null or e.fullname like %:name%)")
    Page<User> findUser(@Param("role") RoleEnum role,
    					@Param("name") String name,
                        Pageable pageable);

}
