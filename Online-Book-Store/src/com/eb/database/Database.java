package com.eb.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	
	static final String DB_URL = "jdbc:mysql://localhost:3306/online_book_store";
	static final String USER = "root";
	static final String PASS = "root";
	
	static Connection conn;
	public static Connection createConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}