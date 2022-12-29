package com.bilgeadam;

import com.bilgeadam.util.HibernateUtils;

public class Test {
	public static void main(String[] args) {
		
		
		/*
		 * Address --> street, number, city Emdedable
		 * Course --> title, teacher, courseMaterial, students
		 * CourseMaterial--> url, course 
		 * EGender --> MAN, Woman
		 * Student--> firstname, lastname, birthDate, gender, address, courses
		 * Teacher--> firstname, lastname, address, courses
		 * 
		 */
		
		HibernateUtils.getSessionFactory().openSession();

	}
}
