package com.bilgeadam.controller;

import java.util.Optional;

import com.bilgeadam.entity.Student;
import com.bilgeadam.service.StudentService;
import com.bilgeadam.util.BAUtils;

public class StudentController {

	StudentService studentService;

	public StudentController() {
		this.studentService = new StudentService();
	}

	public void create() {
		String username = BAUtils.readString("Lütfen kullanıcı için belirlediğiniz kullanıcı adını giriniz: ");
		String password = BAUtils.readString("Lütfen kullanıcı için belirlediğiniz kullanıcı şifresini giriniz: ");

		Student student = new Student(username, password);

		studentService.create(student);
	}

	public void delete() {
		long id = BAUtils.readInt("Lütfen silme istediğiniz öğrencinin ID sini giriniz: ");
		studentService.delete(id);
	}
	
	public Optional<Student> findByUserName(String username) {
		return studentService.findByUserName(username);
	}

}
