package com.bilgeadam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bilgeadam.entity.Adress;
import com.bilgeadam.entity.Name;
import com.bilgeadam.entity.User;
import com.bilgeadam.entity.enums.EAdressType;
import com.bilgeadam.entity.enums.EGender;
import com.bilgeadam.repository.UserDao;
import com.bilgeadam.repository.UserRepository;

public class UserController {

	public static void main(String[] args) {

		UserDao userDao = new UserDao();

		// User user = new User("mehmet", "1234", "erkek");
		// userDao.save(user);

		// userDao.findById(17);
		// userDao.delete(5);

		// User user = new User("ayşe", "sssssssss", "erkek");
		// userDao.update(user, 7);
		// userDao.updateAlternatif(user, 7);

		// List<User> users =userDao.findAll();
		// System.out.println(users);

		// userDao.findAll2();

		UserRepository repository = new UserRepository();
		// User user = repository.findById(13);
		// System.out.println(user.getUsername());

		// repository.findAll();
		Name name = new Name("Mehmet", null, "Yardımcı");
		User user = new User("ahmet123", "123456", EGender.MAN, name);
		// repository.save(user);

		Adress adress = new Adress("Ayrancı", "Turkiye", "Ankara");
		Adress adress2 = new Adress("Tunalı", "Turkiye", "Ankara");
		Map<EAdressType, Adress> map = new HashMap<>();
		map.put(EAdressType.HOME, adress);
		map.put(EAdressType.BUSSINNES, adress2);

		user.setAddress(map);
		repository.save(user);

	}
}
