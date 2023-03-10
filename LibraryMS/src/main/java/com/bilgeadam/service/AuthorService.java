package com.bilgeadam.service;

import java.util.List;
import java.util.Optional;

import com.bilgeadam.dao.AuthorDao;
import com.bilgeadam.entity.Author;

public class AuthorService implements IService<Author>{

	private AuthorDao authorDao;

	public AuthorService() {

		this.authorDao = new AuthorDao();
	}

	@Override
	public void create(Author entity) {
		authorDao.create(entity);
		
	}

	@Override
	public void delete(long id) {
		authorDao.delete(id);
		
	}

	@Override
	public void update(long id, Author entity) {
		authorDao.update(id, entity);
		
	}

	@Override
	public List<Author> listAll() {
		return authorDao.listAll();
	}

	@Override
	public Author find(long id) {
		Author author = authorDao.find(id);
		return author;
	}
	
	public Author findByName(String firstName, String lastName) {
		return authorDao.findByName(firstName, lastName);
	}
	/*
	public Optional<Author> findByName3(String firstName, String lastName) {
		return authorDao.findByName3(firstName, lastName);
	}
	*/
}
