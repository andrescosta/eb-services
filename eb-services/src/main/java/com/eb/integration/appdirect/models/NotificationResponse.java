package com.eb.integration.appdirect.models;

public class NotificationResponse {
	
	private boolean success = true;

	public NotificationResponse(boolean success) {
		super();
		this.success = success;
	}

	
	public boolean isSuccess() {
		return success;
	}

	protected void setSuccess(boolean success) {
		this.success = success;
	}

	
	
}
