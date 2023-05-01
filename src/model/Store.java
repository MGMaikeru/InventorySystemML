package model;

import java.util.ArrayList;
import java.util.Comparator;

public class Store {
	private final ArrayList<Product> products;

	private final ArrayList<Order> orders;

	private final Searcher<Product, String> searcherByString;
	private final Searcher<Product, Double> searcherByDouble;
	private final Searcher<Product, Integer> searcherByInteger;
	private final Searcher<Product, Category> searcherByCategory;

	public Store() {
		products = new ArrayList<Product>();
		orders = new ArrayList<>();
		searcherByString = new Searcher<>();
		searcherByDouble = new Searcher<>();
		searcherByInteger = new Searcher<>();
		searcherByCategory = new Searcher<>();
	}

	/**
	 * @param product
	 */
	public String addProduct(Product product) {
		products.add(product);
		return "Product added!";
	}

	/**
	 * @param order
	 */
	public String addOrder(Order order) {
		orders.add(order);
		return "Order added!";
	}

	/**
	 * @param productName
	 * @param newQuantity
	 */
	public String increaseQuantity(String productName, int newQuantity) {
		Product product = searchProduct("name", productName);
		String msg = "Product not fount";
		if (product != null) {
			int before = product.getQuantityAvailable();
			product.setQuantityAvailable(newQuantity);
			msg = "Quantity of the product " + product.getName() + " modified from " + before + " to " + product.getQuantityAvailable();
		}
		return msg;
	}

	/**
	 * @param searchVariable
	 * @param value
	 * @return
	 */
	public Product searchProduct(String searchVariable, String value) {
		sortProductsByName();
		return searcherByString.binarySearch(products, searchVariable, value);
	}

	/**
	 * @param searchVariable
	 * @param value
	 * @return
	 */
	public Product searchProduct(String searchVariable, double value) {
		Product product = null;
		switch (searchVariable) {
			case "price" -> {
				sortProductsByPrice();
				product = searcherByDouble.binarySearch(products, searchVariable, value);
			}
			case "timesPurchased" -> {
				sortProductsByTimesPurchased();
				product = searcherByInteger.binarySearch(products, searchVariable, (int) value);
			}
		}
		return product;
	}

	/**
	 * @param searchVariable
	 * @param value
	 * @return
	 */
	public Product searchProduct(String searchVariable, Category value) {
		sortProductsByCategory();
		return searcherByCategory.binarySearch(products, searchVariable, value);
	}

	/**
	 * @param variable
	 * @param min
	 * @param max
	 */
	public ArrayList<Product> searchInRange(String variable, int min, int max) {
		sortProductsByTimesPurchased();
		return searcherByInteger.filterRange(products, variable, min, max);
	}

	/**
	 * @param variable
	 * @param prefix
	 * @param finalPrefix
	 * @return
	 */
	public ArrayList<Product> searchInInterval(String variable, String prefix, String finalPrefix) {
		sortProductsByName();
		return searcherByString.filterRange(products, variable, prefix, finalPrefix);
	}

	/**
	 *
	 */
	private void sortProductsByName() {
		products.sort(Comparator.comparing(Product::getName));
	}

	/**
	 *
	 */
	private void sortProductsByPrice() {
		products.sort(Comparator.comparing(Product::getPrice));
	}

	/**
	 *
	 */
	private void sortProductsByTimesPurchased() {
		products.sort(Comparator.comparing(Product::getTimesPurchased));
	}

	/**
	 *
	 */
	private void sortProductsByCategory() {
		products.sort(Comparator.comparing(Product::getCategory));
	}

	/**
	 * @param sense
	 * @param variable
	 */
	public ArrayList<Product> sort(int sense, int variable) {
		// TODO - implement Store.sort
		throw new UnsupportedOperationException();
	}

}