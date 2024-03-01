package com.myproject.controller;

import com.myproject.security.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.entities.JwtRequest;
import com.myproject.entities.JwtResponse;
import com.myproject.security.jwt.JwtHelper;

@RestController
@Slf4j
@AllArgsConstructor
public class JwtAuthenticationController {

	//This class is for authenticate the token whether token is genuine or not

	private UserDetailsService userDetailsService;

	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtHelper jwtHelper;
	
	@PostMapping("/authenticate")
	public String authenticateAndGetToken(@RequestBody JwtRequest jwtRequest){
		//authenticate

		log.info("username: {},password: {}",jwtRequest.getUsername(),jwtRequest.getPassword());
		Authentication authentication=
				authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));

		if (authentication.isAuthenticated()){
			return jwtHelper.generateToken(jwtRequest.getUsername());
		}else{
			throw new UsernameNotFoundException("Invalid user request !!");
		}
	}

}
