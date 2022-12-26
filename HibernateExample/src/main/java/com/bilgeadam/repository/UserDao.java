package com.bilgeadam.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bilgeadam.entity.User;

//16:01

public class UserDao implements ICrud<User> {

	@Override
	public void save(User t) {
		Transaction transaction = null;

		try (Session session = dataBaseConnectionHibernate()) {
			transaction = session.beginTransaction();
			session.save(t);
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println("Saved Error");
		}
	}
	/*
	 * public void saveAlternatif(User t) { Session session = null; try { session =
	 * dataBaseConnectionHibernate(); // database bağlantı kurduk // transaction
	 * başlatıyo session.getTransaction().begin(); // kayıt işlemi session.save(t);
	 * // transaction sonlandırma session.getTransaction().commit(); } catch
	 * (Exception e) { System.out.println("Something Wrong"); e.printStackTrace();
	 * // bi sıkıntı çıkarsa bütün işlemleri iptal etmiş oluyoruz
	 * session.getTransaction().rollback(); } finally { session.close(); } }
	 */

	@Override
	public void update(User t, long id) {
		Transaction transaction = null;
		try (Session session = dataBaseConnectionHibernate()) {
			transaction = session.beginTransaction();
			t.setId(id);
			session.update(t);
			transaction.commit();
			System.out.println(t);
		} catch (Exception e) {
			System.out.println("Updated error");
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	public void updateAlternatif(User t, long id) {
		Session session = null;
		try {
			User user = findById(id);
			if (user != null) {
				user.setUsername(t.getUsername());
				user.setGender(t.getGender());
				user.setPassword(t.getPassword());
				session = dataBaseConnectionHibernate();
				session.getTransaction().begin();
				session.merge(user);
				session.getTransaction().commit();
				System.out.println("Kullanıcı güncellendi");
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	@Override
	public void delete(long id) {
		Transaction transaction = null;
		try (Session session = dataBaseConnectionHibernate()) {
			User user = findById(id);
			if (user != null) {
				transaction = session.beginTransaction();
				session.delete(user);
				transaction.commit();
				System.out.println("User silindi-->" + user.getUsername());
			} else {
				System.out.println("Check ID");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	@Override
	public List<User> findAll() {
		return null;
	}

	@Override
	public User findById(long id) {
		Session session = dataBaseConnectionHibernate();
		User user;

		try {
			user = session.find(User.class, id);
			if (user != null) {
				System.out.println("User bulundu--> " + user);
				return user;
			} else {
				System.out.println("Id yi kontrol edin");
			}
		} catch (Exception e) {
			System.out.println("Something wrong");
		} finally {
			session.close();
		}
		return null;
	}

}
