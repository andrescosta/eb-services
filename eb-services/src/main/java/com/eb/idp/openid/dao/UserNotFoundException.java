package com.eb.idp.openid.dao;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final String openId;

	public UserNotFoundException(String openId) {
		super();
		this.openId = openId;
	}

	public String getOpenId() {
		return openId;
	}

	@Override
	public String toString() {
		return "UserNotFoundException [openId=" + openId + "]";
	}

}
