package com.bookstore.service;

import java.util.Set;

import com.bookstore.domain.User;
import com.bookstore.domain.security.PasswordResetToken;
import com.bookstore.domain.security.UserRole;

public interface UserService {
	public PasswordResetToken getPasswordResetToken(final String token);
		
	void createPasswordResetToken(final User user, final String token);
	
	public User findByUsername(String username);
	
	public User findByEmail(String email);
	
	public User findById(Long id);

	public User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
}
