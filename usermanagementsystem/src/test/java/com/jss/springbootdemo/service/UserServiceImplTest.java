package com.jss.springbootdemo.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.jss.springbootdemo.entity.User;
import com.jss.springbootdemo.repository.UserRepository;

class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	UserRepository userRepository;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	final void testGetUserByUserId() {
		User user = new User();

		user.setFirstName("Jagdish");
		user.setLastName("Patil");
		user.setEmail("jp@gmail.com");

		when(userRepository.findByUserId(anyString())).thenReturn(user);

		User returnedUser = userService.getUserByUserId("jhgjh7868ibmngg576");

		assertNotNull(returnedUser);
		assertEquals("Jagdish", returnedUser.getFirstName());
	}

	@Test
	final void testGetUserByUserId_UsernameNotFoundException() {
		when(userRepository.findByUserId(anyString())).thenReturn(null);
		
		assertThrows(UsernameNotFoundException.class,() -> userService.getUserByUserId("jbjkbjk8767868"));
	}
}




















