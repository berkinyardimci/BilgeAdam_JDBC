package com.productMS.service;

import java.util.List;

import com.productMS.entity.Product;
import com.productMS.repository.ProductDao;

public class ProductService implements IRepository<Product> {
	private ProductDao productDao;

	public ProductService() {
		this.productDao = new ProductDao();
	}

	@Override
	public void create(Product entity) {
		productDao.create(entity);

	}

	@Override
	public void delete(long id) {
		productDao.delete(id);

	}

	@Override
	public void update(long id, Product entity) {
		productDao.update(id, entity);

	}

	@Override
	public List<Product> listAll() {
		// TODO Auto-generated method stub
		return productDao.listAll();
	}

	@Override
	public Product find(long id) {
		Product product = productDao.find(id);
		return product;
	}
}
