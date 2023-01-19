package com.productMS.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;

	private double price;

	private int stock;

	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private Category category;

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinTable(name = "product_customer", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "customer_id"))
	private List<Customer> customers;

	@OneToMany(mappedBy = "product")
	private List<ProductDetail> productDetails;

	public Product() {
		super();
	}

	public Product(long id, String name, double price, int stock, Category category, List<Customer> customers,
			List<ProductDetail> productDetails) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.category = category;
		this.customers = customers;
		this.productDetails = productDetails;
	}

	public Product(String name, double price, int stock, Category category) {
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.category = category;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public List<ProductDetail> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(List<ProductDetail> productDetails) {
		this.productDetails = productDetails;
	}

}
