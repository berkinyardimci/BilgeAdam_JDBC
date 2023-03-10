package com.bilgeadam.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	private Author author;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "book_detail_id", referencedColumnName = "id")
	private BookDetail detail;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "book_student", joinColumns = @JoinColumn(name = "book_id"),
	inverseJoinColumns = @JoinColumn(name = "student_id"))
	private List<Student> studentList;

	public Book(Author author, BookDetail detail, List<Student> studentList) {
		super();
		this.author = author;
		this.detail = detail;
		this.studentList = studentList;
	}

	public Book() {

	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", author=" + author + ", detail=" + detail + "]";
	}
	

}
