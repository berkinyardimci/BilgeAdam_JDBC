package com.bilgeadam.entity;

//hibernate
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
//lombok
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class BookDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "title")
	private String title;

	@Column(name = "is_borrowed")
	private boolean isBorrowed;

	@Column(name = "book_borrow_date")
	private LocalDate bookBorrowDate;

	@Column(name = "return_date")
	private LocalDate bookReturnDate;

	@OneToOne(mappedBy = "detail")
	private Book book;

	public BookDetail(long id, String title, boolean isBorrowed, LocalDate bookBorrowDate, LocalDate bookReturnDate,
			Book book) {
		super();
		this.id = id;
		this.title = title;
		this.isBorrowed = isBorrowed;
		this.bookBorrowDate = bookBorrowDate;
		this.bookReturnDate = bookReturnDate;
		this.book = book;
	}

	public BookDetail() {

	}
}
