package com.eb.pojo;

public class Cart {
	private int id;
	private int userId;
	private int bookId;
	private int quantity;
	
	// Getters and Setters
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getBookId() {
		return bookId;
	}
	
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	// Default Constructor
	public Cart() {
		super();
	}
	
	// Parameterised Constructor
	public Cart(int userId, int bookId, int quantity) {
		super();
		this.userId = userId;
		this.bookId = bookId;
		this.quantity = quantity;
	}
	
	// toString()
	@Override
	public String toString() {
		return "Cart [id=" + id + ", userId=" + userId + ", bookId=" + bookId + ", quantity=" + quantity + "]";
	}
	
	
}