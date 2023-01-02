package com.bilgeadam.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.bilgeadam.entity.Author;

public class AuthorDao implements IRepository<Author> {

	@Override
	public void create(Author entity) {
		Session session = null;
		try {
			session = dataBaseConnectionHibernate();
			session.getTransaction().begin();
			session.persist(entity);
			session.getTransaction().commit();
			System.out.println("Author data is added to DB");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding Author to DB");
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(long id) {
		Session session = null;
		try {
			Author deletedAuthor = find(id);
			if (deletedAuthor != null) {
				session = dataBaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(deletedAuthor);
				session.getTransaction().commit();
				System.out.println("Author data is added to DB");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding Author to DB");
		} finally {
			session.close();
		}
	}

	@Override
	public void update(long id, Author entity) {
		Session session = null;
		try {
			Author author = find(id);

			if (author != null) {
				author.setFirstName(entity.getFirstName());
				author.setLastName(entity.getLastName());
				author.setBookList(entity.getBookList());

				session = dataBaseConnectionHibernate();
				session.getTransaction().begin();
				session.update(author);
				session.getTransaction().commit();
				System.out.println("Author data is added to DB");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding Author to DB");
		} finally {
			session.close();
		}

	}

	@Override
	public List<Author> listAll() {
		Session session = null;
		session = dataBaseConnectionHibernate();
		String query = "select author from Author as author";
		TypedQuery<Author> typedQuery = session.createQuery(query, Author.class);
		List<Author> authorList = typedQuery.getResultList();
		authorList.forEach(System.out::println);
		return authorList;
	}

	@Override
	public Author find(long id) {
		Session session = dataBaseConnectionHibernate();
		Author author;
		try {
			author = session.find(Author.class, id);
			if (author != null) {
				System.out.println("Author Found--> " + author);
				return author;
			} else {
				System.out.println("Author not found");
				return null;
			}
		} catch (Exception e) {
			System.out.println("Some problem occured while adding Author to DB");
		} finally {
			session.close();
		}
		return null;
	}

	public Author findByName2(String firstName, String lastName) {
		Author author = null;
		Session session = null;
		session = dataBaseConnectionHibernate();
		try {
			String query = "select author from Author as author where firstName =:key1 and lastName =:key2";
			TypedQuery<Author> typedQuery = session.createQuery(query, Author.class);
			typedQuery.setParameter("key1", firstName);
			typedQuery.setParameter("key2", lastName);
			author = typedQuery.getSingleResult();

		} catch (Exception e) {
			System.out.println("problem var");
			Author author2 = new Author(firstName, lastName);
			create(author2);
			return author2;

		}
		return author;
	}

	public Author findByName(String firstname, String lastname) {
		Session session = dataBaseConnectionHibernate();
		Author author = null;
		String hql = "select a from Author as a where a.firstName =:fn and a.lastName =:ln ";
		Query query = session.createQuery(hql);
		query.setParameter("fn", firstname);
		query.setParameter("ln", lastname);
		try {
			author = (Author) query.getSingleResult();
		} catch (Exception e) {
			System.out.println("yeni yazar oluşturulcak");
		}
		if (author != null) {
			return author;
		} else {
			System.out.println("Bylynamadı");
			return null;
		}
	}
/*
	public Optional<Author> findByName3(String firstname, String lastname) {
		Session session = dataBaseConnectionHibernate();
		Author author = null;
		String hql = "select a from Author as a where a.firstName =:fn and a.lastName =:ln ";
		Query query = session.createQuery(hql);
		query.setParameter("fn", firstname);
		query.setParameter("ln", lastname);
		try {
			author = (Author) query.getSingleResult();
			return Optional.of(author);
		} catch (Exception e) {
			return Optional.empty();

		}
	}
	*/

}
