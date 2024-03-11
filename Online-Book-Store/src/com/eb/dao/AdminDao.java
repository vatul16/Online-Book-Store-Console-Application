package com.eb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.eb.database.Database;
import com.eb.pojo.Book;

public class AdminDao {
	private Connection conn;
	Scanner scanner = new Scanner(System.in);
	
	public AdminDao() {
		this.conn = Database.createConnection();
	}

	public void displayAllBooks() {
		try {
			System.out.println("-------------------- AVAILABLE BOOKS --------------------");

			String query = "SELECT * FROM books;";
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

	public boolean addBookToStore(Book book) {
		try {
			boolean f = false;
			
			String query = "INSERT INTO books(title,author,price, quantity) VALUES(?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setDouble(3, book.getPrice());
			ps.setInt(4, book.getQuantity());

			ps.executeUpdate();
			
			f = true;

		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;
	}

	public boolean deleteBookFromStore(int bookId) throws SQLException {
		try {
			boolean f = false;

			String query = "DELETE FROM books WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, bookId);

			int res = ps.executeUpdate();

			f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	public boolean updateBookTitle(int updateChoice, String titleToUpdate, int bookId, Book book) {
		boolean f = false;
		try {
			// Update title
			String updateTitleQuery = "UPDATE books SET title = ? where id=?";
			PreparedStatement ps = conn.prepareStatement(updateTitleQuery);
			ps.setString(1, titleToUpdate);
			ps.setInt(2, bookId);
			ps.executeUpdate();
			
			f = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateBookAuthor(int updateChoice, String authorToUpdate, int bookId, Book book) {
		boolean f = false;
		try {
			// Update author
			String updateAuthorQuery = "UPDATE books SET author = ? where id=?";
			PreparedStatement ps1 = conn.prepareStatement(updateAuthorQuery);
			ps1.setString(1, authorToUpdate);
			ps1.setInt(2, bookId);
			ps1.executeUpdate();
			
			f = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateBookPrice(int updateChoice, int priceToUpdate, int bookId, Book book) {
		boolean f = false;
		try {
			// Update price
			String updatePriceQuery = "UPDATE books SET price = ? where id=?";
			PreparedStatement ps = conn.prepareStatement(updatePriceQuery);
			ps.setInt(1, priceToUpdate);
			ps.setInt(2, bookId);

			ps.executeUpdate();
			
			f = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateBookQuantity(int updateChoice, int quantityToUpdate, int bookId, Book book) {
		boolean f = false;
		try {
			// Update price
			String updatePriceQuery = "UPDATE books SET quantity = ? where id=?";
			PreparedStatement ps = conn.prepareStatement(updatePriceQuery);
			ps.setInt(1, quantityToUpdate);
			ps.setInt(2, bookId);

			ps.executeUpdate();
			
			f = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}