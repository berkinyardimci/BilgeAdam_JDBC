package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public static Connection connect() {
		 final String user = "postgres";
		 final String password = "12345";
		 final String url = "jdbc:postgresql://localhost:5432/swing";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to PostgreSQL server successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
