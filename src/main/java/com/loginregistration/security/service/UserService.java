package com.loginregistration.security.service;

import com.loginregistration.security.entity.User;

public interface UserService {

	public User saveUser(User user);
	public void removeSessionMassege();
}
