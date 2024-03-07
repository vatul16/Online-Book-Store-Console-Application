package com.eb.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.eb.dao.UserPanel;
import com.eb.database.Database;

public class UserLogin {
	Scanner scanner = new Scanner(System.in);
	Connection conn = Database.createConnection();
	UserPanel userPanel = new UserPanel();
	
	public void userLogin() throws SQLException {
		System.out.println("Enter username:");
		String username = scanner.next();
		
		System.out.println("Enter password:");
		String password = scanner.next();
		
		// Prepare SQL statement to check if the user is not an admin
		String query = "SELECT isAdmin FROM users WHERE username = ? AND password = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, username);
		ps.setString(2, password);
		
		ResultSet rs = ps.executeQuery();
		
		// Check if the result set has any row
		if(rs.next()) {
			boolean isAdmin = rs.getBoolean("isAdmin");
			
			if(!isAdmin) {
				System.out.println("Welcome to User Panel");
				
				// Add your user panel logic here
				userPanelMenu();
			}
			else {
				System.out.println("You are trying to login with Admin Credentials");
			}
		}
		else {
			System.out.println("Invalid username or password");
		}
	}
	
	public int getUserIdByUsernameAndPassword(String username, String password) {
        int userId = 0;
        try {
            String sql = "SELECT id FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("id");
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }
	
	public void userPanelMenu() throws SQLException{
		Scanner scanner = new Scanner(System.in);
		int choice = 0;
		
		do {
			System.out.println("User Panel");
			System.out.println("1. Display all books");
			System.out.println("2. Search for a book");
			System.out.println("3. Add book to cart");
			System.out.println("4. View cart");
			System.out.println("5. Checkout");
			System.out.println("6. Back to main menu");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();
			
			switch(choice) {
			case 1:
				// Display all books
				userPanel.displayAllBooks();
				break;
			case 2:
				// Search for a book
				System.out.println("Enter title to be searched : ");
				String search = scanner.next();
				System.out.print("\n");
				userPanel.searchForBook(search);
				break;
			case 3:
				// Add book to cart
				System.out.print("Enter User Id : ");
				int userId =scanner.nextInt();
				System.out.print("\n");
				System.out.print("Enter Book Id : ");
				int bookId= scanner.nextInt();
				System.out.print("\n");
				System.out.print("Enter the quantity : ");
				int quantity=scanner.nextInt();
				System.out.print("\n");
				userPanel.addBookToCart(userId, bookId, quantity);
				break;
			case 4:
				// View cart
				System.out.print("Enter User Id : ");
				int userID =scanner.nextInt();
				System.out.print("\n");
				userPanel.viewCart(userID);
				break;
			case 5:
				// Checkout
				userPanel.checkout();
				break;
			case 6:
				return;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}while (choice != 5);
		
		scanner.close();
	}
}
