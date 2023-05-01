package model;

import java.util.ArrayList;

public class Controller {

	private final Store mercadoLibre;

	public Controller() {
		mercadoLibre = new Store();
	}

	/**
	 * Adds a new product to the MercadoLibre system.
	 *
	 * @param productName    The name of the product.
	 * @param description    The description of the product.
	 * @param price          The price of the product.
	 * @param quantity       The quantity of the product available in inventory.
	 * @param cate           The code of the product category.
	 * @param timesPurchased The number of times the product has been purchased.
	 * @return A string representing the result of adding the product.
	 * @throws RuntimeException If there is an error adding the product.
	 */
	public String addProduct(String productName, String description, double price, int quantity, int cate,
							 int timesPurchased) throws RuntimeException {
		if (productName.isEmpty())
			throw new RuntimeException("Error. The name of the product is empty.");
		if (quantity < 0)
			throw new RuntimeException("Error. The quantity of product in inventory cannot be negative.");
		Category category = switch (cate) {
			case 1 -> Category.BOOKS;
			case 2 -> Category.ELECTRONIC;
			case 3 -> Category.APPAREL_AND_ACCESSORIES;
			case 4 -> Category.FOODS_AND_BEVERAGES;
			case 5 -> Category.STATIONARY;
			case 6 -> Category.SPORTS;
			case 7 -> Category.BEAUTY;
			case 8 -> Category.TOYS;
			default -> throw new RuntimeException("Error. Invalid category.");
		};
		return mercadoLibre.addProduct(new Product(productName, description, price, quantity, category, timesPurchased));
	}

	/**
	 * @param buyerName
	 * @param productList
	 */
	public void addOrder(String buyerName, ArrayList<String> productList) {
		// TODO - implement Controller.addOrder
		throw new UnsupportedOperationException();
	}

	/**
	 * @param productName
	 * @param newQuantity
	 */
	public String IncreaseQuantity(String productName, int newQuantity) {
		// TODO - implement Controller.IncreaseQuantity
		throw new UnsupportedOperationException();
	}

	/**
	 * @param value
	 */
	public String searchProduct(String value) {
		Product product = mercadoLibre.searchProduct("name", value);
		return printProduct(product);
	}

	/**
	 * @param searchVariable
	 * @param value
	 */
	public String searchProduct(int searchVariable, double value) {
		Product product = switch (searchVariable) {
			case 1 -> mercadoLibre.searchProduct("price", value);
			case 2 -> mercadoLibre.searchProduct("timesPurchased", value);
			default -> throw new IllegalStateException("Unexpected value: " + searchVariable);
		};
		return printProduct(product);
	}

	public String searchProduct(Category value) {
		Product product = mercadoLibre.searchProduct("category", value);
		return printProduct(product);
	}

	private String printProduct(Product product) {
		return (product != null) ? product.toString() : "Not found.";
	}

	/**
	 * @param searchVariable
	 * @param value
	 */
	public String searchOrder(int searchVariable, int value) {
		// TODO - implement Controller.searchOrder
		throw new UnsupportedOperationException();
	}

	/**
	 * @param searchVariable
	 * @param value
	 */
	public String searchOrder(int searchVariable, String value) {
		// TODO - implement Controller.searchOrder
		throw new UnsupportedOperationException();
	}

	/**
	 * @param minimum
	 * @param maximum
	 */
	public ArrayList<Product> searchInRange(int minimum, int maximum) {
		return mercadoLibre.searchInRange("timesPurchased", minimum, maximum);
	}

	/**
	 *
	 * @param variable
	 * @param prefix
	 * @param finalPrefix
	 * @return
	 */
	public ArrayList<Product> searchInInterval(int variable, String prefix, String finalPrefix) {
		return mercadoLibre.searchInInterval("name", prefix, finalPrefix);
	}

	/**
	 * @param senseSort
	 * @param sortVariable
	 */
	public String sorting(int senseSort, int sortVariable) {
		// TODO - implement Controller.sorting
		throw new UnsupportedOperationException();
	}

}