package com.bilgeadam.hql;

import java.util.Iterator;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.bilgeadam.entity.User;
import com.bilgeadam.entity.UserDetail;
import com.bilgeadam.util.HibernateUtils;

public class HqlTest {

	/*
	 * bir değer gircez String olarak girdiğimiz değer ile başlayan isimleri bulalım
	 * 
	 * 
	 * User tablosuna post number diye bi colon eklicez sonrada post numbere 10 dan
	 * büyük olanları getirelim
	 * 
	 * Postnumber
	 * 
	 * min max ortalama bulalım sum
	 * 
	 * girilen 2 değer arasındaki post numberleri göstersin
	 * 
	 *
	 * 
	 */

	public static void findAll() {
		String hql = "select user from UserDetail as user";
		Session session = HibernateUtils.getSessionFactory().openSession();
		TypedQuery<UserDetail> typedQuery = session.createQuery(hql, UserDetail.class);
		List<UserDetail> users = typedQuery.getResultList();
		users.forEach(System.out::println);
	}

	//
	public static void startLike(String s) {
		String hql = "select user from UserDetail as user where firstName like '" + s + "%'";
		Session session = HibernateUtils.getSessionFactory().openSession();
		TypedQuery<UserDetail> typedQuery = session.createQuery(hql, UserDetail.class);
		List<UserDetail> users = typedQuery.getResultList();
		users.forEach(System.out::println);
	}

	public static void startLikes(String value) {
		String hql2 = "select user from UserDetail as user where user.name.lastName like :key";
		Session session = HibernateUtils.getSessionFactory().openSession();
		TypedQuery<UserDetail> typedQuery = session.createQuery(hql2, UserDetail.class);
		typedQuery.setParameter("key", value + "%");
		List<UserDetail> users = typedQuery.getResultList();
		users.forEach(System.out::println);
	}

	public static void graterThan(int number) {
		String hql2 = "select user from User as user where user.postNumber >:key ";
		Session session = HibernateUtils.getSessionFactory().openSession();
		TypedQuery<User> typedQuery = session.createQuery(hql2, User.class);
		typedQuery.setParameter("key", number);
		List<User> users = typedQuery.getResultList();
		users.forEach(System.out::println);
	}

	public static void minPostNumber() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		String minHql = "select min(user.postNumber) from User as user ";
		Query minQuery = session.createQuery(minHql);
		System.out.println("min--> " + minQuery.getSingleResult());
	}

	public static void maxPostNumber() {
		String hql2 = "select user from UserDetail as user where user.postNumber "
				+ "in (select max(user.postNumber) from UserDetail as user) ";
		Session session = HibernateUtils.getSessionFactory().openSession();
		TypedQuery<UserDetail> typedQuery = session.createQuery(hql2, UserDetail.class);
		UserDetail user = typedQuery.getSingleResult();
		System.out.println(user.getName().getName());
	}

	public static void avgPostNumber() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		String minHql = "select avg(user.postNumber) from UserDetail as user ";
		Query avgQuery = session.createQuery(minHql);
		System.out.println("avg--> " + avgQuery.getSingleResult());
	}

	public static void avgPostNumberAlternatif() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		String avgHql = "select avg(user.postNumber) from UserDetail as user ";
		Double avg = session.createQuery(avgHql, Double.class).getSingleResult();
		System.out.println(avg);
	}

	public static void sumPostNumber() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		String sumHql = "select sum(user.postNumber) from UserDetail as user ";
		long sum = session.createQuery(sumHql, Long.class).getSingleResult();
		System.out.println(sum);
	}

	// Hibernate, ORM, JDBC, JPA bunların arasındaki ilişkiler ne oldukları,
	// farkları, neden kullanıyoruz

	public static void betweenPostNumber(int number1, int number2) {
		String hql2 = "select user from UserDetail as user where user.postNumber between :number1 and :number2 ";
		Session session = HibernateUtils.getSessionFactory().openSession();
		TypedQuery<UserDetail> typedQuery = session.createQuery(hql2, UserDetail.class);
		typedQuery.setParameter("number1", number1);
		typedQuery.setParameter("number2", number2);
		List<UserDetail> posts = typedQuery.getResultList();
		posts.forEach(System.out::println);
	}

	public static void betweenPostNumber2(int number1, int number2) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		String HQL_QUERY = "select user from UserDetail user where user.postNumber between :number1 and :number2";
		TypedQuery<UserDetail> typedQuery = session.createQuery(HQL_QUERY,UserDetail.class);
		typedQuery.setParameter("number1", number1);
		typedQuery.setParameter("number2", number2);
		List<UserDetail> posts = typedQuery.getResultList();
		for (UserDetail user : posts) {
			System.out.println(user.getName().getLastName());
		}
	}

	public static void main(String[] args) {
		// findAll();
		// startLikes("Y");
		// graterThan(10);
		// minPostNumber();
		// maxPostNumber();
		// avgPostNumber();
		// avgPostNumberAlternatif();
		// sumPostNumber();
		// betweenPostNumber(9, 56);
		betweenPostNumber2(10,45);
	}

	// 14:40
}
