package com.bookstore.utility;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtility {
	private static final String SALT="salt";  //SALT should be protected carefully
	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder(12,new SecureRandom(SecurityUtility.SALT.getBytes()));
	}
	
	@Bean
	public static String RandomPasswordGenerator(){
		String SALTCHARS="ABCDEFGHIJKIMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt=new StringBuilder();
		Random rnd = new Random();
		
		while(salt.length()<18){
			int index=(int) rnd.nextFloat()*SALTCHARS.length();
			salt.append(SALTCHARS.charAt(index));
		}
		
		String saltstr=salt.toString();
		return saltstr;
	}
} 
