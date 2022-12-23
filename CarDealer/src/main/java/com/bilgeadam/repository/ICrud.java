package com.bilgeadam.repository;

import java.util.List;


public interface ICrud <T>{
	
	void save(T t);
	void update(T t, long id);
	void delete(long id);
	List<T> findAll();
	void saveAll(List<T> t);
	
	
	
	//Interfacelerde gövdeli method yazabilir miyiz
	//java 8 den sonra gövdeli method oluşturabiliriz
}
