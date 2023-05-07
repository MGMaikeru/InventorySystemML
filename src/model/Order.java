package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Order {

	private final String buyerName, date;
	private double totalPrice;
	private final ArrayList<Product> productList;

	public Order(String buyerName, ArrayList<Product> productList, Calendar date) {
		this.buyerName = buyerName;
		this.productList = new ArrayList<>();
		this.productList.addAll(productList);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.date = dateFormat.format(date.getTime());
		calculateTotalPrice();
	}

	public void calculateTotalPrice() {
		for (Product product : productList)
			totalPrice += product.getPrice() * product.getQuantityAvailable();
	}

	public String getBuyerName() {
		return buyerName;
	}

	public String getDate() {
		return date;
	}

	public double getTotalPrice() {
		return totalPrice;
	}
}