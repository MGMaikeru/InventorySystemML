package model;

import java.util.ArrayList;
import java.util.Calendar;

public class Order {

	private String buyerName;
	private String id;
	private Calendar date;
	private int totalPrice;
	private ArrayList<Product> productList;

	public Order(String buyerName, String id, ArrayList<Product> productList) {
		this.buyerName = buyerName;
		this.id = id;
		this.productList.addAll(productList);
		calculateTotalPrice();
	}

	public void calculateTotalPrice(){
		for (Product o: productList) {
			totalPrice += o.getPrice() * o.getQuantityAvailable();
		}
	}

	public String getId() {
		return id;
	}
}