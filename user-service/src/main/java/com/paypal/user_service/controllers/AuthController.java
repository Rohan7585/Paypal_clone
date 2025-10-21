package com.paypal.user_service.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.user_service.dtos.SigninRequest;
import com.paypal.user_service.entities.UserEntity;
import com.paypal.user_service.repositories.UserRepository;
import com.paypal.user_service.utilities.JWTUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final JWTUtil jwtUtil;
	
	public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTUtil jwtUtil) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody SigninRequest signinRequest){
		Optional<UserEntity> existingUser = userRepository.findByEmail(signinRequest.getEmail());
		if(existingUser.isPresent()) {
			return ResponseEntity.badRequest().body("User already exists");
		}
		
		UserEntity user = new UserEntity();
		user.setName(signinRequest.getName());
		user.setEmail(signinRequest.getEmail());
		user.setRole("ROLE_USER");
		user.setPassword(passwordEncoder.encode(signinRequest.getPassword()));
		userRepository.save(user);
		
		UserEntity savedUser = userRepository.save(user);
		
		return ResponseEntity.ok("User register successfully");
	}
}
