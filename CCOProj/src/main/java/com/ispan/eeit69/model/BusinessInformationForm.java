package com.ispan.eeit69.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BusinessUsers")
public class BusinessInformationForm {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String username;
    private String password;
    private String level;
    private String storeName;
    private String storeAddress;
    private String contactPerson;
    private String phoneNum;
    private String email;
    
    public BusinessInformationForm(){}

	public BusinessInformationForm(Long id, String username, String password, String level, String storeName,
			String storeAddress, String contactPerson, String phoneNum, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.level = level;
		this.storeName = storeName;
		this.storeAddress = storeAddress;
		this.contactPerson = contactPerson;
		this.phoneNum = phoneNum;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
