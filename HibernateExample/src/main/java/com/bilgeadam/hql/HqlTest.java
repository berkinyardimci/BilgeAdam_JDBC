package com.bilgeadam.hql;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.bilgeadam.entity.User;
import com.bilgeadam.util.HibernateUtils;

public class HqlTest {
	
	/*
	 * bir değer gircez String olarak girdiğimiz değer ile başlayan isimleri bulalım
	 * 
	 */
	
	public static void findAll() {
		String hql = "select user from User as user";
		Session session = HibernateUtils.getSessionFactory().openSession();
		TypedQuery<User> typedQuery = session.createQuery(hql, User.class);
		List<User> users = typedQuery.getResultList();
		users.forEach(System.out::println);
	}
	//
	public static void startLike(String s) {
		String hql = "select user from User as user where firstName like '" + s+"%'";
		Session session = HibernateUtils.getSessionFactory().openSession();
		TypedQuery<User> typedQuery = session.createQuery(hql, User.class);
		List<User> users = typedQuery.getResultList();
		users.forEach(System.out::println);
	}
	public static void startLikes(String value) {
		String hql2 = "select user from User as user where user.name.lastName like :key";
		Session session = HibernateUtils.getSessionFactory().openSession();
		TypedQuery<User> typedQuery = session.createQuery(hql2, User.class);
		typedQuery.setParameter("key", value+ "%" );
		List<User> users = typedQuery.getResultList();
		users.forEach(System.out::println);
	}
	
	public static void main(String[] args) {
		findAll();
		startLikes("Y");
	}
}
