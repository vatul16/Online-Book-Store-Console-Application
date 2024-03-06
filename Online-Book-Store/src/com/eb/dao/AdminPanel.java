package com.eb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.eb.database.Database;

public class AdminPanel {
	private Connection conn;
	Scanner scanner = new Scanner(System.in);

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

	public void addBookToStore() {
	}

	public void deleteBookFromStore() {
	}

	public void updateBookData() {
	}
}