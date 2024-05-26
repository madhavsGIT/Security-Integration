package com.acciojob.Security.Integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SecurityIntegrationApplication {



	public static void main(String[] args) {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println("Encoded version of password " +
				"is " + passwordEncoder.encode("password"));





		SpringApplication.run(SecurityIntegrationApplication.class, args);
	}



}
