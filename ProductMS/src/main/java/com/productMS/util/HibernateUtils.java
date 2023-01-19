package com.productMS.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.productMS.entity.Admin;
import com.productMS.entity.Category;
import com.productMS.entity.Customer;
import com.productMS.entity.Product;
import com.productMS.entity.ProductDetail;

public class HibernateUtils {
	private static SessionFactory sessionFactory = sessionFactory();

	private static SessionFactory sessionFactory() {

		Configuration configuration = new Configuration();

		configuration.addAnnotatedClass(Admin.class);
		configuration.addAnnotatedClass(Customer.class);
		configuration.addAnnotatedClass(Product.class);
		configuration.addAnnotatedClass(ProductDetail.class);
		configuration.addAnnotatedClass(Category.class);

		SessionFactory factory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();

		return factory;
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
