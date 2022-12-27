package com.bilgeadam.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Name {

	private String name;
	private String middleName;
	private String lastName;

	public Name(String name, String middleName, String lastName) {
		super();
		this.name = name;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	public Name() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
