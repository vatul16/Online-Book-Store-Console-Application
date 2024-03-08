package com.eb.pojo;

public class Book {
	private int id;
	private String title;
	private String author;
	private double price;
	private int quantity;

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	// Default Constructor
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Parameterized Constructor
	public Book(String title, String author, double price, int quantity) {
		super();
		this.title = title;
		this.author = author;
		this.price = price;
		this.quantity = quantity;
	}

	// toString()
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", price=" + price + ", quantity="
				+ quantity + "]";
	}

}