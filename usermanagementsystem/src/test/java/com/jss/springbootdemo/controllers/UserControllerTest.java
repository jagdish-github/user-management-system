package com.jss.springbootdemo.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.jss.springbootdemo.entity.User;
import com.jss.springbootdemo.entity.UserRest;
import com.jss.springbootdemo.service.UserServiceImpl;

class UserControllerTest {
	
	@InjectMocks
	UserController userController;
	
	@Mock
	UserServiceImpl userService;
	
	User user;
	
	final String USER_ID = "ghfyyjyjt667876";

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		user = new User();
		user.setFirstName("Rajvardhan");
		user.setLastName("Patil");
		user.setEmail("raj@gmail.com");
		user.setPassword("hjhiu687gg78");		
		user.setUserId(USER_ID);
	}

	@Test
	final void testGetUserByUserId() {
		
		when(userService.getUserByUserId(anyString())).thenReturn(user);
		
		UserRest userRest = userController.getUserByUserId(USER_ID);
		
		assertNotNull(userRest);
		assertEquals(user.getFirstName(), userRest.getFirstName());
		assertEquals(user.getLastName(), userRest.getLastName());
		assertEquals(user.getUserId().length(), userRest.getUserId().length());
	}

}
