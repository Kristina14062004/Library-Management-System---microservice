package com.user.UserService.service;

import java.util.List;

import com.user.UserService.dto.Login;
import com.user.UserService.dto.Signup;
import com.user.UserService.entity.User;

public interface UserService {
	public User createUser(User user);
	public User getUserById(Integer id);
	public List<User> getAllUsers();
	public User updateUser(Integer id, User updated);
	public void deleteUser(Integer id);
	public boolean validateUser(Integer id);
	
	
	public User signup(Signup request);
	public String login(Login request);
}
