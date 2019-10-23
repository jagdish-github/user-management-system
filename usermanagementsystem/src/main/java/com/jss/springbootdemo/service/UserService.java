package com.jss.springbootdemo.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.jss.springbootdemo.entity.OperationStatus;
import com.jss.springbootdemo.entity.User;

public interface UserService extends UserDetailsService {
	User createUser(User user);

	User getUserByUserId(String userId);

	OperationStatus deleteUser(String userId);

	User updateUser(String userId, User user);
	
	User getUserByUsername(String userName);
	
	List<User> getAllUsers();
	
	User getUserByEmailAndFirstName(String email, String firstName);
	
	User findUserByEmail(String email);
}
