package com.model;

public class OrderTable {
	private String name;
	private int quantity;
	private int price;
	private String girls_name;

	public OrderTable(String name, int quantity, int price) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getGirls_name() {
		return girls_name;
	}

	public void setGirls_name(String girls_name) {
		this.girls_name = girls_name;
	}

}
