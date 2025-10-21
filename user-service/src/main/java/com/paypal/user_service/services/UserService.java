package com.paypal.user_service.services;

import java.util.List;
import java.util.Optional;

import com.paypal.user_service.entities.UserEntity;

public interface UserService {
	UserEntity createUser(UserEntity userEntity);
	Optional<UserEntity> getUserById(Long Id);
	List<UserEntity> getAllUser();
}
