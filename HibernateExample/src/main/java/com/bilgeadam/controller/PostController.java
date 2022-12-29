package com.bilgeadam.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.bilgeadam.entity.Post;
import com.bilgeadam.entity.User;

import com.bilgeadam.repository.PostRepository;
import com.bilgeadam.repository.UserRepository;


public class PostController {

	public static void main(String[] args) {

		PostRepository postRepository = new PostRepository();
		UserRepository userRepository = new UserRepository();
		Date date = new Date();
		/*
		
		Adress address1 = new Adress("Fatih cad", "Türkiye", "Ankara");
		Adress address2 = new Adress("Baris Cad", "Türkiye", "Ankara");
		Map<EAdressType, Adress> map = new HashMap();
		map.put(EAdressType.HOME, address1);
		map.put(EAdressType.BUSSINNES, address2);
		List<String> interest = new ArrayList<>();
		interest.add("Tiyatro");
		interest.add("Müzik");
		UserDetail userDetail = new UserDetail(EGender.MAN, new Name("Esat", "Aras", "Gürel"), map, interest, 10);
		User user = new User("userssssss", "123", userDetail);
*/

		User user1 = userRepository.findById(3);
		
		System.err.println(user1.getUsername());

		postRepository.save(new Post("içerik 55 ", date, user1));
		
		
		//15:05

		
	}
}
