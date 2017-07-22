package com.pet.pup.petpupuserservice.service;

import com.pet.pup.petpupuserservice.domain.User;

public interface UserDetailsService {
	void addUser(User user);
	void deleteUser(String userName);
	void updateUser(User user);
	User getUser(String userName);	
}
