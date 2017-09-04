package com.eb.integration.appdirect.managers;

public class EbStoreException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public EbStoreException() {
		super();
	}

	public EbStoreException(String message) {
		super(message);
	}

	public EbStoreException(Throwable cause) {
		super(cause);
	}

	public EbStoreException(String message, Throwable cause) {
		super(message, cause);
	}

	public EbStoreException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}


}