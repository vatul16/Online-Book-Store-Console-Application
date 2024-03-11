package com.eb.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.eb.dao.UserDao;
import com.eb.database.Database;
import com.eb.pojo.Book;

public class UserLogin {
	Scanner scanner = new Scanner(System.in);
	Connection conn = Database.createConnection();
	UserDao userPanel = new UserDao();
	
	public void userLogin() throws SQLException {
		System.out.println("Enter username:");
		String username = scanner.next();
		
		System.out.println("Enter password:");
		String password = scanner.next();
		
		// Prepare SQL statement to check if the user is not an admin
		String query = "SELECT id, username, password, isAdmin FROM users WHERE username = ? AND password = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, username);
		ps.setString(2, password);
		
		ResultSet rs = ps.executeQuery();
		
		// Check if the result set has any row
		if(rs.next()) {
			int userId = rs.getInt("id");
			String userName = rs.getString("username");
			boolean isAdmin = rs.getBoolean("isAdmin");
			
			if(!isAdmin) {
				System.out.println("Welcome to User Panel");
				System.out.println("ID: " + userId + "\t" + "Username: " + userName);
				
				// Add your user panel logic here
				userPanelMenu(userId, userName);
			}
			else {
				System.out.println("You are trying to login with Admin Credentials");
			}
		}
		else {
			System.out.println("Invalid username or password");
		}
	}
	
	
	public void userPanelMenu(int currentUserId, String currentUsername) throws SQLException{
		Scanner scanner = new Scanner(System.in);
		int choice = 0;
		
		do {
			System.out.println("User Panel");
			System.out.println("1. Display all books");
			System.out.println("2. Search for a book");
			System.out.println("3. Add book to cart");
			System.out.println("4. View cart");
			System.out.println("5. Checkout");
			System.out.println("6. Log out");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();
			
			switch(choice) {
			case 1:
				// Display all books
				userPanel.displayAllBooks();
				break;
			case 2:
				// Search for a book
				System.out.println("-------------------- SEARCH FOR A BOOK IN STORE --------------------");

				System.out.println("Search Menu");
				System.out.println("1. Search by Book Title");
				System.out.println("2. Search by Author Name");
				System.out.println("3. Back");

				System.out.print("Enter choice: ");
				int searchChoice = scanner.nextInt();
				
				if(searchChoice == 1) {
					System.out.print("Enter the Book Name to be searched: ");
					String bookNameSearch = scanner.next();

					userPanel.searchForBook(searchChoice, bookNameSearch);
				}
				else if(searchChoice == 2) {
					System.out.print("Enter the Author Name to be searched: ");
					String bookAuthorSearch = scanner.next();

					userPanel.searchForBook(searchChoice, bookAuthorSearch);
				}
				break;
			case 3:
				// Add book to cart
				System.out.println("-------------------- ADD BOOK TO CART --------------------");

				System.out.print("Enter Book ID you want to add to the cart: ");
				int bookId = scanner.nextInt();

				System.out.print("Enter the quantity : ");
				int quantity = scanner.nextInt();
				
				userPanel.addBookToCart(currentUserId, bookId, quantity);
				
				System.out.println("BOOK ADDED TO CART SUCCESSFULLY!!!");
				
				break;
			case 4:
				// View cart
				userPanel.viewCart(currentUserId);
				break;
			case 5:
				// Checkout
				userPanel.checkout(currentUserId);
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
