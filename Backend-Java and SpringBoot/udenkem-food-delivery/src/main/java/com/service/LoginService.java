package com.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Login;
import com.repository.LoginRepository;

@Service
public class LoginService {
	
	@Autowired
	LoginRepository loginRepository;
	
	public String SignUp(Login login) {
		Optional<Login> res = loginRepository.findById(login.getEmailid());
		if(res.isPresent()) {
			return "Sign-Up Denied!; account details already exists, try again!";
		}else {
			if(login.getTypeofuser().equals("admin")) {
				return "Account creation denied!, you are not allowed to create an admin account!";
			}else {
				loginRepository.save(login);
				return "Sign-Up Accepted!, account created successfully!";
				
			}
		}
	}
	
	public String SignIn(Login login) {
		Optional<Login> res = loginRepository.findById(login.getEmailid());
		if(res.isPresent()) {
			Login ll = res.get();
			if(ll.getPassword().equals(login.getPassword())){
				if(ll.getTypeofuser().equals("admin") && login.getTypeofuser().equals("admin")) {
					return "Access Accepted!, Admin login successfully!";
				}else if(!ll.getTypeofuser().equals("admin") && login.getTypeofuser().equals("admin")){
					return "Access Denied!, You are not an Admin!";
				}else {
					return "Access Accepted!, Customer login successfully!";
				}
			}else {
				return "Access Denied!, your email id or password is incorrect!";
			}
		}else {
			return "Access Denied!, your email id or password is incorrect!";
		}
	}

}
