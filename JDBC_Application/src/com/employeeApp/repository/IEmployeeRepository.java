package com.employeeApp.repository;

import com.employeeApp.entitiy.Employee;

public interface IEmployeeRepository {
	
	public void createEmployee(Employee employee);
	
	public void getAllEmployee();
	
	public void getEmployeeById(int id);
	
	public void updateEmployeeById(int id, String name);
	
	public void deleteEmployeeById(int id);
}
