package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Login;
import com.service.LoginService;

import jakarta.ws.rs.core.MediaType;

@RestController
@RequestMapping("login")
@CrossOrigin				//allow to access rest api by frontend technology
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@PostMapping(value="signin",consumes= MediaType.APPLICATION_JSON)
	public String SignIn(@RequestBody Login login) {
		return loginService.SignIn(login);
	}
	
	@PostMapping(value="signup",consumes= MediaType.APPLICATION_JSON)
	public String SignUp(@RequestBody Login login) {
		return loginService.SignUp(login);
		
		
	}

}
