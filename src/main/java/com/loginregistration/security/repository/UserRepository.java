package com.loginregistration.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loginregistration.security.entity.User;

public interface UserRepository  extends JpaRepository<User,Integer>{

	public User findByEmail(String email); 
}
