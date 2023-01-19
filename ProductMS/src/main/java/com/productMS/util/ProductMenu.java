package com.productMS.util;

import java.util.HashMap;

import com.productMS.controller.CustomerController;
import com.productMS.controller.ProductController;

public class ProductMenu {
	private ProductController productController;
	private CustomerController customerController;

	public ProductMenu() {
		this.productController = new ProductController();
		this.customerController = new CustomerController();
	}

	public void menu() {
		HashMap<Integer, String> menuItems = new HashMap<>();
		menuItems.put(1, "Admin");
		menuItems.put(2, "Customer");

		int key = BAUtils.menu(menuItems);

		switch (key) {
		case 1:
			adminMenu();
			break;
		case 2:
			customerMenu();
			break;

		default:
			break;
		}
	}

	private void customerMenu() {
		HashMap<Integer, String> menuItems = new HashMap<>();
		menuItems.put(1, "Kayıt Ol");
		menuItems.put(2, "Giriş yap");
		int key = BAUtils.menu(menuItems);

		switch (key) {
		case 1:
			customerController.customerRegister();
			break;
		case 2:
			customerController.customerLogin();
			break;
		default:
			break;
		}
	}

	private void adminMenu() {
		HashMap<Integer, String> menuItems = new HashMap<>();

		while (true) {
			menuItems.put(1, "Category ekle");
			menuItems.put(2, "Product ekle");
			menuItems.put(3, "Customerları listele");
			menuItems.put(4, "Menuye Dön");
			int key = BAUtils.menu(menuItems);
			switch (key) {
			case 1:
				productController.createCategory();
				break;
			case 2:
				productController.createProduct();
				break;
			case 3:

				break;
			case 4:
				menu();
				break;

			default:
				break;
			}
		}

	}
}
