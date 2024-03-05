package com.eb.pojo;

public class User {
	private int id;
	private String username;
	private String password;
	private boolean isAdmin;
	
	// Getters and Setters
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}
	
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	// Default Constructor
	public User() {
		super();
	}

	// Parameterised Constructor
	public User(String username, String password, boolean isAdmin) {
		super();
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
	}

	// toString()
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", isAdmin=" + isAdmin + "]";
	}
}