package com.paypal.user_service.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.user_service.entities.UserEntity;
import com.paypal.user_service.services.UserService;

@RestController
@RequestMapping("/api/users/")
public class UserController {

	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userEntity));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserEntity> getUserById(@PathVariable Long id){
		return userService.getUserById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<UserEntity>> getAllUser(){
		return ResponseEntity.ok(userService.getAllUser());
	}
}
