package com.film.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Oyuncu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	//private List<Odul> odul;
	
	@ManyToMany(mappedBy = "oyuncu")
	private Set<Film> films;

	public Oyuncu(int id, String name, List<Odul> odul) {
		super();
		this.id = id;
		this.name = name;
		this.films = new HashSet<>();
	}

	public Oyuncu() {
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


}
