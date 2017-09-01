package com.eb.integration.appdirect.models;

public class Item {
	private int quantity;
	public Item() {
		super();
	}

	@Override
	public String toString() {
		return "Item [quantity=" + quantity + ", unit=" + unit + "]";
	}

	private String unit;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
