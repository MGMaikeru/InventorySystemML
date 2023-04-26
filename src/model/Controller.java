package model;

import java.util.ArrayList;

public class Controller {

	private Store mercadoLibre;

	public Controller() {
		mercadoLibre = new Store();
	}

	/**
	 * 
	 * @param productName
	 * @param description
	 * @param price
	 * @param quantity
	 * @param category
	 */
	public String addProduct(String productName, String description, double price, int quantity, int cate,
			int timesPurchased) throws RuntimeException {
		if (productName.isEmpty())
			throw new RuntimeException("Error. The name of the product is empty.");
		if (quantity < 0)
			throw new RuntimeException("Error. The quantity of product in inventory cannot be negative.");
		Category category = null;
		switch (cate) {
			case 1:
				category = Category.BOOKS;
				break;
			case 2:
				category = Category.ELECTRONIC;
				break;
			case 3:
				category = Category.APPAREL_AND_ACCESSORIES;
				break;
			case 4:
				category = Category.FOODS_AND_BEVERAGES;
				break;
			case 5:
				category = Category.STATIONARY;
				break;
			case 6:
				category = Category.SPORTS;
				break;
			case 7:
				category = Category.BEAUTY;
				break;
			case 8:
				category = Category.TOYS;
				break;
			default:
				throw new RuntimeException("Error. Invalid category.");
		}
		return mercadoLibre
				.addProduct(new Product(productName, description, price, quantity, category, timesPurchased));
	}

	/**
	 * 
	 * @param buyerName
	 * @param productList
	 */
	public void addOrder(String buyerName, ArrayList<String> productList) {
		// TODO - implement Controller.addOrder
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param productName
	 * @param newQuantity
	 */
	public String IncreaseQuantity(String productName, int newQuantity) {
		// TODO - implement Controller.IncreaseQuantity
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param searchVariable
	 * @param value
	 */
	public String searchProduct(int searchVariable, int value) {
		// TODO - implement Controller.searchProduct
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param searchVariable
	 * @param value
	 */
	public String searchProduct(int searchVariable, String value) {
		// TODO - implement Controller.searchProduct
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param searchVariable
	 * @param value
	 */
	public String searchOrder(int searchVariable, int value) {
		// TODO - implement Controller.searchOrder
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param searchVariable
	 * @param value
	 */
	public String searchOrder(int searchVariable, String value) {
		// TODO - implement Controller.searchOrder
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param variable
	 * @param minimum
	 * @param maximum
	 */
	public ArrayList<Product> searchInRange(int variable, int minimum, int maximum) {
		// TODO - implement Controller.searchInRange
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param variable
	 * @param prefix
	 * @param suffix
	 */
	public ArrayList<Product> searchInInterval(int variable, String prefix, String suffix) {
		// TODO - implement Controller.searchInInterval
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param senseSort
	 * @param sortVariable
	 */
	public String sorting(int senseSort, int sortVariable) {
		// TODO - implement Controller.sorting
		throw new UnsupportedOperationException();
	}

}