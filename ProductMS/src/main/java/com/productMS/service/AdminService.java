package com.productMS.service;

import java.util.List;

import com.productMS.entity.Admin;
import com.productMS.repository.AdminDao;

public class AdminService implements IRepository<Admin> {
	// controller ver repository katmanı arasında bir köprü
	// bussines kodlarımızı yazıyoruz

	// Spring Dependency Injectiion, IoC
	private AdminDao adminDao;

	public AdminService() {
		this.adminDao = new AdminDao();
	}

	@Override
	public void create(Admin entity) {
		adminDao.create(entity);

	}

	@Override
	public void delete(long id) {
		adminDao.delete(id);

	}

	@Override
	public void update(long id, Admin entity) {
		adminDao.update(id, entity);

	}

	@Override
	public List<Admin> listAll() {
		return adminDao.listAll();
	}

	@Override
	public Admin find(long id) {
		Admin admin = adminDao.find(id);
		return admin;
	}

}
