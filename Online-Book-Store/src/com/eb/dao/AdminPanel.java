package com.eb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.eb.database.Database;

public class AdminPanel {
	private Connection conn;
	Scanner scanner = new Scanner(System.in);

//	public AdminPanel() {
//		this.conn = Database.createConnection();
//	}

	public void displayAllBooks() {
		try {
			this.conn = Database.createConnection();
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

	public void addBookToStore(String title,String author,double price) {
		try {
			this.conn = Database.createConnection();
			String query = "INSERT INTO books(title,author,price) VALUES(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, title);
			ps.setString(2, author);
			ps.setDouble(3, price);
			int res = ps.executeUpdate();
			if(res>0) {
				System.out.println("Data inserted successfully");
			}
			else {
				System.out.println("Data not inserted");
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
	}

	public void deleteBookFromStore(int bookId) throws SQLException {
		try {
			this.conn = Database.createConnection();
			String query = "DELETE FROM books WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, bookId);
			int res = ps.executeUpdate();
			if(res>0) {
				System.out.println("Data deleted successfully");
			}
			else {
				System.out.println("Data not deleted");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public void updateBookData(int bookId1, String updatedValue, int updateOption) {
		
		try{
			this.conn = Database.createConnection();
			switch(updateOption) {
			case 1:
				String query = "UPDATE books SET title = ? where id=?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, updatedValue);
				ps.setInt(2, bookId1);
				int res = ps.executeUpdate();
				if(res>0) {
					System.out.println("Data updated successfully");
				}
				else {
					System.out.println("Data not updated");
				}
				break;
			case 2:
				String query2 = "UPDATE books SET author = ? where id=?";
				PreparedStatement ps2 = conn.prepareStatement(query2);
				ps2.setString(1, updatedValue);
				ps2.setInt(2, bookId1);
				int res2 = ps2.executeUpdate();
				if(res2>0) {
					System.out.println("Data updated successfully");
				}
				else {
					System.out.println("Data not updated");
				}
				break;
			case 3:
				String query3 = "UPDATE books SET price = ? where id=?";
				PreparedStatement ps3 = conn.prepareStatement(query3);
				double price = Double.parseDouble(updatedValue);
				ps3.setDouble(1, price);
				ps3.setInt(2, bookId1);
				int res3 = ps3.executeUpdate();
				if(res3>0) {
					System.out.println("Data updated successfully");
				}
				else {
					System.out.println("Data not updated");
				}
				break;
				
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
//	public void updateAllBookData(int bookId,String title,String author,double price) throws SQLException {
//		try {
//			this.conn = Database.createConnection();
//			String query = "UPDATE books SET title = ? , author=?,price=? where book_id=?";
//			PreparedStatement ps = conn.prepareStatement(query);
//			ps.setString(1, title);
//			ps.setString(2, author);
//			ps.setDouble(3, price);
//			ps.setInt(4, bookId);
//			int res = ps.executeUpdate();
//			if(res>0) {
//				System.out.println("Data updated successfully");
//			}
//			else {
//				System.out.println("Data not updated");
//			}
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//			
//		}
//		
//		
//		
//	}
}