package com.eb.integration.appdirect.models;

import java.util.List;


public class Order {
	private String editionCode;
	public Order() {
		super();
	}

	@Override
	public String toString() {
		return "Order [editionCode=" + editionCode + ", pricingDuration=" + pricingDuration + ", items=" + items
				+ "]";
	}

	private String pricingDuration;
	private List<Item> items;

	public String getEditionCode() {
		return editionCode;
	}

	public void setEditionCode(String editionCode) {
		this.editionCode = editionCode;
	}

	public String getPricingDuration() {
		return pricingDuration;
	}

	public void setPricingDuration(String pricingDuration) {
		this.pricingDuration = pricingDuration;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}