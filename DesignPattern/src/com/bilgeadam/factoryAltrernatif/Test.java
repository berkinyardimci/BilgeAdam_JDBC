package com.bilgeadam.factoryAltrernatif;

public class Test {

	public static void main(String[] args) {
		System.out.println(CarFactory.builCar(CarType.HATCBACK));
		System.out.println(CarFactory.builCar(CarType.SEDAN));
		System.out.println(CarFactory.builCar(CarType.JEEP));

	}

}
