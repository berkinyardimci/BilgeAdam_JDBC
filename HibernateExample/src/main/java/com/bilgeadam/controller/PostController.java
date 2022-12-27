package com.bilgeadam.controller;

import java.util.Date;

import com.bilgeadam.entity.Post;
import com.bilgeadam.repository.PostRepository;

public class PostController {

	public static void main(String[] args) {

		PostRepository postRepository = new PostRepository();

		Date date = new Date();
		Post post = new Post("Drama", date);

		postRepository.save(post);
		
	}
}
