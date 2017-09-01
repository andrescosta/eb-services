package com.eb.integration.appdirect.models;

public class Notice {
	NoticeType type;

	public NoticeType getType() {
		return type;
	}

	public void setType(NoticeType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Notice [type=" + type + "]";
	}
	
}
