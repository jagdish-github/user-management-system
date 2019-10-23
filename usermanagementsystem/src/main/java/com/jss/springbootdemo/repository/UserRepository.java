package com.jss.springbootdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jss.springbootdemo.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUserId(String userId);
	
	@Query(value="SELECT u FROM User u", nativeQuery=false)
	List<User> findAllUsers();
	
	@Query(value="SELECT u FROM User u WHERE u.email = ?1 and u.firstName = ?2", nativeQuery=false)
	User getUserByEmailAndFirstName(String email, String firstName);
	
	@Query(value="SELECT * FROM users u WHERE u.email = ?1", nativeQuery=true)
	User findUserByEmail(String email);
}
