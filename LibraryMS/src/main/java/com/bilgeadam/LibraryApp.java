package com.bilgeadam;

import com.bilgeadam.util.HibernateUtils;

public class LibraryApp {
	
	//MVC
	//Model, view, Controller
	
	public static void main(String[] args) {
		
		HibernateUtils.getSessionFactory().openSession();
		
	}
}
