package com.bilgeadam.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Transaction;

import org.hibernate.Session;

import com.bilgeadam.entity.User;
import com.bilgeadam.util.HibernateUtils;

public class UserRepository implements ICrud<User> {

	private Session session;
	private Transaction transaction;
	private CriteriaBuilder criteriaBuilder;
	private EntityManager entityManager;

	// set methodları yok, nasıl set ederiz

	// openTransaction
	// successClose
	// errorClose

	public UserRepository() {
		entityManager = HibernateUtils.getSessionFactory().createEntityManager();
		criteriaBuilder = entityManager.getCriteriaBuilder();
	}

	public void openTransaction() {
		session = dataBaseConnectionHibernate();
		transaction = session.beginTransaction();
	}

	public void successClose() {
		transaction.commit();
		session.close();
	}

	public void errorClose() {
		transaction.rollback();
		session.close();
	}

	@Override
	public void save(User t) {
		try {
			openTransaction();
			session.save(t);
			successClose();
		} catch (Exception e) {
			e.printStackTrace();
			errorClose();
		}
	}

	@Override
	public void update(User t, long id) {
		try {
			openTransaction();
			t.setId(id);
			session.update(t);
			successClose();
		} catch (Exception e) {
			errorClose();
		}
	}

	@Override
	public void delete(long id) {
		User user = findById(id);
		if (user != null) {
			try {
				openTransaction();
				session.delete(user);
				successClose();
			} catch (Exception e) {
				errorClose();
			}
		} else {
			System.out.println(id + " li kullanıcı bulunmamaktadır");
		}
	}

	@Override
	public List<User> findAll() {

		openTransaction();
		criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);
		criteriaQuery.select(root);
		List<User> userList = entityManager.createQuery(criteriaQuery).getResultList();
		for (User user : userList) {
			System.out.println(user);
		}
		return userList;
	}

	@Override
	public User findById(long id) {
		openTransaction();
		criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));

		return entityManager.createQuery(criteriaQuery).getSingleResult();
	}

}
