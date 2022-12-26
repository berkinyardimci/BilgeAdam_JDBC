package com.bilgeadam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Code First
 * username--> unique olsun ve null geçilmesin
 * password 32 charakter olsun
 * gender string
 *  tablomuzun ismide --tbluser
 *  
 *  Crud işlemleri için bir interface açalım, birde userRepository si oluşturalım
 *  save, update, delete, findById, findAll , Generic olsun
 *  
 *  hibernate Configuration ı yapalım
 *  
 *  save
 *  findbyId methodu yazalım find methodu parametre olarak ne alıyo
 *  
 *  delete 
 *  
 *  update 
 *  
 * 
 */

@Entity
@Table(name = "tbluser")
public class User {

	// generated value strategies neler var fakları neler
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(length = 32)
	private String password;

	private String gender;

	public User(String username, String password, String gender) {
		super();
		this.username = username;
		this.password = password;
		this.gender = gender;
	}
	public User() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", gender=" + gender + "]";
	}

}
