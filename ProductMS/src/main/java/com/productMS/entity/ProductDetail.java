package com.productMS.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProductDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String comment;

	private int puan;
	
	@ManyToOne
	@JoinColumn(name ="product_id", referencedColumnName = "id")
	private Product product;

	public ProductDetail() {

	}

	public ProductDetail(long id, String comment, int puan, Product product) {
		super();
		this.id = id;
		this.comment = comment;
		this.puan = puan;
		this.product = product;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getPuan() {
		return puan;
	}

	public void setPuan(int puan) {
		this.puan = puan;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	

}
