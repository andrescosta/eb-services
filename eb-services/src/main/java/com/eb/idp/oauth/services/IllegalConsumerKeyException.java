package com.eb.idp.oauth.services;

import org.springframework.security.oauth.common.OAuthException;

public class IllegalConsumerKeyException extends OAuthException {

	
	public IllegalConsumerKeyException() {
		super("Illegal provided consumer key");
	
	}

	private static final long serialVersionUID = 1L;

}
