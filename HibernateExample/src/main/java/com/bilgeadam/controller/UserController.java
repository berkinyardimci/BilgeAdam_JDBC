package com.bilgeadam.controller;

import java.util.List;

import com.bilgeadam.entity.User;
import com.bilgeadam.repository.UserDao;
import com.bilgeadam.repository.UserRepository;

public class UserController {
	
	public static void main(String[] args) {
		
		
		UserDao userDao = new UserDao();
		
		//User user = new User("mehmet", "1234", "erkek");
		//userDao.save(user);
		
		//userDao.findById(17);
		//userDao.delete(5);
		
		//User user = new User("ayşe", "sssssssss", "erkek");
		//userDao.update(user, 7);
		//userDao.updateAlternatif(user, 7);
		
		//List<User> users =userDao.findAll();
		//System.out.println(users);
		
		//userDao.findAll2();
		
		UserRepository repository = new UserRepository();
		User user = repository.findById(13);
		System.out.println(user.getUsername());
	}
}
