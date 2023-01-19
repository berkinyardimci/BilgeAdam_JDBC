package com.productMS.service;

import java.util.List;

public interface IRepository<T> {
	public void create(T entity);

	public void delete(long id);

	public void update(long id, T entity);

	public List<T> listAll();

	public T find(long id);
}
