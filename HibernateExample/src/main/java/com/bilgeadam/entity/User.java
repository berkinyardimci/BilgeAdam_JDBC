package com.bilgeadam.entity;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.swing.JSpinner.DateEditor;

import com.bilgeadam.entity.enums.EAdressType;
import com.bilgeadam.entity.enums.EGender;

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

	@Enumerated(EnumType.STRING)
	private EGender gender;
	
	@Embedded
	private Name name;
	
	@ElementCollection
	private Map<EAdressType, Adress> address;
	
	@ElementCollection
	private List<String> areasOfInterest;
	
	public User(String username, String password, EGender gender) {
		super();
		this.username = username;
		this.password = password;
		this.gender = gender;
	}
	
	public User(String username, String password, EGender gender, Name name) {
		super();
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.name = name;
	}
	
	


	public User() {
	}
	
	

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Map<EAdressType, Adress> getAddress() {
		return address;
	}

	public void setAddress(Map<EAdressType, Adress> address) {
		this.address = address;
	}

	public List<String> getAreasOfInterest() {
		return areasOfInterest;
	}

	public void setAreasOfInterest(List<String> areasOfInterest) {
		this.areasOfInterest = areasOfInterest;
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

	public EGender getGender() {
		return gender;
	}

	public void setGender(EGender gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", gender=" + gender + "]";
	}

}
