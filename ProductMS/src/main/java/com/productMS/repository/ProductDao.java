package com.productMS.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.productMS.entity.Product;

public class ProductDao implements IRepository<Product> {
	@Override
	public void create(Product entity) {
		Session session = null;

		try {
			session = databaseConnectionHibernate();
			session.getTransaction().begin();
			session.save(entity);
			session.getTransaction().commit();
			System.out.println("Product data is added to DB");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding Product data");
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(long id) {
		Session session = null;
		try {
			Product deleteProduct = find(id);
			if (deleteProduct != null) {

				session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(deleteProduct);
				session.getTransaction().commit();

				System.out.println("Product data is deleted from DB");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while Deleting Product");
		} finally {
			session.close();
		}
	}

	@Override
	public void update(long id, Product entity) {
		Session session = null;

		try {
			Product product = find(id);
			if (product != null) {
				product.setName(entity.getName());
				product.setPrice(entity.getPrice());
				product.setCategory(entity.getCategory());
				product.setStock(entity.getStock());
				product.setCustomers(entity.getCustomers());

				session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.merge(product);
				session.getTransaction().commit();
				System.out.println("Successful Product update ");

			}
		} catch (Exception e) {
			System.out.println("Some problem has occured while Product UPDATE.");
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public List<Product> listAll() {
		Session session = null;

		session = databaseConnectionHibernate();
		String query = "select product from Product as product";
		TypedQuery<Product> typedQuery = session.createQuery(query, Product.class);
		List<Product> productList = typedQuery.getResultList();

		for (Product product : productList) {
			System.out.println(product);
		}
		return productList;
	}

	@Override
	public Product find(long id) {
		Session session = databaseConnectionHibernate();
		Product product;

		try {
			product = session.find(Product.class, id);

			if (product != null) {
				System.out.println("Product found: " + product);
				return product;
			} else {
				System.out.println("Product not found");
				return product;
			}

		} catch (Exception e) {
			System.out.println("Some problems has occured during Product find operation.");

		} finally {
			session.close();
		}

		return null;
	}
}
