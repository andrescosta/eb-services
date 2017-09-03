package com.eb.contracts;

public class EBUser {
	public String id;
	public EBUserState state;
	public String firstName;
	public String lastName;
	public String openId;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public EBUserState getState() {
		return state;
	}
	public void setState(EBUserState state) {
		this.state = state;
	}
}
