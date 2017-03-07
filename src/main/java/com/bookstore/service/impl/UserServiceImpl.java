package com.bookstore.service.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.domain.User;
import com.bookstore.domain.security.PasswordResetToken;
import com.bookstore.domain.security.UserRole;
import com.bookstore.repository.PasswordResetTokenRepository;
import com.bookstore.repository.RoleRepository;
import com.bookstore.repository.UserRepository;
import com.bookstore.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	@Autowired
	public PasswordResetTokenRepository passwordResetTokenRepository;
	
	@Override
	public PasswordResetToken getPasswordResetToken(String token) {
		return passwordResetTokenRepository.findByToken(token);
	}

	@Override
	public void createPasswordResetToken(User user, String token) {
		// TODO Auto-generated method stub
		final PasswordResetToken myToken= new PasswordResetToken(token,user);
		passwordResetTokenRepository.save(myToken);
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}

	@Override
	public User findById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findOne(id);
	}

	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		// TODO Auto-generated method stub
		User localUser=userRepository.findByUsername(user.getUsername());
		if(localUser!=null){
			LOG.info("user {} already exists. Nothing will be done",user.getUsername());
		}else{
			for(UserRole ur: userRoles){
				roleRepository.save(ur.getRole());
			}
			
			//link roles with this user, then save this user in database
			user.getUserRoles().addAll(userRoles);
			
			localUser=userRepository.save(user);
		}
		
		return localUser;
	}

}
