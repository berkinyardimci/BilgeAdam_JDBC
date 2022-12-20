package com.employeeApp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	
	public static Connection connect() {
		
		final String url = "jdbc:postgresql://localhost:5432/employeeDB";
		final String username = "postgres";
		final String password = "12345";
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(url,username,password);
			System.out.println("Connection Success");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
