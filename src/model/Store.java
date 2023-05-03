package model;

import java.util.ArrayList;
import java.util.Collections;
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
		String msg = "Product not found";
		if (product != null) {
			int before = product.getQuantityAvailable();
			product.setQuantityAvailable(before + newQuantity);
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
		sortProductsByName(products);
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
				sortProductsByPrice(products);
				product = searcherByDouble.binarySearch(products, searchVariable, value);
			}
			case "timesPurchased" -> {
				sortProductsByTimesPurchased(products);
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
		sortProductsByCategory(products);
		return searcherByCategory.binarySearch(products, searchVariable, value);
	}

	/**
	 * Searches for products within a specified range of a given search variable and returns the list of matches.
	 *
	 * @param searchVariable The search variable to filter on (e.g., "price", "timesPurchased").
	 * @param min            The minimum value of the range.
	 * @param max            The maximum value of the range.
	 * @param senseSort      The sense of sort (1 for ascending, 2 for descending).
	 * @param sortVariable   The variable to be used for sorting (e.g., "name", "price", "category", "timesPurchased").
	 * @return The list of product matches within the specified range.
	 */
	public ArrayList<Product> searchInRange(String searchVariable, double min, double max, int senseSort, String sortVariable) {
		sortProductsByTimesPurchased(products);
		ArrayList<Product> matches = switch (searchVariable) {
			case "price" -> searcherByDouble.filterRange(products, searchVariable, min, max);
			case "timesPurchased" -> searcherByInteger.filterRange(products, searchVariable, (int) min, (int) max);
			default -> throw new IllegalStateException("Invalid search variable: " + searchVariable);
		};
		return sortProducts(matches, senseSort, sortVariable);
	}

	/**
	 * Searches for products within a specified interval and returns the sorted result.
	 *
	 * @param variable     The variable to search for (e.g., "name", "price").
	 * @param startPrefix  The prefix indicating the start of the interval.
	 * @param finalPrefix  The prefix indicating the end of the interval.
	 * @param senseSort    The sense of sort (1 for ascending, 2 for descending).
	 * @param sortVariable The variable to be used for sorting (e.g., "name", "price").
	 * @return The sorted list of products within the specified interval.
	 */
	public ArrayList<Product> searchInInterval(String variable, String startPrefix, String finalPrefix, int senseSort, String sortVariable) {
		sortProductsByName(products);
		ArrayList<Product> matches = searcherByString.filterRange(products, variable, startPrefix, finalPrefix);
		return sortProducts(matches, senseSort, sortVariable);
	}

	/**
	 * Sorts the products list by name in ascending order.
	 *
	 * @param productList products list.
	 */
	private void sortProductsByName(ArrayList<Product> productList) {
		productList.sort(Comparator.comparing(Product::getName));
	}

	/**
	 * Sorts the products list by price in ascending order.
	 *
	 * @param productList products list.
	 */
	private void sortProductsByPrice(ArrayList<Product> productList) {
		productList.sort(Comparator.comparing(Product::getPrice));
	}

	/**
	 * Sorts the products list by the number of times purchased in ascending order.
	 *
	 * @param productList products list.
	 */
	private void sortProductsByTimesPurchased(ArrayList<Product> productList) {
		productList.sort(Comparator.comparing(Product::getTimesPurchased));
	}

	/**
	 * Sorts the products list by category in ascending order.
	 *
	 * @param productList products list.
	 */
	private void sortProductsByCategory(ArrayList<Product> productList) {
		productList.sort(Comparator.comparing(Product::getCategory));
	}

	/**
	 * Sorts the given list of products based on the specified sorting variable and sense of sort.
	 *
	 * @param matches      The list of products to be sorted.
	 * @param senseSort    The sense of sort (1 for ascending, 2 for descending).
	 * @param sortVariable The variable to be used for sorting (e.g., "name", "price").
	 * @return The sorted list of products.
	 * @throws RuntimeException If an invalid sense of ordering is provided.
	 */
	private ArrayList<Product> sortProducts(ArrayList<Product> matches, int senseSort, String sortVariable) throws RuntimeException {
		switch (sortVariable) {
			case "name" -> sortProductsByName(matches);
			case "price" -> sortProductsByPrice(matches);
		}
		if (senseSort == 2) {
			Collections.reverse(matches);
		} else if (senseSort != 1) {
			throw new RuntimeException("Error. Invalid sense of ordering");
		}
		return matches;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}
}