package com.eb.integration.appdirect.models;

import com.eb.store.managers.EbStoreException;

public class NotificationErrorResponse extends NotificationResponse{
	private String errorCode;
	private String message;
	
	public static NotificationErrorResponse GENERIC_ERROR = new NotificationErrorResponse(ErrorCodes.UNKNOWN_ERROR.toString(), "Upps something happened. Try again latter.");

	public NotificationErrorResponse(ErrorCodes ec, EbStoreException e)
	{
		this(ec.toString(), e.getMessage());
	}
	
	public NotificationErrorResponse(String errorCode, String message) {
		super(false);
		this.errorCode = errorCode;
		this.message = message;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "NotificationErrorResponse [errorCode=" + errorCode + ", message=" + message + "]";
	}
	
	
}
