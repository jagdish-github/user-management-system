package com.jss.springbootdemo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jss.springbootdemo.entity.OperationStatus;
import com.jss.springbootdemo.entity.User;
import com.jss.springbootdemo.entity.UserRest;
import com.jss.springbootdemo.service.UserService;
import com.jss.springbootdemo.utility.ObjectMapperUtils;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserRest saveUser(@RequestBody User user) {
		User savedUser = new User();
		if (user != null) {
			savedUser = userService.createUser(user);
		}

		UserRest returnValue = ObjectMapperUtils.map(savedUser, UserRest.class);
		return returnValue;
	}

	@ApiOperation(value="Get User Details Web Service Endpoint", notes="${userController.GetUser.ApiOperation.Notes}")
	@ApiImplicitParams({@ApiImplicitParam(name="Authorization", value="${userController.authorizationHeader.description}", paramType="header")})	
	@GetMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserRest getUserByUserId(@PathVariable("userId") String userId) {
		User returnedUser = userService.getUserByUserId(userId);

		UserRest returnValue = ObjectMapperUtils.map(returnedUser, UserRest.class);
		return returnValue;
	}

	@ApiImplicitParams({@ApiImplicitParam(name="Authorization", value="${userController.authorizationHeader.description}", paramType="header")})
	@DeleteMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public OperationStatus deleteUser(@PathVariable("userId") String userId) {
		OperationStatus operationStatus = userService.deleteUser(userId);
		return operationStatus;
	}

	@ApiImplicitParams({@ApiImplicitParam(name="Authorization", value="${userController.authorizationHeader.description}", paramType="header")})
	@PutMapping(path = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserRest updateUser(@PathVariable("userId") String userId, @RequestBody(required = true) User user) {
		User updatedUser = userService.updateUser(userId, user);

		UserRest returnValue = ObjectMapperUtils.map(updatedUser, UserRest.class);
		return returnValue;
	}
	
	@ApiImplicitParams({@ApiImplicitParam(name="Authorization", value="${userController.authorizationHeader.description}", paramType="header")})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserRest> getAllUsers() {
		List<UserRest> userRests = new ArrayList<>();

		List<User> users = userService.getAllUsers();

		userRests = ObjectMapperUtils.mapAll(users, UserRest.class);
		return userRests;
	}

	@ApiImplicitParams({@ApiImplicitParam(name="Authorization", value="${userController.authorizationHeader.description}", paramType="header")})
	@GetMapping(path="/getUserByEmailAndFirstName", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserRest getUserByEmailAndFirstName(@RequestParam("email") String email,
			@RequestParam("firstName") String firstName) {
		UserRest userRest = new UserRest();

		User user = userService.getUserByEmailAndFirstName(email, firstName);

		userRest = ObjectMapperUtils.map(user, UserRest.class);

		return userRest;

	}

	@ApiImplicitParams({@ApiImplicitParam(name="Authorization", value="${userController.authorizationHeader.description}", paramType="header")})
	@GetMapping(path="/getUserByEmail", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public UserRest findUserByEmail(@RequestParam String email) {
		UserRest userRest = new UserRest();

		User user = userService.findUserByEmail(email);

		userRest = ObjectMapperUtils.map(user, UserRest.class);

		return userRest;
	}
}
