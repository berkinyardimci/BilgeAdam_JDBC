package com.bilgeadam.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.bilgeadam.entity.Car;
import com.bilgeadam.util.DBConnection;

public class CarRepository implements ICrud<Car>{

	DBConnection dbConnection = new DBConnection();
	Connection connection = dbConnection.connect();
		
	@Override
	public void save(Car t) {
		
	}

	@Override
	public void update(Car t, long id) {
		
	}

	@Override
	public void delete(long id) {
		
	}

	@Override
	public List<Car> findAll() {
		return null;
	}

	@Override
	public void saveAll(List<Car> t) {
		String sql = "insert into cars values(?,?,?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			for (Car car : t) {
				preparedStatement.setLong(1, car.getId());
				preparedStatement.setString(2, car.getBrand());
				preparedStatement.setString(3, car.getCarModel());
				preparedStatement.setString(4, car.getModelYear());
				preparedStatement.setLong(5, car.getDealerShipId());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
