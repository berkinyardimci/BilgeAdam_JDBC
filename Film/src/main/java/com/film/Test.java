package com.film;


import com.film.controller.FilmController;
import com.film.util.HibernateUtils;

public class Test {
	
	public static void main(String[] args) {
		
		//HibernateUtils.getSessionFactory().openSession();
		
		FilmController filmController = new FilmController();
		//filmController.createFilm();
		
		//filmController.delete();
		
		filmController.find();
	}
}
