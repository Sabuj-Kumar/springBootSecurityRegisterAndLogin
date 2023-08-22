package com.loginregistration.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.loginregistration.security.entity.User;
import com.loginregistration.security.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public User saveUser(User user) {
	    
		String password = passwordEncoder.encode(user.getPassword());
		
		user.setPassword(password);
		user.setRole("ROLE_USER");
		User newUser = userRepo.save(user);
		
		return newUser;
	}

	@Override
	public void removeSessionMassege() {
	 
		HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();		
	    
		session.removeAttribute("msg");
	}
}
