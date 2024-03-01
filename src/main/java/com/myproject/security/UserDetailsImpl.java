package com.myproject.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.myproject.entities.UserInfo;

@Component
public class UserDetailsImpl implements UserDetails {

	private UserInfo userInfo;
	
	
	public UserDetailsImpl(UserInfo user) {
		super();
		this.userInfo=user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
			
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userInfo.getRoles());
		return List.of(simpleGrantedAuthority);
	}

	@Override
	public String getPassword() {
		return userInfo.getPassword();
	}

	@Override
	public String getUsername() {

		return userInfo.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
