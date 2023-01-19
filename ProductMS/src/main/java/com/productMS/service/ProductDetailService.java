package com.productMS.service;

import java.util.List;

import com.productMS.entity.ProductDetail;
import com.productMS.repository.ProductDetailDao;

public class ProductDetailService implements IRepository<ProductDetail> {
	private ProductDetailDao productDetailDao;

	public ProductDetailService() {
		this.productDetailDao = new ProductDetailDao();
	}

	@Override
	public void create(ProductDetail entity) {
		productDetailDao.create(entity);

	}

	@Override
	public void delete(long id) {
		productDetailDao.delete(id);

	}

	@Override
	public void update(long id, ProductDetail entity) {
		productDetailDao.update(id, entity);
	}

	@Override
	public List<ProductDetail> listAll() {
		return productDetailDao.listAll();
	}

	@Override
	public ProductDetail find(long id) {
		ProductDetail productDetail = productDetailDao.find(id);
		return productDetail;
	}
}
