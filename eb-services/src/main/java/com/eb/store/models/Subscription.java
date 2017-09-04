package com.eb.store.models;

import java.util.Collection;
import java.util.LinkedList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	
	@Column
	String identifier;
	@Column()
	boolean active;
	@Embedded
	IdentityProviderMetadata identityProviderMetadata;
	
	@OneToMany(cascade = CascadeType.ALL,
			orphanRemoval = true)
	@JoinColumn(name = "subscription_id")
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
	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}
	
	public void addUser(User user)
	{
		if (getUsers()==null)
			setUsers(new LinkedList<User>());
		getUsers().add(user);
	}
	@Override
	public String toString() {
		return "Subscription [id=" + id + ", marketPlaceId=" + marketPlaceId + ", status=" + status + ", identifier="
				+ identifier + ", active=" + active + ", identityProviderMetadata=" + identityProviderMetadata
				+ ", users=" + users + ", quantity=" + quantity + ", address=" + address + "]";
	}
}
