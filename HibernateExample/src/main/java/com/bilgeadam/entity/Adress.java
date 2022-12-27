package com.bilgeadam.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Adress {
	private String streetName;
	private String country;
	private String city;

	public Adress(String streetName, String country, String city) {
		super();
		this.streetName = streetName;
		this.country = country;
		this.city = city;
	}
	public Adress() {

	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
