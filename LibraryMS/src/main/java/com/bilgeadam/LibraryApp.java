package com.bilgeadam;

import com.bilgeadam.util.HibernateUtils;
import com.bilgeadam.util.LibrarySystemMenu;

public class LibraryApp {

	// MVC
	// Model, view, Controller

	public static void main(String[] args) {

		 //HibernateUtils.getSessionFactory().openSession();

		LibrarySystemMenu menu = new LibrarySystemMenu();
		while (true) {
			menu.menu();
		}
		
		 //17:45
	}
}
