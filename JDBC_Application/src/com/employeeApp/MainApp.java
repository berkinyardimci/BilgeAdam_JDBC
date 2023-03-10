package com.employeeApp;

import java.sql.SQLException;
import java.util.Scanner;

import com.employeeApp.entitiy.Employee;
import com.employeeApp.repository.EmployeeReposityory;
import com.employeeApp.repository.IEmployeeRepository;
import com.employeeApp.util.DBConnection;

public class MainApp {

	public static void main(String[] args) {
		//veritabanı tablosu oluşturalım
		//name String, salary int, age int
		//Employee clasını yazalım veritabana tablosuna göre
		
		IEmployeeRepository repository = new EmployeeReposityory();
		
		
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("1. Create Employee \n" + "2. Bütün Çalışanları Göster\n" + "3. Id ye göre Çalışan Getir \n"
					+ "4. Çalışanı Güncelle \n" + "5. Çalışanı sil\n" + "6. Çıkış\n");
			
			System.out.println("Choose a number");
			int value = scanner.nextInt();
			switch (value) {
			case 1:
				System.out.println("Name: ");
				String name= scanner.next();
				System.out.println("Salary: ");
				int salary =scanner.nextInt();
				System.out.println("Age: ");
				int age = scanner.nextInt();
				Employee employee = new Employee(name, salary, age);
				repository.createEmployee(employee);
				break;
			case 2:
				repository.getAllEmployee();
				break;
			case 3:
				System.out.println("ID: ");
				int employeeId = scanner.nextInt();
				repository.getEmployeeById(employeeId);
				break;
			case 4:
				System.out.println("ID: ");
				employeeId = scanner.nextInt();
				scanner.nextLine();
				System.out.println("Name: ");
				name = scanner.next();
				repository.updateEmployeeById(employeeId, name);
				break;
			case 5:
				System.out.println("ID: ");
				employeeId = scanner.nextInt();
				repository.deleteEmployeeById(employeeId);
				break;
			case 6:
				try {
					DBConnection.connect().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.exit(0);
				System.out.println("Exited");
				break;

			default:
				System.out.println("please choose a correct number");
				break;
			}
			
			
		}while(true);
		
		
		
	}

}
