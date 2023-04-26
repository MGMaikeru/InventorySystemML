package model;

public class Product {

	private String name;
	private String description;
	private double price;
	private int quantityAvailable;
	private Category category;
	private int timesPurchased;

	public Product(String name, String description, double price, int quantityAvailable, Category category,
			int timesPurchased) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantityAvailable = quantityAvailable;
		this.category = category;
		this.timesPurchased = timesPurchased;
	}

}