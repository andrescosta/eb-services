package com.eb.store.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;

import com.eb.store.repositories.UserRepository;

@Entity
public class User {
	
	@Autowired
	UserRepository repository;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	String vendorId;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	String userName;
	String lastName;
	@Override
	public String toString() {
		return "User [id=" + id + ", vendorId=" + vendorId + ", userName=" + userName + ", lastName=" + lastName + "]";
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void save()
	{
		repository.save(this);
	}

}
