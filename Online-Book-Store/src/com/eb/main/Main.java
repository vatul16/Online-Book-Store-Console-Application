package com.eb.main;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	int  choice=0;
	public static void main(String[] args) throws SQLException {
		Scanner scanner = new Scanner(System.in);
		AdminLogin adminLogin = new AdminLogin();
		UserLogin userLogin = new UserLogin();
		NewUser newUser = new NewUser();
		
		int choice;
		
		do {
			System.out.println("---------- ONLINE BOOK STORE ----------");
			System.out.println("Menu");
			System.out.println("1. Admin Panel");
			System.out.println("2. User Panel");
			System.out.println("3. New User");
			System.out.println("4. Exit");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				adminLogin.adminLogin();
				break;
			case 2:
				userLogin.userLogin();
				break;
			case 3:
				newUser.newUser();
				break;
			case 4:
				System.out.println("THANK YOU FOR VISITING...");
				System.out.println("EXITTING...");
				break;
			default:
				System.out.println("INVALID CHOICE. PLEASE TRY AGAIN...");
			}
		} while (choice != 4);
		
		scanner.close();
		
	}
}