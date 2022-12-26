package com.bilgeadam.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bilgeadam.entity.User;

public class HibernateUtils {

	private static final SessionFactory SESSION_FACTORY = sessionFactoryHibernate();

	private static SessionFactory sessionFactoryHibernate() {

		try {
			Configuration configuration = new Configuration();

			configuration.addAnnotatedClass(User.class);
			SessionFactory factory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
			return factory;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

	public static SessionFactory getSessionFactory() {
		return SESSION_FACTORY;
	}
}
