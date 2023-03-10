package com.bilgeadam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {

	// actor sayısını bulcaz method bize int değer dönsün, try with resource

	// getActors diye bir method yazalım, bu methodun içinde farklı bir method daha
	// kullancaz
	// displayActors methodu olcak parametre olarak Result set alcak, id, f.name,
	// l.name göstercek şekilde
	// method olcak

	// insert actor methodu yazalım Actor nesnesi parametre olarak, ekledikten
	// sonrada bize eklediğimzi
	// actorun id sini dönsün, dönüş tipi int
	
	//insertActors methodu yazalım List<Acotr> parametre olarak
	//

	private final String url = "jdbc:postgresql://localhost:5432/dvdrental";
	private final String username = "postgres";
	private final String password = "12345";

	public Connection connect() {
		Connection connect = null;
		try {
			connect = DriverManager.getConnection(url, username, password);
			System.out.println("Başarılı şekilde Bağlantı gerçekleşti ");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return connect;
	}
	private void displayActors(ResultSet resultSet) {
		try {
			while (resultSet.next()) {
				System.out.println(
						resultSet.getString(1) + "\t\t" + resultSet.getString(2) + "\t\t" + resultSet.getString(3));
			}
		} catch (Exception e) {
		}
	}

	public void getActors() {
		String sql = "Select * from actor ";
		try (Connection connection = connect();
				PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
			ResultSet resultSet = preparedStatement.executeQuery();
			displayActors(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getActorCount() {
		String sql = "select count(*) from actor";

		int count = 0;

		try (Connection connection = connect();
				PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			count = resultSet.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int insertActor(Actor actor) {
		int id = 0;

		String sql = "insert into actor(first_name, last_name) values (?,?)";
		try (Connection connection = connect();
				PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
			preparedStatement.setString(1, actor.getFirstname());
			preparedStatement.setString(2, actor.getLastname());
			int affectedRow = preparedStatement.executeUpdate();
			if (affectedRow > 0) {
				try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
					if (resultSet.next()) {
						id = resultSet.getInt(1);
					}
				}
			}
			System.err.println(actor.getFirstname() + " Veri tabanına eklendi");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	//insertActors methodu yazalım List<Actor> parametre olarak
	//how to insert multiple object in jdbc
	
	public void insertActors(List<Actor> ActorList) {
		String sql = "insert into actor(first_name, last_name) values (?,?)";
		try (Connection connection = connect();
				PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
			connection.setAutoCommit(false);
			for (Actor actor2 : ActorList) {
				preparedStatement.setString(1, actor2.getFirstname());
				preparedStatement.setString(2, actor2.getLastname());
				
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			connection.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void insertActorsAlternatif(List<Actor> ActorList) {
		String sql = "insert into actor(first_name, last_name) values (?,?)";
		try (Connection connection = connect();
				PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
			int count =0;
			for (Actor actor2 : ActorList) {
				preparedStatement.setString(1, actor2.getFirstname());
				preparedStatement.setString(2, actor2.getLastname());
				preparedStatement.addBatch();
				count ++;
				//her 100 kayıtta bir execute, yada listenin sizena eşit oldugunda execute
				if(count %100 ==0 || count==ActorList.size()) {
					preparedStatement.executeBatch();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//updateLastname --> id ve lastname parametresi alsın boolean değer dönsün
	public boolean updateLastName(int id, String lastName) {
		boolean check =false;
		int affectedRow=0;
		String sql = "update actor set last_name=? where actor_id =?";
		try (Connection connection = connect();
				PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
				preparedStatement.setString(1, lastName);
				preparedStatement.setInt(2, id);
				affectedRow = preparedStatement.executeUpdate();
				
				if(affectedRow>0) {
					check = true;
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}
	
	//delete actor--> id parametresi alsın belirtilen id li actoru silsin
	public boolean deleteActor(int id) {
		boolean check =false;
		int affectedRow=0;
		String sql = "delete from actor where actor_id =?";
		try (Connection connection = connect();
				PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
				preparedStatement.setInt(1, id);
				affectedRow = preparedStatement.executeUpdate();
				
				if(affectedRow>0) {
					check = true;
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}
	

	public static void main(String[] args) {

		// dvdrental db sine bağlanalım, connect metodu yazalım
		Main main = new Main();
		// main.connect();
		// int total = main.getActorCount();
		// System.out.println(total);
		// main.getActors();

		//Actor actor = new Actor("Ayse", "Kaya");
		//System.out.println(main.insertActor(actor)); 
		
//		List<Actor> actorList = new ArrayList<>();
//		Actor actor1 = new Actor("Ayse", "Kaya");
//		Actor actor2 = new Actor("Defne", "Bahar");
//		Actor actor3 = new Actor("Zeynep", "Kış");
//		Actor actor4 = new Actor("Deniz", "Yaz");
//		actorList.add(actor1);
//		actorList.add(actor2);
//		actorList.add(actor3);
//		actorList.add(actor4);
//		
//		main.insertActors(actorList);
		
		//System.out.println(main.updateLastName(780, "Sonbahar")); 
		main.deleteActor(215);
	
		

		
	}
}
