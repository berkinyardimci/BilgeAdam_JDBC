package com.bilgeadam.util;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.bilgeadam.controller.BookController;
import com.bilgeadam.controller.StudentController;
import com.bilgeadam.entity.Student;

public class LibrarySystemMenu {

	StudentController studentController;
	BookController bookController;

	public LibrarySystemMenu() {
		super();
		this.studentController = new StudentController();
		this.bookController = new BookController();

	}

	public void menu() {
		HashMap<Integer, String> menuItems = new HashMap<>();
		menuItems.put(1, "Admin");
		menuItems.put(2, "Öğrenci Login");
		int key = BAUtils.menu(menuItems);
		switch (key) {
		case 1:
			// adminLogin
			adminMenu();
			break;
		case 2:
			studentLogin();
			break;

		default:
			break;
		}
	}

	private void adminMenu() {
		HashMap<Integer, String> menuItems = new HashMap<>();
		menuItems.put(1, "Ogrenci ekle");
		menuItems.put(2, "Ogrenci sil");
		menuItems.put(3, "Kitap Ekle");
		menuItems.put(4, "Kitap Sil");
		menuItems.put(5, "Tüm kitapları listele");
		menuItems.put(6, "Kirada olan kitapları listele");
		menuItems.put(7, "Kitabın ne zaman döneceği bilgisi");
		
		int key = BAUtils.menu(menuItems);

		switch (key) {
		case 1:
			studentController.create();
			break;
		case 2:
			studentController.delete();
			break;
		case 3:
			bookController.createBook();
			break;
		case 4:
			bookController.delete();
			break;
		case 5:
			bookController.listAll();
			break;
		case 6:
			bookController.listBorrowedBooks();
			break;
		case 7:
			bookController.returnDate();
			break;
		default:
			break;
		}
	}

	private void studentMenu(Student student) {
		HashMap<Integer, String> menuItems = new HashMap<>();
		menuItems.put(1, "Kitap Ödünç Al");
		menuItems.put(2, "Kitabı Geri İadeEt");
		int key = BAUtils.menu(menuItems);

		switch (key) {
		case 1:
			bookController.borrowBook(student);
			break;
		case 2:
			bookController.returnBook(student);
			break;
		default:
			break;
		}
	}

	private void studentLogin() {
		String username = BAUtils.readString("Lütfen username giriniz: ");
		String password = BAUtils.readString("Lütfen password giriniz: ");

		Optional<Student> student = studentController.findByUserName(username);
		
		if (student.isPresent()) {
			if (student.get().getPassword().equals(password)) {
				studentMenu(student.get());
			} else {
				System.out.println("Şifreyi kontrol edin");
			}
		} else {
			System.out.println("Username hatalı");
		}

	}

}
