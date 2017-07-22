package com.pet.pup.petpupuserservice.controller;


import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pet.pup.petpupuserservice.constants.ErrorCodes;
import com.pet.pup.petpupuserservice.domain.User;
import com.pet.pup.petpupuserservice.service.UserDetailsService;

@RestController
public class UserController {
	private UserDetailsService userDetailsService;
	
	@Autowired
	public UserController(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@RequestMapping(value="/v1/users/{userName}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUser(@PathVariable("userName") String userName) {
		
		if( null == userName || userName.isEmpty()) {
			throw new IllegalArgumentException(ErrorCodes.USER_NAME_REQUIRED.getErrorMessage());
		}

		return userDetailsService.getUser(userName);
	}
	
	
	@RequestMapping(value="/v1/users/{userName}" , method = RequestMethod.DELETE)
	public void removeUser(@PathVariable("userName") String userName) {
		
		if( null == userName || userName.isEmpty()) {
			throw new IllegalArgumentException(ErrorCodes.USER_NAME_REQUIRED.getErrorMessage());
		}

		userDetailsService.deleteUser(userName);
	}
	
	@RequestMapping(value="/v1/users" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateUser(@RequestBody User user) {
		
		if(null == user) {
			throw new IllegalArgumentException(ErrorCodes.USER_DETAILS_REQUIRED.getErrorMessage());
		}

		userDetailsService.updateUser(user);
	}
	
	@RequestMapping(value="/v1/users" , method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addUser(@RequestBody User user) {

		if(null == user) {
			throw new IllegalArgumentException(ErrorCodes.USER_DETAILS_REQUIRED.getErrorMessage());
		}
		
		userDetailsService.addUser(user);
	}
}
