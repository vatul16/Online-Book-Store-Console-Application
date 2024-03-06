package com.eb.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.eb.database.Database;

public class NewUser {
	Scanner scanner = new Scanner(System.in);
	Connection conn = Database.createConnection();
	
	public void newUser() throws SQLException {
		System.out.println("Enter username (Don't use any space in your username): ");
		String newUsername = scanner.next();
		
		System.out.println("Enter password: ");
		String newPassword = scanner.next();
		
		int isAdmin = 0;
		
		// Prepare SQL statement to insert a new user into the database
		String query = "INSERT INTO users (username, password, isAdmin) VALUES(?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, newUsername);
		ps.setString(2, newPassword);
		ps.setInt(3, isAdmin);
		
		int rowsAffected = ps.executeUpdate();
		
		if(rowsAffected > 0) {
			System.out.println("New user created successfully.");
		}
		else {
			System.out.println("Failed to create new user.");
		}
		
		ps.close();
	}
}
