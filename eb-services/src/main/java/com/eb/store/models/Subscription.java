package com.eb.store.models;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "subscription")
public class Subscription {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@Column(name="mkt_id")
	String marketPlaceId;
	
	@Column
	SubscriptionStatus status;
	
	@Column(name="identifier")
	String identifier;
	@Column()
	boolean active;
	@Embedded
	IdentityProviderMetadata identityProviderMetadata;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	User owner;

	@OneToMany(cascade = CascadeType.ALL,
			orphanRemoval = true)
	@JoinColumn(name = "subscription_id")
	@JsonIgnore
	Collection<User> users;

	@Column
	int quantity;
	@Embedded
	Address address;
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	
	 public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public SubscriptionStatus getStatus() {
		return status;
	}

	public void setStatus(SubscriptionStatus status) {
		this.status = status;
	}

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

	public String getMarketPlaceId() {
		return marketPlaceId;
	}

	public void setMarketPlaceId(String vendorId) {
		this.marketPlaceId = vendorId;
	}


	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	@Override
	public String toString() {
		return "Subscription [id=" + id + ", marketPlaceId=" + marketPlaceId + ", status=" + status + ", identifier="
				+ identifier + ", active=" + active + ", identityProviderMetadata=" + identityProviderMetadata
				+ ", owner=" + owner + ", users=" + users + ", quantity=" + quantity + ", address=" + address + "]";
	}
}
