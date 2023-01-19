package com.productMS.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin extends User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	public Admin(String firstName, String lastName, String email) {
		super(firstName, lastName, email);
	}

	public Admin() {
		super();
	}
}
