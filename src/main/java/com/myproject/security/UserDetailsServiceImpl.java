package com.myproject.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.myproject.entities.UserInfo;
import com.myproject.repository.UserInfoRepo;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserInfoRepo userInfoRepo;
	
	@Autowired
	UserDetailsImpl UserDetailsImpl;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		 UserInfo userInfo = userInfoRepo.findByuserName(username).orElseThrow(() ->new RuntimeException("user not found"));
		
		UserDetailsImpl userDetailsImpl=new UserDetailsImpl(userInfo);
		return userDetailsImpl;
	}

}
