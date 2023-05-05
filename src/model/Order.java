package model;

import java.util.ArrayList;
import java.util.Calendar;

public class Order {

	private final String buyerName;
	private double totalPrice;
	private final Calendar date;
	private ArrayList<Product> productList;

	public Order(String buyerName, ArrayList<Product> productList, Calendar date) {
		this.buyerName = buyerName;
		this.productList = new ArrayList<>();
		this.productList.addAll(productList);
		this.date = date;
		calculateTotalPrice();
	}

	public void calculateTotalPrice() {
		for (Product product : productList)
			totalPrice += product.getPrice() * product.getQuantityAvailable();
	}

	public String getBuyerName() {
		return buyerName;
	}

	public Calendar getDate() {
		return date;
	}

	public double getTotalPrice() {
		return totalPrice;
	}
}