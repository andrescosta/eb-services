package com.eb.service.models;

class Company {
	public Company() {
		super();
	}

	private String country;
	@Override
	public String toString() {
		return "Company [country=" + country + ", name=" + name + ", phoneNumber=" + phoneNumber + ", uuid="
				+ uuid + ", website=" + website + "]";
	}

	private String name;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	private String phoneNumber;
	private String uuid;
	private String website;
}