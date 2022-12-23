package com.bilgeadam.service;

import com.bilgeadam.entity.Car;
import com.bilgeadam.main.Manager;
import com.bilgeadam.repository.CarRepository;
import com.bilgeadam.util.Constant;
import com.bilgeadam.util.FileUtils;

public class CarService {

	CarRepository carRepository;

	public CarService() {
		carRepository = new CarRepository();
	}

	public void fownloadFileToDataBase() {
		if (carRepository.databaseControl()) {
			System.out.println("Already Exist");
		} else {
			carRepository.saveAll2(FileUtils.getCarList(FileUtils.readFile(Constant.carFile)));
			System.out.println("Success");
		}
	}

	public void save(Car t) {
		carRepository.save(Manager.getCarInformation());
	}

}
