package com.eb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.eb.database.Database;
import com.eb.main.Main;

public class UserDao {
	private Connection conn;

	public UserDao() {
		this.conn = Database.createConnection();
	}

	public void displayAllBooks() {
		try {
			String query = "SELECT * FROM books";
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			System.out.println("Books available:");
			while (rs.next()) {
				System.out.println(rs.getInt("id") + ", " + rs.getString("title") + ", " + rs.getString("author") + ", "
						+ rs.getDouble("price"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchForBook(String title) {
		try {
			
			String query = "SELECT * FROM books WHERE title LIKE ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, "%" + title + "%");
			ResultSet rs = stmt.executeQuery();
			System.out.println("Search results:");
			while (rs.next()) {
				System.out.println(rs.getInt("id") + ", " + rs.getString("title") + ", " + rs.getString("author") + ", "
						+ rs.getDouble("price"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addBookToCart(int userId, int bookId, int quantity) {
		try {
			String query = "INSERT INTO cart (user_id, book_id, quantity) VALUES (?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, userId);
			stmt.setInt(2, bookId);
			stmt.setInt(3, quantity);
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Book added to cart successfully.");
			} else {
				System.out.println("Failed to add book to cart.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void viewCart(int userId) {
		try {
			String query = "SELECT b.title, b.author, b.price, c.quantity "
					+ "FROM cart c JOIN books b ON c.book_id = b.id WHERE c.user_id = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			System.out.println("Cart contents:");
			while (rs.next()) {
				System.out.println(rs.getString("title") + ", " + rs.getString("author") + ", " + rs.getDouble("price")
						+ " x " + rs.getInt("quantity"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkout() {
		try {
			// Implement checkout logic here
			System.out.println("You have been checked out");
			return;
//			System.out.println("Press 1 to go to Main Menu");
////			System.out.println("Press 2 to go to User Panel");
//			Scanner scanner = new Scanner(System.in);
//			int choice =scanner.nextInt();
//			if(choice==1) {
//				
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
