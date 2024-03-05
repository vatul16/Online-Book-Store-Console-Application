package com.eb.main;

import java.util.Scanner;

import com.eb.dao.AdminPanel;
import com.eb.dao.UserPanel;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		AdminPanel adminPanel = new AdminPanel();
		UserPanel userPanel = new UserPanel();
		
		int choice = 0;
		do {
			System.out.println("Welcome to the Online Book Store");
			System.out.println("1. Admin Panel");
			System.out.println("2. User Panel");
			System.out.println("3. New User");
			System.out.println("4. Exit");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();
			
			switch (choice) {
			case 1:
				adminPanelMenu(adminPanel);
				break;
			case 2:
				userPanelMenu(userPanel);
				break;
			case 3:
				// New User registration logic
				break;
			case 4:
				System.out.println("Thank you for visiting. Exiting...");
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}while (choice != 4);
		
		scanner.close();
	}

	private static void userPanelMenu(UserPanel userPanel) {
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
				break;
			case 2:
				// Search for a book
				break;
			case 3:
				// Add book to cart
				break;
			case 4:
				// View cart
				break;
			case 5:
				// Checkout
				break;
			case 6:
				return;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}while (choice != 5);
		
		scanner.close();
	}

	private static void adminPanelMenu(AdminPanel adminPanel) {
		Scanner scanner = new Scanner(System.in);
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