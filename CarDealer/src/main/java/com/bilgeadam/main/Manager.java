package com.bilgeadam.main;

import java.util.Scanner;

import com.bilgeadam.repository.CarRepository;
import com.bilgeadam.util.Constant;
import com.bilgeadam.util.FileUtils;

public class Manager {
	
	CarRepository carRepository;
	
	public Manager() {
		carRepository = new CarRepository();
	}
	
	public void menu() {
		Scanner scanner = new Scanner(System.in);
		int input =0;
		
		do {
			System.out.println("0-çıkış");
			System.out.println("1-Dosyadan Databaseye verileri aktarma");
			input = Integer.parseInt(scanner.nextLine());
			
			switch (input) {
			case 1:
				carRepository.saveAll(FileUtils.getCarList(FileUtils.readFile(Constant.carFile)));
				System.out.println("Success");
				break;
			case 2:
				
				break;

			default:
				break;
			}
		}while(true);
	}
	public static void main(String[] args) {
		Manager manager = new Manager();
		manager.menu();
	}
}
