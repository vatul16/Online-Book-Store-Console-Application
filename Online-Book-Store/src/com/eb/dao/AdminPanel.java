package com.eb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.eb.database.Database;

public class AdminPanel {
	private Connection conn;

	public AdminPanel() {
		this.conn = Database.createConnection();
	}

	public void displayAllBooks() {
		try {
			String query = "SELECT * FROM books;";
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			System.out.println("Books available:");
			while (rs.next()) {
				System.out.println(rs.getInt("id") + "\t" + rs.getString("title") + "\t" + rs.getString("author") + "\t"
						+ rs.getDouble("price"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addBookToStore(String title, String author, double price) {
		try {
			String query = "INSERT INTO books (title, author, price) VALUES (?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, title);
			stmt.setString(2, author);
			stmt.setDouble(3, price);
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Book added successfully.");
			} else {
				System.out.println("Failed to add book.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteBookFromStore(int bookId) {
		try {
			String query = "DELETE FROM books WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, bookId);
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Book deleted successfully.");
			} else {
				System.out.println("Failed to delete book.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateBookData(int bookId, String title, String author, double price) {
		try {
			String query = "UPDATE books SET title = ?, author = ?, price = ? WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, title);
			stmt.setString(2, author);
			stmt.setDouble(3, price);
			stmt.setInt(4, bookId);
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Book data updated successfully.");
			} else {
				System.out.println("Failed to update book data.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}