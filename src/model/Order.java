package model;

import java.util.ArrayList;
import java.util.Calendar;

public class Order {

	private String buyerName;
	private Calendar date;
	private double totalPrice;
	private ArrayList<Product> productList;

	public Order(String buyerName, ArrayList<Product> productList) {
		this.productList = new ArrayList<>();
		this.buyerName = buyerName;
		this.productList.addAll(productList);
		calculateTotalPrice();
	}

	public void calculateTotalPrice() {
		for (Product product : productList) {
			totalPrice += product.getPrice() * product.getQuantityAvailable();
		}
	}

}