package com;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.entity.Login;
import com.repository.LoginRepository;

import jakarta.annotation.PostConstruct;

@SpringBootApplication (scanBasePackages="com")
@EntityScan(basePackages = "com.entity")
@EnableJpaRepositories(basePackages = "com.repository")
@EnableDiscoveryClient

public class UdenkemFoodDeliveryApplication {

	@Autowired
	LoginRepository loginRepository;
	
	@PostConstruct
	public void init() {
		Optional<Login> res = loginRepository.findById("udeobim@gmail.com");
		if(res.isPresent()) {
			
		}else {
			Login ll = new Login();
			ll.setEmailid("udeobim@gmail.com");
			ll.setPassword("udeobim@123");
			ll.setTypeofuser("admin");
			loginRepository.save(ll);
			System.out.println("Admin account created successfully...");
		}
		
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(UdenkemFoodDeliveryApplication.class, args);
		System.err.println("Udenkem Kitchen Micro Service is running on the port number 9090");
	}

}
