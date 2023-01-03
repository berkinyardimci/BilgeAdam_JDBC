package com.bilgeadam.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.bilgeadam.entity.Author;
import com.bilgeadam.entity.Book;
import com.bilgeadam.entity.BookDetail;
import com.bilgeadam.entity.Student;
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
	 * public void createBook2() { Author author; Book book = new Book(); String
	 * title =
	 * BAUtils.readString("Lütfen eklemek istediğiniz kitabın ismini girin");
	 * BookDetail bookDetail = new BookDetail(); bookDetail.setTitle(title);
	 * bookDetailService.create(bookDetail);
	 * 
	 * String firstName = BAUtils.readString("Lütfen yazarın ismini giriniz");
	 * String lastName = BAUtils.readString("Lütfen yazarın soyadını giriniz");
	 * 
	 * Optional<Author> optionalAuthor = authorService.findByName3(firstName,
	 * lastName); if (optionalAuthor.isEmpty()) { author = new Author(firstName,
	 * lastName);
	 * 
	 * authorService.create(author);
	 * System.out.println(optionalAuthor.get().getFirstName()); } else { author =
	 * optionalAuthor.get(); System.out.println("yazar db de mevcut"); }
	 * book.setAuthor(author); book.setDetail(bookDetail); bookService.create(book);
	 * 
	 * // eğer yazar db varsa yeni kayıt atmicak yazarlar tablosun // yoksa hem
	 * yazar oluşturcak hemde kitap oluşturcak
	 */

	public void borrowBook(Student student) {
		//ödünç alınmamış kitapları gösterilim
		// id sini ve ismini gösterelim
		//kiralamk istediğinzi kitabın ıd si
		//kaç gün kiralicaksınız (20) 
		//kitaba ulaşmamız hangi kitabı ödünç almak istiyoruz id 
		//kaç günlüğüne kiralicaksınız
		//studenta kiraliıdı kitapları eklicez
		List<Book> books = bookService.listAll()
				.stream()
				.filter(bookDetail -> !bookDetail.getDetail().isBorrowed())
				.collect(Collectors.toList());
		
		books.forEach(book-> System.out.println(book.getId() + " " + book.getDetail().getTitle()));
		
		int bookdId = BAUtils.readInt("Kiralamk istediğiniz kitabın ID si");
		int duration = BAUtils.readInt("Kaç gün kiralamak istiyorsunuz");
		Book borrowBook = bookService.find(bookdId);
		LocalDate date = LocalDate.now();
		borrowBook.getDetail().setBookBorrowDate(date);
		borrowBook.getDetail().setBookReturnDate(date.plusDays(duration));
		borrowBook.getDetail().setBorrowed(true);
		borrowBook.getStudentList().add(student);
		student.getBooks().add(borrowBook);
		bookService.update(bookdId, borrowBook);
		
	}
}
