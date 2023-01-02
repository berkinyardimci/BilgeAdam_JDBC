package com.bilgeadam.controller;

import java.util.Optional;

import com.bilgeadam.entity.Author;
import com.bilgeadam.entity.Book;
import com.bilgeadam.entity.BookDetail;
import com.bilgeadam.service.AuthorService;
import com.bilgeadam.service.BookDetailService;
import com.bilgeadam.service.BookService;
import com.bilgeadam.util.BAUtils;

public class BookController {

	BookDetailService bookDetailService;
	AuthorService authorService;
	BookService bookService;

	public BookController() {
		this.bookDetailService = new BookDetailService();
		this.authorService = new AuthorService();
		this.bookService = new BookService();
	}

	public void createBook() {
		Author author;
		Book book = new Book();
		String title = BAUtils.readString("Lütfen eklemek istediğiniz kitabın ismini girin");
		BookDetail bookDetail = new BookDetail();
		bookDetail.setTitle(title);
		bookDetailService.create(bookDetail);

		String firstName = BAUtils.readString("Lütfen yazarın ismini giriniz");
		String lastName = BAUtils.readString("Lütfen yazarın soyadını giriniz");

		author = authorService.findByName(firstName, lastName);
		if (author == null) {
			Author author2 = new Author(firstName, lastName);
			authorService.create(author2);
			book.setAuthor(author2);
		} else {
			book.setAuthor(author);
		}
		book.setDetail(bookDetail);
		bookService.create(book);

		// eğer yazar db varsa yeni kayıt atmicak yazarlar tablosun
		// yoksa hem yazar oluşturcak hemde kitap oluşturcak

	}
/*
	public void createBook2() {
		Author author;
		Book book = new Book();
		String title = BAUtils.readString("Lütfen eklemek istediğiniz kitabın ismini girin");
		BookDetail bookDetail = new BookDetail();
		bookDetail.setTitle(title);
		bookDetailService.create(bookDetail);

		String firstName = BAUtils.readString("Lütfen yazarın ismini giriniz");
		String lastName = BAUtils.readString("Lütfen yazarın soyadını giriniz");

		Optional<Author> optionalAuthor = authorService.findByName3(firstName, lastName);
		if (optionalAuthor.isEmpty()) {
			author = new Author(firstName, lastName);
			
			authorService.create(author);
			System.out.println(optionalAuthor.get().getFirstName()); 
		} else {
			author = optionalAuthor.get();
			System.out.println("yazar db de mevcut");
		}
		book.setAuthor(author);
		book.setDetail(bookDetail);
		bookService.create(book);

		// eğer yazar db varsa yeni kayıt atmicak yazarlar tablosun
		// yoksa hem yazar oluşturcak hemde kitap oluşturcak
*/
	}

