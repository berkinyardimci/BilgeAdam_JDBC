package com.productMS.controller;

import java.util.HashMap;

import com.productMS.entity.Customer;
import com.productMS.entity.Product;
import com.productMS.service.CustomerService;
import com.productMS.util.BAUtils;

public class CustomerController {
	private CustomerService customerService;
	private ProductController productController;
	private ProductDetailController productDetailController;
	
	public CustomerController() {
		this.customerService = new CustomerService();
		this.productController = new ProductController();
		this.productDetailController = new ProductDetailController();
	}
	
	public void customerRegister() {
		String firstName = BAUtils.readString("İsminiz: ");
		String lastName = BAUtils.readString("Soy İsminiz: ");
		String email = BAUtils.readString("email: ");
		String identity = BAUtils.readString("TCkimlik: ");
		String password = BAUtils.readString("Şifreniz: ");
		
		Customer customer = new Customer();
		customer.setEmail(email);
		customer.setFirstName(firstName);
		customer.setIdentity(identity);
		customer.setLastName(lastName);
		customer.setPassword(password);
		customerService.create(customer);
	}
	
	public void customerManager(Customer customer) {
		HashMap<Integer, String> menuItems = new HashMap<>();
		while (true) {
			menuItems.put(1, "Satın al");
			menuItems.put(2, "Yorum yap");
			menuItems.put(3, "Stoku bitmek üzere olan ürünler");
			menuItems.put(4, "Tüm ürünleri listele");
			menuItems.put(5, "Ürüne göre Yorumları listele");
			menuItems.put(6, "Çıkış Yap");
			int key = BAUtils.menu(menuItems);

			switch (key) {
			case 1:
				productController.buyProduct(customer);
				break;
			case 2:
				productDetailController.evaluate();
				break;
			case 3:
				productController.getProductWhereStockSmallerThanten();
				break;
			case 4:
				//productController.listAll();
				break;
			case 5:
				productDetailController.listCommentByID();
				break;
			case 6:
				System.exit(0);
				break;
			default:
				break;
			}
		}
	}
	
	public void customerLogin() {
		String email = BAUtils.readString("email: ");
		String password = BAUtils.readString("Şifreniz: ");
		Customer customer = customerService.findByEmail(email);
		System.out.println(customer+ "+++");
		if(customer== null) {
			System.err.println("Hatalı email: ");
		}else {
			if(customer.getPassword().equals(password)) {
				customerManager(customer);
			}else {
				System.out.println("Şifreyi kotnrol et");
			}
		}
	}
	
	
	
	
}
