package com.eb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.eb.database.Database;
import com.eb.main.Main;
import com.eb.pojo.Book;

public class UserDao {
	private Connection conn;
	Scanner scanner = new Scanner(System.in);

	public UserDao() {
		this.conn = Database.createConnection();
	}

	public void displayAllBooks() {
		try {
			System.out.println("-------------------- AVAILABLE BOOKS --------------------");

			String query = "SELECT * FROM books";

			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();

			System.out.println("Book ID\tBook Title\tAuthor Name\tPrice\tQuantity");
			while (rs.next()) {
				System.out.println(rs.getInt("id") + "\t" + rs.getString("title") + "\t" + rs.getString("author") + "\t"
						+ rs.getDouble("price") + "\t" + rs.getInt("quantity"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchForBook(int searchChoice, String bookQuery) {
		try {
			switch (searchChoice) {
			case 1:
				String searchTitleQuery = "SELECT * FROM books WHERE title LIKE ?";
				PreparedStatement ps = conn.prepareStatement(searchTitleQuery);
				ps.setString(1, "%" + bookQuery + "%");

				ResultSet rs = ps.executeQuery();

				System.out.println("SEARCH RESULTS:");
				System.out.println("Book ID\tBook Title\tAuthor Name\tPrice\tQuantity");
				while (rs.next()) {
					System.out.println(rs.getInt("id") + "\t" + rs.getString("title") + "\t" + rs.getString("author")
							+ "\t" + rs.getDouble("price") + "\t" + rs.getInt("quantity"));
				}

				break;

			case 2:
				String searchAuthorQuery = "SELECT * FROM books WHERE author LIKE ?";
				PreparedStatement ps1 = conn.prepareStatement(searchAuthorQuery);
				ps1.setString(1, "%" + bookQuery + "%");

				ResultSet rs1 = ps1.executeQuery();

				System.out.println("SEARCH RESULTS:");
				System.out.println("Book ID\tBook Title\tAuthor Name\tPrice\tQuantity");
				while (rs1.next()) {
					System.out.println(rs1.getInt("id") + "\t" + rs1.getString("title") + "\t" + rs1.getString("author")
							+ "\t" + rs1.getDouble("price") + "\t" + rs1.getInt("quantity"));
				}

				break;

			case 3:
				return;

			default:
				System.out.println("INVALID CHOICE. PLEASE TRY AGAIN...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addBookToCart(int loggedInUserId, int bookId, int quantity) {
		try {
			boolean f = false;
			
			String query = "INSERT INTO cart (user_id, book_id, quantity) VALUES (?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, loggedInUserId);
			ps.setInt(2, bookId);
			ps.setInt(3, quantity);
			ps.executeUpdate();
			
			f = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void viewCart(int loggedInUserId) {
		try {
			System.out.println("-------------------- YOUR CART --------------------");

			String query = "SELECT b.title, b.author, b.price, c.quantity FROM cart c JOIN books b ON c.book_id = b.id WHERE c.user_id = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, loggedInUserId);

			ResultSet rs = stmt.executeQuery();

			System.out.println("YOUR CART ITEMS");
			System.out.println("Book Title\tAuthor Name\tPrice\tQuantity");
			while (rs.next()) {
				System.out.println(rs.getString("title") + "\t" + rs.getString("author") + "\t" + rs.getDouble("price")
						+ "\t" + rs.getInt("quantity"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkout(int loggedInUserId) {
		try {
			System.out.println("-------------------- CHECKOUT --------------------");
			System.out.println("YOUR BOOKS WILL BE DELIVERED TO YOU SOON");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
