package com.santosh.projects.authorizationserver.model;

import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {

	private static final long serialVersionUID = 1L;
	private String userId;
	private String firstName;
	private String lastName;
	private String mobile;
	private String country;
	private String userType;
	
	public CustomUser(UserEntity user) {
		super(user.getEmailId(), user.getPassword(), user.getGrantedAuthorities());
		this.userId = user.getEmailId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.mobile = user.getMobile();
		this.country = user.getCountry();
		this.userType = user.getUserType();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
}
