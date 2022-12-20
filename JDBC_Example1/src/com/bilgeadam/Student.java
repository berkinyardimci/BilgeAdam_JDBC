package com.bilgeadam;

public class Student {

	private int id;
	private String name;
	private int age;
	private String cty;

	public Student(String name, int age, String cty) {
		super();
		this.name = name;
		this.age = age;
		this.cty = cty;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCty() {
		return cty;
	}

	public void setCty(String cty) {
		this.cty = cty;
	}

}
