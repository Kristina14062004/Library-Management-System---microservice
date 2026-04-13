package com.user.UserService.controller;

import java.util.List; 

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.UserService.dto.Login;
import com.user.UserService.dto.Signup;
import com.user.UserService.entity.User;

import com.user.UserService.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	private UserService userservice;
	
	public UserController( UserService userservice) {
		this.userservice = userservice;
	}
	
	@PostMapping("/addUser")
	public User create(@RequestBody User user) {
		return userservice.createUser(user);
	}
	
	@GetMapping("/{id}")
	public User getById(@PathVariable Integer id) {
		return userservice.getUserById(id);
	}
	
	@GetMapping("/getAllUser")
	public List<User> getAll(){
		return userservice.getAllUsers();
	}
	
	@PutMapping("update/{id}")
	public User update (@PathVariable Integer id, @RequestBody User updated) {
		return userservice.updateUser(id, updated);
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		userservice.deleteUser(id);
		return "User deleted successfully";
	}
	
	@GetMapping("/validate/{id}")  
	public boolean validate(@PathVariable Integer id) {  
		return userservice.validateUser(id);   
	}
	
	@PostMapping("/signup")
//	ResponseEntity is used for better error handling , http status 
	public ResponseEntity<User> signup(@RequestBody Signup req){
		return ResponseEntity.ok(userservice.signup(req));
	}
		
	@PostMapping("/login")
	public ResponseEntity<String> login (@RequestBody Login req){
		return ResponseEntity.ok(userservice.login(req));
	}
}
