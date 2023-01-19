package com.productMS.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Customer extends User{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String password;

	@Column(unique = true)
	private String identity;
	
	@ManyToMany(mappedBy = "customers")
	private List<Product> products;

	public Customer() {

	}

	public Customer(long id, String password, String identity, List<Product> products) {
		super();
		this.id = id;
		this.password = password;
		this.identity = identity;
		this.products = products;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", password=" + password + ", identity=" + identity + "]";
	}

}
