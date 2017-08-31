package com.eb.store.models;

import java.util.Collection;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;

import com.eb.store.repositories.SubscriptionRepository;

public class Subscription {
	
	@Autowired
	SubscriptionRepository repository;
	
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

	User owner;
	Collection<User> users;
	int quantity;
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Subscription [id=" + id + ", owner=" + owner + ", users=" + users + ", quantity=" + quantity + "]";
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public Collection<User> getUsers() {
		return users;
	}
	public void setUsers(Collection<User> users) {
		this.users = users;
	}
	
	public void save() {
		repository.save(this);
	}
	

}
