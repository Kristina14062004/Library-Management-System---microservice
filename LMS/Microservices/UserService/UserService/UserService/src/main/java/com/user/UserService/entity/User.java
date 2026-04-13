package com.user.UserService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "User")
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String name;
	
	@Column(unique = true)
	private String email;
	private String password;
	//admin or member 
	private String role; 
	
	//active or blocked
	private String membershipStatus; 
	
	//limit 5
	private int totalBooksCheckout;
	
	

	public User() {
		super();
	}

	public User(Integer id, String name, String email, String password, String role, String membershipStatus,
			int totalBooksCheckout) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.membershipStatus = membershipStatus;
		this.totalBooksCheckout = totalBooksCheckout;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getMembershipStatus() {
		return membershipStatus;
	}

	public void setMembershipStatus(String membershipStatus) {
		this.membershipStatus = membershipStatus;
	}

	public int getTotalBooksCheckout() {
		return totalBooksCheckout;
	}

	public void setTotalBooksCheckout(int totalBooksCheckout) {
		this.totalBooksCheckout = totalBooksCheckout;
	}
	
	
}
