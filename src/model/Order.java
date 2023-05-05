package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Order {

	private String buyerName;
	private String date;
	private double totalPrice;
	private ArrayList<Product> productList;

	public Order(String buyerName, ArrayList<Product> productList) {
		this.productList = new ArrayList<>();
		this.buyerName = buyerName;
		this.productList.addAll(productList);
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		this.date = dateFormat.format(calendar.getTime());
		calculateTotalPrice();
	}

	public void calculateTotalPrice() {
		for (Product product : productList) {
			totalPrice += product.getPrice() * product.getQuantityAvailable();
		}
	}

}