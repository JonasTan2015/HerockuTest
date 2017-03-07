package com.bookstore;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bookstore.domain.User;
import com.bookstore.domain.security.Role;
import com.bookstore.domain.security.UserRole;
import com.bookstore.service.UserService;
import com.bookstore.utility.SecurityUtility;

@SpringBootApplication
public class BookstoreApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		User user1=new User();
		user1.setFirstName("John");
		user1.setLastName("Adams");
		user1.setUsername("John Adams");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("12345"));
		user1.setEmail("jonastanstevens2017@gmail.com");
		Set<UserRole> userRoles = new HashSet<UserRole>();
		Role role1 = new Role();
		role1.setRoleId(1);
		role1.setName("ROLE_USER");
		userRoles.add(new UserRole(user1,role1));
		userService.createUser(user1,userRoles);
	}
}
