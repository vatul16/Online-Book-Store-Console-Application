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
	
	public void adminPanelMenu() throws SQLException {
		
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
				System.out.println("Enter Book Details \n");
				System.out.print("Title : ");
				String title = scanner.next();
				System.out.println();
				System.out.print("Author : ");
				String author = scanner.next();
				System.out.println();
				System.out.print("Price : ");
				double price = scanner.nextDouble();
				System.out.println();
				adminPanel.addBookToStore(title, author, price);
				
				break;
			case 3:
				// Delete book from store
				System.out.print("Enter Id of the book that needs to be deleted : ");
				int bookId = scanner.nextInt();
				adminPanel.deleteBookFromStore(bookId);
				break;
			case 4:
				// Update book data
				
				System.out.println("Press 1 to Update Title");
				System.out.println("Press 2 to Update Author");
				System.out.println("Press 3 to Update Price");
				System.out.println("Enter choice");
				int updateOption = scanner.nextInt();
				System.out.println("Enter the book id which you want to update");
				int bookId1 = scanner.nextInt();
//				if(updateOption==4) {
//					System.out.print("Title : ");
//					String newTitle = scanner.next();
//					System.out.println();
//					System.out.print("Author : ");
//					String newAuthor = scanner.next();
//					System.out.println();
//					System.out.print("Price : ");
//					double newPrice = scanner.nextDouble();
//					adminPanel.updateAllBookData(bookId1,newTitle,newAuthor,newPrice);
//				}
				if(updateOption==1 || updateOption==2 || updateOption==3 ) {
					System.out.print("Enter the value to be updated");
					String updatedValue = scanner.next();
					adminPanel.updateBookData( bookId1, updatedValue,updateOption);
				}
				else {
					System.out.println("Invalid input");
				}
				
				break;
			case 5:
				Main m = new Main();
				m.menu();
				return;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}while (choice != 5);
		
		scanner.close();
	}
}
