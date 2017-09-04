package com.eb.idp.openid.dao;

import org.springframework.security.core.AuthenticationException;

public class UserInactiveException extends AuthenticationException  {

	public UserInactiveException(String msg) {
		super(msg);
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
