package com.productMS.controller;

import com.productMS.entity.Product;
import com.productMS.entity.ProductDetail;
import com.productMS.service.ProductDetailService;
import com.productMS.service.ProductService;
import com.productMS.util.BAUtils;

public class ProductDetailController {

	private ProductService productService;
	private ProductDetailService productDetailService;

	public ProductDetailController() {
		this.productService = new ProductService();
		this.productDetailService = new ProductDetailService();
	}
	
	public void evaluate() {
		long productId = BAUtils.readInt("Yorum yapmak istediğiniz Product ID'si: ");
		String customerComment = BAUtils.readString("Yorumunuz: ");
		int customerPuan = BAUtils.readInt("Puanınız: ");
		
		Product product = productService.find(productId);
		ProductDetail productDetail = new ProductDetail();
		
		productDetail.setComment(customerComment);
		productDetail.setPuan(customerPuan);
		
		product.getProductDetails().add(productDetail);
		productDetail.setProduct(product);
		productDetailService.create(productDetail);
	}

	public void listCommentByID() {
		long productId = BAUtils.readInt("Yorumları ve puanlarını görmek istediğiniz Product ID'si: ");
		Product product = productService.find(productId);
		
		product.getProductDetails().
		forEach(productDetail-> System.out.println(productDetail.getId() + " "+ productDetail.getComment() + " " + productDetail.getPuan()));
		
	}

}
