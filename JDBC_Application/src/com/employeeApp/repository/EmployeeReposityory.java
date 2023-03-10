package com.employeeApp.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.employeeApp.entitiy.Employee;
import com.employeeApp.util.DBConnection;





public class EmployeeReposityory implements IEmployeeRepository {

	private Connection connection = DBConnection.connect();

	@Override
	public void createEmployee(Employee employee) {
		String sql = "insert into employee (name,salary,age) values (?,?,?) ";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setInt(2, employee.getSalary());
			preparedStatement.setInt(3, employee.getAge());
			preparedStatement.executeUpdate();

			System.out.println(employee.getName() + " Veri Tabanına Eklendi");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void getAllEmployee() {
		String sql = "select * from employee ";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			displayEmployee(resultSet);
		} catch (Exception e) {
		}
	}

	private void displayEmployee(ResultSet resultSet) throws SQLException {
		while (resultSet.next()) {
			System.out.println(resultSet.getInt("ID") + "-" + "\t" + resultSet.getString("Name") + "\t\t"
					+ resultSet.getInt("Salary") + "\t" + resultSet.getInt("Age"));
		}
	}

	@Override
	public void getEmployeeById(int id) {
		String sql = "select * from employee where id =  " + id;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			displayEmployee(resultSet);
		} catch (Exception e) {
		}
	}

	@Override
	public void updateEmployeeById(int id, String name) {
		String sql = " update employee set name = ? where id =" + id;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			int effectedRow = preparedStatement.executeUpdate();

			if (effectedRow > 0) {
				System.out.println("Updated Success");
			} else {
				System.out.println("Failed");
			}
		} catch (Exception e) {
		}
	}

	@Override
	public void deleteEmployeeById(int id) {
		String sql = "delete from employee where id = " + id;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			int effectedRow = preparedStatement.executeUpdate();

			if (effectedRow > 0) {
				System.out.println("Delete Success");
			} else {
				System.out.println("Failed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
