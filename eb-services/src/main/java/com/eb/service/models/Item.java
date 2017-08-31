package com.eb.service.models;

public class Item {
	private String quantity;
	public Item() {
		super();
	}

	@Override
	public String toString() {
		return "Item [quantity=" + quantity + ", unit=" + unit + "]";
	}

	private String unit;

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
