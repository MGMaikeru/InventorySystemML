package model;

public class Product implements Cloneable {

	private final String name, description;
	private final double price;
	private int quantityAvailable;
	private final Category category;
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

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	public double getPrice() {
		return this.price;
	}

	public int getQuantityAvailable() {
		return this.quantityAvailable;
	}

	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public Category getCategory() {
		return this.category;
	}

	public int getTimesPurchased() {
		return this.timesPurchased;
	}

	public void setTimesPurchased(int timesPurchased) {
		this.timesPurchased += timesPurchased;
	}

	public Product clone() throws CloneNotSupportedException {
		return (Product) super.clone();
	}

	@Override
	public String toString() {
		return "{" +
				" name='" + getName() + "'" +
				", description='" + getDescription() + "'" +
				", price='" + getPrice() + "'" +
				", quantityAvailable='" + getQuantityAvailable() + "'" +
				", category='" + getCategory() + "'" +
				", timesPurchased='" + getTimesPurchased() + "'" +
				"}";
	}
}