package com.eb.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.eb.dao.AdminPanel;
import com.eb.database.Database;

public class AdminLogin {
	Scanner scanner = new Scanner(System.in);
	Connection conn = Database.createConnection();
	AdminPanel adminPanel = new AdminPanel();
	
	public void adminLogin() throws SQLException {
		System.out.println("Enter username:");
		String username = scanner.next();
		
		System.out.println("Enter password:");
		String password = scanner.next();
		
		// Prepare SQL statement to check if the user is an admin
		String query = "SELECT isAdmin FROM users WHERE username = ? AND password = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, username);
		ps.setString(2, password);
		
		ResultSet rs = ps.executeQuery();
		
		// Check if the result set has any row
		if(rs.next()) {
			boolean isAdmin = rs.getBoolean("isAdmin");
			
			if(isAdmin) {
				System.out.println("Welcome to Admin Panel");
				
				// Add your admin panel logic here
				adminPanelMenu();
			}
			else {
				System.out.println("You do not have permission to access the admin panel.");
			}
		}
		else {
			System.out.println("Invalid username or password");
		}
	}
	
	public void adminPanelMenu() {
		
		int choice = 0;
		
		do {
			System.out.println("Admin Panel");
			System.out.println("1. Display all books");
			System.out.println("2. Add book to store");
			System.out.println("3. Delete book from store");
			System.out.println("4. Update book data");
			System.out.println("5. Back to main menu");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();
			
			switch(choice) {
			case 1:
				// Display all books
				adminPanel.displayAllBooks();
				break;
			case 2:
				// Add book to store
				break;
			case 3:
				// Delete book from store
				break;
			case 4:
				// Update book data
				break;
			case 5:
				return;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}while (choice != 5);
		
		scanner.close();
	}
}
