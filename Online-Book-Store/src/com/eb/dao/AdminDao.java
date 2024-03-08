package com.eb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.eb.database.Database;

public class AdminDao {
	private Connection conn;
	Scanner scanner = new Scanner(System.in);
	
	public AdminDao() {
		this.conn = Database.createConnection();
	}

	public void displayAllBooks() {
		try {
			System.out.println("-------------------- AVAILABLE BOOKS --------------------");

			String query = "SELECT * FROM books;";
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			
			System.out.println("Book ID\tBook Title\tAuthor Name\tPrice\tQuantity");
			while (rs.next()) {
				System.out.println(rs.getInt("id") + "\t" + rs.getString("title") + "\t" + rs.getString("author") + "\t"
						+ rs.getDouble("price") + "\t" + rs.getInt("quantity"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addBookToStore() {
		try {
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

			String query = "INSERT INTO books(title,author,price, quantity) VALUES(?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, title);
			ps.setString(2, author);
			ps.setDouble(3, price);
			ps.setInt(4, quantity);

			int res = ps.executeUpdate();
			if (res > 0) {
				System.out.println("Data inserted successfully");
			} else {
				System.out.println("Data not inserted");
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void deleteBookFromStore() throws SQLException {
		try {
			System.out.println("-------------------- DELETE BOOK FROM STORE --------------------");

			System.out.print("Enter ID of the book that need to be deleted: ");
			int bookId = scanner.nextInt();

			String query = "DELETE FROM books WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, bookId);

			int res = ps.executeUpdate();
			if (res > 0) {
				System.out.println("BOOK DELETION SUCCESSFUL!!!");
			} else {
				System.out.println("BOOK DELETEION UNSUCCESSFUL!!!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateBookData() {

		try {
			System.out.println("-------------------- UPDATE BOOK DATA --------------------");

			System.out.println("Press 1 to Update Book Title");
			System.out.println("Press 2 to Update Book Author");
			System.out.println("Press 3 to Update Book Price");
			System.out.println("Press 4 to Update Quantity");
			System.out.println("Press 5 to go back");

			System.out.print("Enter choice: ");
			int choice = scanner.nextInt();


			switch (choice) {
			case 1:
				System.out.print("Enter the Book ID whose title is to be updated: ");
				int bookIDT = scanner.nextInt();
				
				System.out.print("Enter the Updated Book Title: ");
				String bookTitle = scanner.next();

				String updateTitleQuery = "UPDATE books SET title = ? where id=?";

				PreparedStatement ps = conn.prepareStatement(updateTitleQuery);
				ps.setString(1, bookTitle);
				ps.setInt(2, bookIDT);

				int res = ps.executeUpdate();

				if (res > 0) {
					System.out.println("BOOK TITLE UPDATE SUCCESSFUL!!!");
				} else {
					System.out.println("BOOK TITLE UPDATE UNSUCCESSFUL!!!");
				}
				break;

			case 2:
				System.out.print("Enter the Book ID whose author is to be updated: ");
				int bookIDA = scanner.nextInt();
				
				System.out.print("Enter the Updated Book Author: ");
				String bookAuthor = scanner.next();

				String updateAuthorQuery = "UPDATE books SET author = ? where id=?";

				PreparedStatement ps1 = conn.prepareStatement(updateAuthorQuery);
				ps1.setString(1, bookAuthor);
				ps1.setInt(2, bookIDA);

				int res1 = ps1.executeUpdate();

				if (res1 > 0) {
					System.out.println("BOOK AUTHOR UPDATE SUCCESSFUL!!!");
				} else {
					System.out.println("BOOK AUTHOR UPDATE UNSUCCESSFUL!!!");
				}
				break;

			case 3:
				System.out.print("Enter the Book ID whose price is to be updated: ");
				int bookIDP = scanner.nextInt();
				
				System.out.print("Enter the Updated Book Price: ");
				double bookPrice = scanner.nextDouble();

				String updatePriceQuery = "UPDATE books SET price = ? where id=?";

				PreparedStatement ps2 = conn.prepareStatement(updatePriceQuery);
				ps2.setDouble(1, bookPrice);
				ps2.setInt(2, bookIDP);

				int res2 = ps2.executeUpdate();

				if (res2 > 0) {
					System.out.println("BOOK PRICE UPDATE SUCCESSFUL!!!");
				} else {
					System.out.println("BOOK PRICE UPDATE UNSUCCESSFUL!!!");
				}
				break;

			case 4:
				System.out.print("Enter the Book ID whose quantity is to be updated: ");
				int bookIDQ = scanner.nextInt();
				
				System.out.print("Enter the Updated Quantity: ");
				int bookQuantity = scanner.nextInt();

				String updateQuantityQuery = "UPDATE books SET quantity = ? where id = ?";

				PreparedStatement ps3 = conn.prepareStatement(updateQuantityQuery);
				ps3.setInt(1, bookQuantity);
				ps3.setInt(2, bookIDQ);

				int res3 = ps3.executeUpdate();

				if (res3 > 0) {
					System.out.println("QUANTITY UPDATE SUCCESSFUL!!!");
				} else {
					System.out.println("QUANTITY UPDATE UNSUCCESSFUL!!!");
				}
				break;
				
			case 5:
				return;

			default:
				System.out.println("INVALID CHOICE. PLEASE TRY AGAIN...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}