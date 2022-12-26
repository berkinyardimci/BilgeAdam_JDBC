package com.bilgeadam.controller;

import com.bilgeadam.entity.User;
import com.bilgeadam.repository.UserDao;

public class UserController {
	
	public static void main(String[] args) {
		
		
		UserDao userDao = new UserDao();
		
		//User user = new User("mehmet", "1234", "erkek");
		//userDao.save(user);
		
		//userDao.findById(17);
		//userDao.delete(5);
		
		User user = new User("ayşe", "sssssssss", "erkek");
		userDao.update(user, 7);
		//userDao.updateAlternatif(user, 7);
	}
}
