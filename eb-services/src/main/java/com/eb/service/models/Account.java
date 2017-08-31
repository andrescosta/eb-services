package com.eb.service.models;

public class Account {
	String accountIdentifier;
	String status;
	public String getAccountIdentifier() {
		return accountIdentifier;
	}
	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Account [accountIdentifier=" + accountIdentifier + ", status=" + status + "]";
	}
}
