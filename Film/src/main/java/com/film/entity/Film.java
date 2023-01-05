package com.film.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Film {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private Set<Oyuncu> oyuncu;

	private Yonetmen yonetmen;

	private Odul odul;

	private Category category;

	private LocalDate production_date;

	public Film(int id, String name, LocalDate production_date, Set<Oyuncu> oyuncu, Yonetmen yonetmen, Odul odul,
			Category category) {
		super();
		this.id = id;
		this.name = name;
		this.production_date = production_date;
		this.oyuncu = oyuncu;
		this.yonetmen = yonetmen;
		this.odul = odul;
		this.category = category;
	}

	public Film() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getProduction_date() {
		return production_date;
	}

	public void setProduction_date(LocalDate production_date) {
		this.production_date = production_date;
	}

	public Set<Oyuncu> getOyuncu() {
		return oyuncu;
	}

	public void setOyuncu(Set<Oyuncu> oyuncu) {
		this.oyuncu = oyuncu;
	}

	public Yonetmen getYonetmen() {
		return yonetmen;
	}

	public void setYonetmen(Yonetmen yonetmen) {
		this.yonetmen = yonetmen;
	}

	public Odul getOdul() {
		return odul;
	}

	public void setOdul(Odul odul) {
		this.odul = odul;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
