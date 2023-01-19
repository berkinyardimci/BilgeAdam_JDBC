package com.productMS.controller;

import java.util.List;

import com.productMS.entity.Category;
import com.productMS.entity.Customer;
import com.productMS.entity.Product;
import com.productMS.service.CategoryService;
import com.productMS.service.ProductService;
import com.productMS.util.BAUtils;

public class ProductController {

	private ProductService productService;
	private CategoryService categoryService;

	public ProductController() {
		this.productService = new ProductService();
		this.categoryService = new CategoryService();
	}

	public void createProduct() {
		String name = BAUtils.readString("Product name: ");
		double price = BAUtils.readDouble("Product price: ");
		int stock = BAUtils.readInt("Product Stock: ");
		long categoryId = BAUtils.readInt("Category ID");
		Category category = categoryService.find(categoryId);

		if (category != null) {
			Product product = new Product(name, price, stock, category);

			productService.create(product);
		} else {

			System.out.println("Category ID kontrol edin");
		}
	}

	public void createCategory() {
		String name = BAUtils.readString("Category name: ");
		Category category = new Category();

		category.setName(name);
		categoryService.create(category);
	}

	public void buyProduct(Customer customer) {
		long productId = BAUtils.readInt("satın almak istediğiniz product ID'sini girin: ");
		int amount = BAUtils.readInt("Kaç adet almak istiyosunuz: ");

		Product product = productService.find(productId);
		product.getCustomers().add(customer);
		customer.getProducts().add(product);
		product.setStock(product.getStock() - amount);
		productService.update(productId, product);

	}

	public void getProductWhereStockSmallerThanten() {
		List<Product> products = productService.listAll().stream().filter(product -> product.getStock() < 10).toList();
		products.forEach(product -> System.out.println(product.getId() + " " + product.getName()));

	}
}
