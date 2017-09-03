package com.eb.integration.appdirect.models;

public class NotificationSuccessResponse extends NotificationResponse {
	
	private String accountIdentifier;
	
	public NotificationSuccessResponse(String accountIdentifier) {
		super(true);
		this.accountIdentifier = accountIdentifier;
	}
	
	public NotificationSuccessResponse() {
		super(true);
	}
	
	public String getAccountIdentifier() {
		return accountIdentifier;
	}
	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}
	@Override
	public String toString() {
		return "NotificationSuccessResponse [accountIdentifier=" + accountIdentifier + "]";
	}
	
	
}
