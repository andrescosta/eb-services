package com.eb.integration.appdirect.models;

public class Address {
	@Override
	public String toString() {
		return "Address [city=" + city + ", country=" + country + ", state=" + state + ", street1=" + street1 + ", zip="
				+ zip + "]";
	}
	String city;
	String country;
	String state;
	String street1;
	String zip;
}