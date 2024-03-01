package com.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.entities.UserInfo;
import com.myproject.service.UserInfoService;

@RestController
@RequestMapping("/user")
public class UserInfoController {

	//This class is for adding the user in the database.

	@Autowired
	private UserInfoService userInfoService;
	
	@PostMapping("/add_user")
	public String addUser(@RequestBody UserInfo userInfo) {
		
		UserInfo user1 = userInfoService.addUser(userInfo);
		return "user added successfully";
	}
}
