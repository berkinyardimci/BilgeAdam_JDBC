package com.bilgeadam.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.bilgeadam.entity.Car;

public class FileUtils {

	public static List<String> readFile(String path) {

		List<String> list = new ArrayList<String>();

		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				list.add(line);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}

	// Car entitiy kurcaz
	//Okuma ve yazma işlemlerinde for iterator ile for each için bir fark yok
	public static List<Car> getCarList(List<String> list) {
		List<Car> cars = new ArrayList<>();
		
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			String[] array = string.split(",");
			cars.add(convertToCar(array));
		}
		return cars;
	}
	/*
	public List<Car> getCarListAltarnatif(List<String> list) {
		List<Car> cars = new ArrayList<>();
		for (String string : list) {
			cars.add(convertToCar(string.split(",")));
		}
		return cars;
	}
	*/
	
	public static Car convertToCar(String[] array) {

		Car car = new Car(Long.parseLong(array[0]), array[1], array[2], array[3], Long.parseLong(array[4]));
		return car;
	}

	public static void main(String[] args) {
		
		FileUtils file = new FileUtils();
		List<String> list = file.readFile(Constant.carFile);

		for (String string : list) {
			System.out.println(string);
		}
		
//		FileUtils file = new FileUtils();
//		List<Car> list = file.getCarList(file.readFile(Constant.carFile));
//		
//		for (Car car : list) {
//			System.out.println(car);
//		}
	}
}
