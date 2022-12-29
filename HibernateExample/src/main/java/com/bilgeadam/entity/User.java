package com.bilgeadam.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*
 * Code First
 * username--> unique olsun ve null geçilmesin
 * password 32 charakter olsun
 * gender string
 *  tablomuzun ismide --tbluser
 *  
 *  Crud işlemleri için bir interface açalım, birde userRepository si oluşturalım
 *  save, update, delete, findById, findAll , Generic olsun
 *  
 *  hibernate Configuration ı yapalım
 *  
 *  save
 *  findbyId methodu yazalım find methodu parametre olarak ne alıyo
 * 
 *  delete 
 * 
 *  update 
 *  
 * Post sınıfı oluşturalım
 * content
 * createdDay --> oluşturulma tarihi
 * Post repository oluşturup posta göre düzenleyelim
 * postController oluşturup bitane post oluşturalım
 * post oluşturunca veritabanında oluşturdugumuz saniyeye kadar görcez.
 * 
 * Name sınıfı oluşturalım bu veritabanı tablosu olmicak @Embedable
 * firstname
 * lastName,
 * middleName
 * 
 * EGender enum MAN, WOMAN
 * 
 * Address country, streetName, city embeddable
 * EAdressType HOME, BUSINESS MI
 * 
 * Map yapısı içinde tutucaz EAdressType, Address
 * 
 * 
 */

@Entity
@Table(name = "tbluser")
public class User {

	// generated value strategies neler var fakları neler
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(length = 32)
	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	private UserDetail userDetail;
	
	@OneToMany( mappedBy = "user",cascade = CascadeType.ALL)
	private List<Post> posts;

	public User(String username, String password, UserDetail userDetail) {
		super();
		this.username = username;
		this.password = password;
		this.userDetail = userDetail;
	}

	public User() {

	}

	public User(long id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

}
