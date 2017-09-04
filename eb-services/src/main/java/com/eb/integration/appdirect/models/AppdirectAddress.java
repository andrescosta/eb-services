package com.eb.integration.appdirect.models;

import com.eb.store.models.Address;

public class AppdirectAddress {
	
	String city;
	String country;
	String state;
	String street1;
	String street2;
	String zip;

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStreet1() {
		return street1;
	}
	public void setStreet1(String street1) {
		this.street1 = street1;
	}
	public String getStreet2() {
		return street2;
	}
	public void setStreet2(String street2) {
		this.street2 = street2;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public Address AsAddress()
	{
		Address address = new Address();
		address.setCity(getCity());
		address.setCountry(getCountry());
		address.setState(getState());
		address.setStreet1(getStreet1());
		address.setStreet2(getStreet2());
		address.setZip(getZip());
		return address;
	}
	
	@Override
	public String toString() {
		return "Address [city=" + city + ", country=" + country + ", state=" + state + ", street1=" + street1
				+ ", street2=" + street2 + ", zip=" + zip + "]";
	}
}