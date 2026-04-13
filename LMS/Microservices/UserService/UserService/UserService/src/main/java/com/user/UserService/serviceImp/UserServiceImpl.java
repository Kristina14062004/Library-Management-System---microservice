package com.user.UserService.serviceImp;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.UserService.dto.Login;
import com.user.UserService.dto.Signup;
import com.user.UserService.entity.User;
import com.user.UserService.repo.UserRepo;
import com.user.UserService.service.UserService;

@Service 
public class UserServiceImpl implements UserService{

	private UserRepo repo;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public UserServiceImpl( UserRepo repo) {
		this.repo = repo;
	}
	
	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		user.setMembershipStatus("ACTIVE");
		user.setTotalBooksCheckout(0);
		return repo.save(user);
	}

	@Override
	public User getUserById(Integer id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
		
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public User updateUser(Integer id, User updated) {
		// TODO Auto-generated method stub
		User existing = getUserById(id);
		existing.setName(updated.getName());
		existing.setEmail(updated.getEmail());
		existing.setRole(updated.getRole());
		
		return repo.save(existing);
	}

	@Override
	public void deleteUser(Integer id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public boolean validateUser(Integer id) {
		// TODO Auto-generated method stub
		return repo.existsById(id);
	}
	@Override
	public User signup(Signup req) {
		// TODO Auto-generated method stub
		if(repo.findByEmail(req.getEmail()).isPresent()) {
			throw new RuntimeException("Email already registered");
		}
		
//		creating a new user
		User user = new User();
		user.setName(req.getName());
		user.setEmail(req.getEmail());
		user.setPassword(passwordEncoder.encode(req.getPassword()));
		user.setRole("Member");
		return repo.save(user);
	}
	@Override
	public String login(Login req) {
		// TODO Auto-generated method stub
		User user = repo.findByEmail(req.getEmail()).orElseThrow(()-> new RuntimeException("Invalid email or password"));
		
		if(!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid email or password");
		}
		return "Login Successfull";
	}

}
