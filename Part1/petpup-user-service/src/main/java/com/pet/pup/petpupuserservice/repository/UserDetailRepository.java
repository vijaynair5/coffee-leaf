package com.pet.pup.petpupuserservice.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.pet.pup.petpupuserservice.repository.domain.UserDetail;

public interface UserDetailRepository extends CrudRepository<UserDetail, Long>{
	
	public Optional<UserDetail> findByUserName(String userName);
	
	public void deleteByUserName(String userName);
	

}
