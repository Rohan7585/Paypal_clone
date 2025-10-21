package com.paypal.user_service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.paypal.user_service.entities.UserEntity;
import com.paypal.user_service.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserEntity createUser(UserEntity userEntity) {
		
		return userRepository.save(userEntity);
	}

	@Override
	public Optional<UserEntity> getUserById(Long Id) {
		
		return userRepository.findById(Id);
	}

	@Override
	public List<UserEntity> getAllUser() {
		
		return userRepository.findAll();
	}

}
