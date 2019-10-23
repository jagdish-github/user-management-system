package com.jss.springbootdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jss.springbootdemo.entity.ErrorMessages;
import com.jss.springbootdemo.entity.OperationResult;
import com.jss.springbootdemo.entity.OperationStatus;
import com.jss.springbootdemo.entity.User;
import com.jss.springbootdemo.exceptions.UserServiceException;
import com.jss.springbootdemo.repository.UserRepository;
import com.jss.springbootdemo.utility.Utils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private Utils utils;

	@Override
	public User createUser(User user) {

		if (userRepository.findUserByEmail(user.getEmail()) != null) {
			throw new UserServiceException("Record already exists");
		}

		String publicUserId = utils.generateUserId(30);
		user.setUserId(publicUserId);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()).trim());

		User savedUser = userRepository.save(user);

		return savedUser;
	}

	@Override
	public User getUserByUserId(String userId) {
		User returnedUser = userRepository.findByUserId(userId);

		if (returnedUser == null) {
			throw new UsernameNotFoundException("User with ID " + userId + " not fond");
		}

		return returnedUser;
	}

	@Override
	public OperationStatus deleteUser(String userId) {
		OperationStatus operationStatus = new OperationStatus();
		User user = userRepository.findByUserId(userId);
		userRepository.delete(user);

		operationStatus.setOperationName(HttpMethod.DELETE);
		if (user != null) {
			operationStatus.setOperationStatus(OperationResult.SUCCESS);
		} else {
			operationStatus.setOperationStatus(OperationResult.SUCCESS);
		}
		return operationStatus;
	}

	@Override
	public User updateUser(String userId, User user) {
		User returnedUser = userRepository.findByUserId(userId);

		if (returnedUser == null) {
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getMessage());
		}

		returnedUser.setEmail(user.getEmail());

		userRepository.save(returnedUser);

		return returnedUser;
	}

	@Override
	public User getUserByUsername(String userName) {
		User user = userRepository.findUserByEmail(userName);

		if (user == null) {
			throw new UsernameNotFoundException(userName);
		}

		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userEntity = userRepository.findUserByEmail(username);

		if (userEntity == null)
			throw new UsernameNotFoundException(username);

		return new org.springframework.security.core.userdetails.User(userEntity.getEmail(),
				userEntity.getPassword(), new ArrayList<>());

		// return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new
		// ArrayList<>());
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = userRepository.findAllUsers();
		return users;
	}

	@Override
	public User getUserByEmailAndFirstName(String email, String firstName) {
		User user = userRepository.getUserByEmailAndFirstName(email, firstName);
		return user;
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

}
