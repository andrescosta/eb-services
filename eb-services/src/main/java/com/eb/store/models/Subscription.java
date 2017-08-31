package com.eb.store.models;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subscription")
public class Subscription {

	@Id
	Long id;

	@Column
	String vendorId;
	
	 @Embedded
	 IdentityProviderMetadata identityProviderMetadata;


	public IdentityProviderMetadata getIdentityProviderMetadata() {
		return identityProviderMetadata;
	}

	public void setIdentityProviderMetadata(IdentityProviderMetadata identityProviderMetadata) {
		this.identityProviderMetadata = identityProviderMetadata;
	}

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

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	User owner;

	@OneToMany
	@JoinColumn(name = "subscription_id")
	Collection<User> users;

	@Column
	int quantity;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Subscription [id=" + id + ", vendorId=" + vendorId + ", identityProviderMetadata="
				+ identityProviderMetadata + ", owner=" + owner + ", users=" + users + ", quantity=" + quantity + "]";
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

}
