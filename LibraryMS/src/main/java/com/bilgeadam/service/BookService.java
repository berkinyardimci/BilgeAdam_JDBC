package com.bilgeadam.service;

import java.util.List;

import com.bilgeadam.dao.BookDao;
import com.bilgeadam.entity.Book;

public class BookService implements IService<Book> {

	private BookDao bookDao;

	public BookService() {

		this.bookDao = new BookDao();
	}

	@Override
	public void create(Book entity) {
		bookDao.create(entity);

	}

	@Override
	public void delete(long id) {
		bookDao.delete(id);

	}

	@Override
	public void update(long id, Book entity) {
		bookDao.update(id, entity);

	}

	@Override
	public List<Book> listAll() {
		
		return bookDao.listAll();
	}

	@Override
	public Book find(long id) {
		Book book = bookDao.find(id);
		return book;
	}

}
