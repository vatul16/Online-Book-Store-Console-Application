package com.eb.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.eb.dao.AdminDao;
import com.eb.database.Database;
import com.eb.pojo.Book;

public class AdminLogin {
	Scanner scanner = new Scanner(System.in);
	Connection conn = Database.createConnection();
	AdminDao adminPanel = new AdminDao();
	
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
			System.out.println("---------- ADMIN PANEL ----------");
			System.out.println("Menu");
			System.out.println("1. Display all books");
			System.out.println("2. Add book to store");
			System.out.println("3. Delete book from store");
			System.out.println("4. Update book data");
			System.out.println("5. Logout");
			
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();
			
			switch(choice) {
			case 1:
				// Display all books
				adminPanel.displayAllBooks();
				break;
			case 2:
				// Add book to store
				System.out.println("-------------------- ADD BOOK TO STORE --------------------");
				System.out.println("Enter the following details to add book to store...");

				System.out.print("Title : ");
				String title = scanner.next();

				System.out.print("\nAuthor : ");
				String author = scanner.next();

				System.out.print("\nPrice : ");
				double price = scanner.nextDouble();

				System.out.print("\nQuanity : ");
				int quantity = scanner.nextInt();
				
				Book book = new Book(title, author, price, quantity);
				adminPanel.addBookToStore(book);
				
				System.out.println("BOOK INSERTED SUCCESSFULLY");

				break;
			case 3:
				// Delete book from store
				System.out.println("-------------------- DELETE BOOK FROM STORE --------------------");

				System.out.print("Enter ID of the book that need to be deleted: ");
				int bookId = scanner.nextInt();
				
				adminPanel.deleteBookFromStore(bookId);
				
				System.out.println("BOOK DELETED SUCCESSFULLY");
				
				break;
			case 4:
				// Update book data
				System.out.println("-------------------- UPDATE BOOK DATA --------------------");

				System.out.println("Press 1 to Update Book Title");
				System.out.println("Press 2 to Update Book Author");
				System.out.println("Press 3 to Update Book Price");
				System.out.println("Press 4 to Update Quantity");
				System.out.println("Press 5 to go back");

				System.out.print("Enter choice: ");
				int updateChoice = scanner.nextInt();
				
				if(updateChoice == 1) {
					System.out.print("Enter the Book ID whose title is to be updated: ");
					int bookIDT = scanner.nextInt();
					
					System.out.print("Enter the Updated Book Title: ");
					String bookTitle = scanner.next();
					
					Book bookUT = new Book();
					bookUT.setTitle(bookTitle);

					adminPanel.updateBookTitle(updateChoice, bookTitle, bookIDT, bookUT);
					
					System.out.println("BOOK TITLE UPDATED SUCCESSFULLY!!!");
				}
				else if(updateChoice == 2) {
					System.out.print("Enter the Book ID whose author is to be updated: ");
					int bookIDA = scanner.nextInt();
					
					System.out.print("Enter the Updated Author Name: ");
					String bookAuthor = scanner.next();
					
					Book bookUA = new Book();
					bookUA.setAuthor(bookAuthor);

					adminPanel.updateBookAuthor(updateChoice, bookAuthor, bookIDA, bookUA);
					
					System.out.println("AUTHOR NAME UPDATED SUCCESSFULLY!!!");
				}
				else if(updateChoice == 3) {
					System.out.print("Enter the Book ID whose price is to be updated: ");
					int bookIDP = scanner.nextInt();
					
					System.out.print("Enter the Updated Price: ");
					int bookPrice = scanner.nextInt();
					
					Book bookUP = new Book();
					bookUP.setPrice(bookPrice);

					adminPanel.updateBookPrice(updateChoice, bookPrice, bookIDP, bookUP);
					
					System.out.println("BOOK PRICE UPDATED SUCCESSFULLY!!!");
				}
				else if(updateChoice == 4) {
					System.out.print("Enter the Book ID whose quantity is to be updated: ");
					int bookIDQ = scanner.nextInt();
					
					System.out.print("Enter the Updated Quantity: ");
					int bookQuantity = scanner.nextInt();
					
					Book bookUQ = new Book();
					bookUQ.setQuantity(bookQuantity);

					adminPanel.updateBookQuantity(updateChoice, bookQuantity, bookIDQ, bookUQ);
					
					System.out.println("QUANTITY UPDATED SUCCESSFULLY!!!");
				}
				else {
					System.out.println("WRONG CHOICE (ENTER BETWEEN 1 TO 4.)");
				}
				break;
			case 5:
				return;
			default:
				System.out.println("INVALID CHOICE. PLEASE TRY AGAIN...");
			}
		}while (choice != 5);
		
		scanner.close();
	}
}
