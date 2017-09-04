package com.eb.contracts;

public class EBResponse {
	EBUser ebUser;
	EBError error;
	public EBUser getEbUser() {
		return ebUser;
	}
	public void setEbUser(EBUser ebUser) {
		this.ebUser = ebUser;
	}
	public EBError getError() {
		return error;
	}
	public void setError(EBError error) {
		this.error = error;
	}
	public EBResponse(EBUser ebUser) {
		super();
		this.ebUser = ebUser;
	}
	public EBResponse(EBError error) {
		super();
		this.error = error;
	}

}
