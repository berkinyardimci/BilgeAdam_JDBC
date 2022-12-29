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
import javax.persistence.OneToOne;

import com.bilgeadam.entity.enums.EAdressType;
import com.bilgeadam.entity.enums.EGender;

/*
 * Userda sadece username ve password kalsın geriye kalan kısımları UserDetail Sınıfına taşıyalım
 * 
 */
@Entity
public class UserDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	@Enumerated(EnumType.STRING)
	private EGender gender;

	@Embedded
	private Name name;

	@ElementCollection
	private Map<EAdressType, Adress> address;

	@ElementCollection
	private List<String> areasOfInterest;

	@Column(nullable = true, name = "post_number")
	private int postNumber;

	@OneToOne(mappedBy = "userDetail")
	private User user;

	public UserDetail(EGender gender, Name name, Map<EAdressType, Adress> address,
			List<String> areasOfInterest, int postNumber) {
		super();
		this.gender = gender;
		this.name = name;
		this.address = address;
		this.areasOfInterest = areasOfInterest;
		this.postNumber = postNumber;
	}

	public UserDetail() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EGender getGender() {
		return gender;
	}

	public void setGender(EGender gender) {
		this.gender = gender;
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

	public int getPostNumber() {
		return postNumber;
	}

	public void setPostNumber(int postNumber) {
		this.postNumber = postNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
