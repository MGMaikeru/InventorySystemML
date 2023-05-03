package model;

import java.util.ArrayList;
import java.util.Calendar;

public class Order {

	private String buyerName, id;
	private Calendar date;
	private double totalPrice;
	private ArrayList<Product> productList;

	public Order(String buyerName, String id, ArrayList<Product> productList) {
		this.id = id;
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

	public String getId() {
		return id;
	}

}