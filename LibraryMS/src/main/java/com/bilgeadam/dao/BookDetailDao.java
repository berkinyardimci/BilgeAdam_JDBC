package com.bilgeadam.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.bilgeadam.entity.BookDetail;

public class BookDetailDao implements IRepository<BookDetail> {

	@Override
	public void create(BookDetail entity) {
		Session session = null;
		try {
			session = dataBaseConnectionHibernate();
			session.getTransaction().begin();
			session.persist(entity);
			session.getTransaction().commit();
			System.out.println("BookDetail data is added to DB");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding BookDetail to DB");
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(long id) {
		Session session = null;
		try {
			BookDetail deletedBookDetail = find(id);
			if (deletedBookDetail != null) {
				session = dataBaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(deletedBookDetail);
				session.getTransaction().commit();
				System.out.println("BookDetail data is added to DB");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding BookDetail to DB");
		} finally {
			session.close();
		}
	}

	@Override
	public void update(long id, BookDetail entity) {
		Session session = null;
		try {
			BookDetail bookDetail = find(id);
			
			if (bookDetail != null) {
				bookDetail.setBook(entity.getBook());
				bookDetail.setTitle(entity.getTitle());
				bookDetail.setBookBorrowDate(entity.getBookBorrowDate());
				bookDetail.setBorrowed(entity.isBorrowed());
				bookDetail.setBookReturnDate(entity.getBookReturnDate());
				
				session = dataBaseConnectionHibernate();
				session.getTransaction().begin();
				session.update(bookDetail);
				session.getTransaction().commit();
				System.out.println("BookDetail data is added to DB");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding BookDetail to DB");
		} finally {
			session.close();
		}

	}

	@Override
	public List<BookDetail> listAll() {
		Session session = null;
		session = dataBaseConnectionHibernate();
		String query = "select bookDetail from BookDetail as bookDetail";
		TypedQuery<BookDetail> typedQuery = session.createQuery(query, BookDetail.class);
		List<BookDetail> bookDetailList = typedQuery.getResultList();
		bookDetailList.forEach(System.out::println);
		return bookDetailList;
	}

	@Override
	public BookDetail find(long id) {
		Session session = dataBaseConnectionHibernate();
		BookDetail bookDetailList;
		try {
			bookDetailList = session.find(BookDetail.class, id);
			if (bookDetailList != null) {
				System.out.println("BookDetail Found--> " + bookDetailList);
				return bookDetailList;
			} else {
				System.out.println("BookDetail not found");
				return null;
			}
		} catch (Exception e) {
			System.out.println("Some problem occured while adding BookDetail to DB");
		} finally {
			session.close();
		}
		return null;
	}

}
