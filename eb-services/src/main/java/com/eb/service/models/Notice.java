package com.eb.service.models;

public class Notice {
	String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Notice [type=" + type + "]";
	}
	
}
