package com.bilgeadam.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bilgeadam.entity.Adress;
import com.bilgeadam.entity.Name;
import com.bilgeadam.entity.User;
import com.bilgeadam.entity.UserDetail;
import com.bilgeadam.entity.enums.EAdressType;
import com.bilgeadam.entity.enums.EGender;
import com.bilgeadam.repository.UserRepository;

public class UserController2 {
	
	public static void main(String[] args) {
		UserRepository userRepository = new UserRepository();
		
		Adress adress = new Adress("Çayyolu", "Turkiye", "Ankara");
		Adress adress2 = new Adress("Bebek", "Turkiye", "İstanbul");
		Map<EAdressType, Adress> map = new HashMap<>();
		map.put(EAdressType.HOME, adress);
		map.put(EAdressType.BUSSINNES, adress2);
		
		List<String> interests = new ArrayList<>();
		interests.add("Coding");
		interests.add("Reading Artical");
		
		UserDetail userDetail = new UserDetail(EGender.MAN, new Name("Mustafa","","kaya"),map,interests,2);
		
		User user = new User("username789822227","5555",userDetail);
		userRepository.save(user);
		
	}
	
	
	
	
}
