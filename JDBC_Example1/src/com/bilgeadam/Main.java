package com.bilgeadam;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {

		// database oluşturalım school,
		// student tablosu oluşturalım , name surname ve city
		// mainde oluşturdugumuz databse bağlanalım.

		// maven , gradle avatnajları dezavantajları,

		// execute methodu yazalım parametre olarak Connection nesnesi birde sql sorgusu
		// String şeklinde
		// içine geçtiğimiz sql sorgusunu çalıştırsın

		// Student classı oluşturalım dataBase uygun şekilde
		// create methodu yazalım, Student nesnesi ile
		// connection nesnesi alsın parametre olarak.

		// update methodu yazalım , connection, student , id
		
		//dışardan  girdiğimiz sehir isminden kaç tane verimiz oldugunu bulalım parametre olarak connection, sehirIsmin
		
		

		String url = "jdbc:postgresql://localhost:5432/school2";
		String username = "postgres";
		String password = "12345";

		String sql = " insert into student(id,name,age,city) values('M',35,'Ankara') ";

		Connection connect = null;

		try {
			Class.forName("org.postgresql.Driver");
			connect = DriverManager.getConnection(url, username, password);

			// execute(connect,sql);
			// Student student = new Student("B", 15,"İzmir");
			// create(connect,student);

			//Student student1 = new Student("Z", 200, "Bursaazzzzzzzzz");
			//update(connect, student1, 20);
			
			sameCitycount(connect,"ankara");

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		} finally {
			try {
				connect.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		System.out.println("Opened database successfully");
	}

	private static void update(Connection connection, Student student, int id) {
		String sql = " update student set name=?, age=?,city=? where id = ? ";
		String sql2 = " update student set name=?, age=?,city=? where id = " + id;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, student.getName());
			preparedStatement.setInt(2, student.getAge());
			preparedStatement.setString(3, student.getCty());
			preparedStatement.setInt(4, id);
			int rowEffected = preparedStatement.executeUpdate();
			
			if(rowEffected>0) {
				System.out.println("Başarılı güncelleme ");
			}else {
				System.out.println("Güncelleme Başarısız idyi kontrol edin");
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void execute(Connection connection, String sqlSorgu) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlSorgu);
			preparedStatement.executeUpdate();
			System.out.println("Ekeleme başarılı");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void create(Connection connection, Student student) {
		String sql = " insert into student(name,age,city) values(?,?,?)";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, student.getName());
			preparedStatement.setInt(2, student.getAge());
			preparedStatement.setString(3, student.getCty());
			preparedStatement.executeUpdate();
			System.out.println(student.getName() + " Veri tabanına Eklendi");
			preparedStatement.close();

			// 15:00

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	public static void sameCitycount(Connection connection, String city) {
		String sql = " select count(*) from student where lower(city) = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, city.toLowerCase());
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			System.out.println(city + " Counted " + resultSet.getInt("count"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//16:00
}
