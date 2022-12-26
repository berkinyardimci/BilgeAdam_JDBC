package com.bilgeadam.repository;

import java.util.List;

import org.hibernate.Session;

import com.bilgeadam.util.HibernateUtils;

public interface ICrud<T> {

	// Data access object

	void save(T t);

	void update(T t, long id);

	void delete(long id);

	List<T> findAll();

	T findById(long id);

	default Session dataBaseConnectionHibernate() {

		return HibernateUtils.getSessionFactory().openSession();
	}

}
