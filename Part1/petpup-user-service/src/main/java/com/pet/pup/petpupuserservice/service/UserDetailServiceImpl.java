package com.pet.pup.petpupuserservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.pup.petpupuserservice.constants.ErrorCodes;
import com.pet.pup.petpupuserservice.domain.User;
import com.pet.pup.petpupuserservice.exception.ResourceNotFoundException;
import com.pet.pup.petpupuserservice.repository.UserDetailRepository;
import com.pet.pup.petpupuserservice.repository.domain.UserDetail;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	private UserDetailRepository userDetailRepository;
	
	@Autowired
	public UserDetailServiceImpl( UserDetailRepository userDetailRepository) {
		this.userDetailRepository = userDetailRepository;
	}

	@Override
	public void addUser(User user) {
		if(null != user ) {
			Optional<UserDetail> userDetOpt = userDetailRepository.findByUserName(user.getUserName());			
			if(!userDetOpt.isPresent()) {
				UserDetail userDetail = new UserDetail();
				userDetail.setFirstName(user.getFirstName());
				userDetail.setLastName(user.getLastName());
				userDetail.setUserName(user.getUserName());
				userDetailRepository.save(userDetail);
			} else {
				throw new ResourceNotFoundException(ErrorCodes.USER_ALREADY_EXIST.getErrorCode(),ErrorCodes.USER_ALREADY_EXIST.getErrorMessage());
			}
		}
	}

	@Override
	public void deleteUser(String userName) {
		if(null != userName) {
			userDetailRepository.deleteByUserName(userName);
		}
	}

	@Override
	public void updateUser(User user) {
		if(null != user ) {
			Optional<UserDetail> userDetOpt = userDetailRepository.findByUserName(user.getUserName());		
			if(userDetOpt.isPresent()) {
				UserDetail userDetail = userDetOpt.get();
				userDetail.setFirstName(user.getFirstName());
				userDetail.setLastName(user.getLastName());
				userDetail.setUserName(user.getUserName());
				userDetailRepository.save(userDetail);
			} else {
				throw new ResourceNotFoundException(ErrorCodes.USER_NOT_FOUND.getErrorCode(),ErrorCodes.USER_NOT_FOUND.getErrorMessage());
			}
		}
	}

	@Override
	public User getUser(String userName) {
		Optional<UserDetail> userDetOpt;
		User user = null; 
		
		userDetOpt = userDetailRepository.findByUserName(userName);
		if (userDetOpt.isPresent()) {
			user = new User( userDetOpt.get().getUserName(),
					userDetOpt.get().getFirstName(),
					userDetOpt.get().getLastName() );
		} else {
			throw new ResourceNotFoundException(ErrorCodes.USER_NOT_FOUND.getErrorCode(),ErrorCodes.USER_NOT_FOUND.getErrorMessage());
		}
		
		return user;
	}
	

}
