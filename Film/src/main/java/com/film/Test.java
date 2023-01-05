package com.film;


import com.film.util.HibernateUtils;

public class Test {
	
	public static void main(String[] args) {
		
		HibernateUtils.getSessionFactory().openSession();
	}
}
