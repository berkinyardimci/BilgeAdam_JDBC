package com.film.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Yonetmen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private List<Odul> odul;

	public Yonetmen(int id, String name, List<Odul> odul) {
		super();
		this.id = id;
		this.name = name;
		this.odul = odul;
	}

	public Yonetmen() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Odul> getOdul() {
		return odul;
	}

	public void setOdul(List<Odul> odul) {
		this.odul = odul;
	}

}
