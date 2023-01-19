package com.productMS.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.productMS.entity.ProductDetail;

public class ProductDetailDao implements IRepository<ProductDetail> {
	@Override
	public void create(ProductDetail entity) {
		Session session = null;

		try {
			session = databaseConnectionHibernate();
			session.getTransaction().begin();
			session.save(entity);
			session.getTransaction().commit();
			System.out.println("ProductDetail data is added to DB");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding ProductDetail data");
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(long id) {
		Session session = null;
		try {
			ProductDetail deleteProductDetail = find(id);
			if (deleteProductDetail != null) {

				session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(deleteProductDetail);
				session.getTransaction().commit();

				System.out.println("ProductDetail data is deleted from DB");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while Deleting ProductDetail");
		} finally {
			session.close();
		}
	}

	@Override
	public void update(long id, ProductDetail entity) {
		Session session = null;

		try {
			ProductDetail productDetail = find(id);
			if (productDetail != null) {
				productDetail.setComment(entity.getComment());
				productDetail.setPuan(entity.getPuan());
				productDetail.setProduct(entity.getProduct());

				session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.update(productDetail);
				session.getTransaction().commit();
				System.out.println("Successful ProductDetail update ");

			}
		} catch (Exception e) {
			System.out.println("Some problem has occured while ProductDetail UPDATE.");
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public List<ProductDetail> listAll() {
		Session session = null;

		session = databaseConnectionHibernate();
		String query = "select productDetail from ProductDetail as productDetail";
		TypedQuery<ProductDetail> typedQuery = session.createQuery(query, ProductDetail.class);
		List<ProductDetail> productDetailList = typedQuery.getResultList();

		for (ProductDetail productDetail : productDetailList) {
			System.out.println(productDetail);
		}
		return productDetailList;
	}

	@Override
	public ProductDetail find(long id) {
		Session session = databaseConnectionHibernate();
		ProductDetail productDetail;

		try {
			productDetail = session.find(ProductDetail.class, id);

			if (productDetail != null) {
				System.out.println("ProductDetail found: " + productDetail);
				return productDetail;
			} else {
				System.out.println("ProductDetail not found");
				return productDetail;
			}

		} catch (Exception e) {
			System.out.println("Some problems has occured during ProductDetail find operation.");

		} finally {
			session.close();
		}

		return null;
	}
}
