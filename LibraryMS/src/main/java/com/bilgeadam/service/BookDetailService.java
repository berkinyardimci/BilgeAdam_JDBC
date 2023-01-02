package com.bilgeadam.service;

import java.util.List;

import com.bilgeadam.dao.BookDetailDao;
import com.bilgeadam.entity.BookDetail;

public class BookDetailService implements IService<BookDetail> {

	private BookDetailDao bookDetailDao;

	public BookDetailService() {

		this.bookDetailDao = new BookDetailDao();
	}

	@Override
	public void create(BookDetail entity) {
		bookDetailDao.create(entity);
		
	}

	@Override
	public void delete(long id) {
		bookDetailDao.delete(id);
		
	}

	@Override
	public void update(long id, BookDetail entity) {
		bookDetailDao.delete(id);
		
	}

	@Override
	public List<BookDetail> listAll() {
		return bookDetailDao.listAll();
	}

	@Override
	public BookDetail find(long id) {
		BookDetail bookDetail = bookDetailDao.find(id);
		return bookDetail;
	}
}
