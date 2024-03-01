package com.myproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myproject.entities.UserInfo;
import com.myproject.repository.UserInfoRepo;

@Service
public class UserInfoService {
	@Autowired
	private UserInfoRepo userInfoRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserInfo addUser(UserInfo userInfo) {
		
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		
		return userInfoRepo.save(userInfo);
	}
}
