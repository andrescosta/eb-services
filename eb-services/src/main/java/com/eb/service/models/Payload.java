package com.eb.service.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
class Payload {
	public Payload() {
		super();
	}

	private Company company;
	private Order order;
	private User user;
	private Notice notice;
	private Account account;
	
	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Order getOrder() {
		return order;
	}

	
	
	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Payload [company=" + company + ", order=" + order + ", user=" + user + ", notice=" + notice
				+ ", account=" + account + "]";
	}
}