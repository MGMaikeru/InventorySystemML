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
		return mercadoLibre
				.addProduct(new Product(productName, description, price, quantity, category, timesPurchased));
	}

	/**
	 * Adds an order with the given buyer name, order ID, and product list to the MercadoLibre system.
	 *
	 * @param buyerName   The name of the buyer.
	 * @param id          The ID of the order.
	 * @param productList The list of products in the order.
	 * @return A message indicating the success or failure of the operation.
	 * @throws RuntimeException If the buyer name, order ID, or product list is empty.
	 */
	public String addOrder(String buyerName, String id, ArrayList<Product> productList) {
		if (buyerName.isEmpty())
			throw new RuntimeException("Error. The name of buyer is empty.");
		if (id.isEmpty())
			throw new RuntimeException("Error. The order id is empty.");
		if (productList.isEmpty())
			throw new RuntimeException("Error. The product list is empty.");
		return mercadoLibre.addOrder(new Order(buyerName, id, productList));
	}

	/**
	 * Increases the quantity of a product with the given name by the specified amount.
	 *
	 * @param productName The name of the product to increase the quantity.
	 * @param newQuantity The amount to be added to the product's quantity.
	 * @return A message indicating the success or failure of the operation.
	 * @throws RuntimeException If the new quantity is less than or equal to zero.
	 */
	public String IncreaseQuantity(String productName, int newQuantity) throws RuntimeException {
		if (newQuantity <= 0) {
			throw new RuntimeException("The amount to be added must be greater than zero.");
		}
		return mercadoLibre.increaseQuantity(productName, newQuantity);
	}

	/**
	 * Searches for a product with the given value in the name field and returns its details.
	 *
	 * @param value The value to search for.
	 * @return A string representation of the product's details.
	 */
	public String searchProduct(String value) {
		Product product = mercadoLibre.searchProduct("name", value);
		return printProduct(product);
	}

	/**
	 * @param searchVariable
	 * @param value
	 */
	public String searchProduct(int searchVariable, double value) throws IllegalStateException {
		Product product = switch (searchVariable) {
			case 1 -> mercadoLibre.searchProduct("price", value);
			case 2 -> mercadoLibre.searchProduct("timesPurchased", value);
			default -> throw new IllegalStateException("Unexpected value: " + searchVariable);
		};
		return printProduct(product);
	}

	/**
	 * @param value
	 * @return
	 */
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
	 * Searches for products within a specified range of a given search variable and
	 * returns the string representation of the matches.
	 *
	 * @param searchVariable The search variable code (1 for price, 2 for
	 *                       timesPurchased).
	 * @param minimum        The minimum value of the range.
	 * @param maximum        The maximum value of the range.
	 * @param senseSort      The sense of sort (1 for ascending, 2 for descending).
	 * @param sortVariable   The sorting variable code (1 for name, 2 for price, 3
	 *                       for category, 4 for timesPurchased).
	 * @return The string representation of the product matches within the specified
	 * range.
	 * @throws IllegalStateException If an unexpected search variable code is
	 *                               provided.
	 */
	public String searchInRange(int searchVariable, double minimum, double maximum, int senseSort, int sortVariable)
			throws IllegalStateException {

		ArrayList<Product> matches = switch (searchVariable) {
			case 1 -> mercadoLibre.searchInRange("price", minimum, maximum, senseSort, sortVariable(sortVariable));
			case 2 ->
					mercadoLibre.searchInRange("timesPurchased", minimum, maximum, senseSort, sortVariable(sortVariable));
			default -> throw new IllegalStateException("Unexpected value: " + searchVariable);
		};
		return printProductList(matches);
	}

	/**
	 * Searches for products within a specified interval and returns a String with
	 * the sorted result.
	 *
	 * @param startPrefix  The prefix indicating the start of the interval.
	 * @param finalPrefix  The prefix indicating the end of the interval.
	 * @param senseSort    The sense of sort (1 for ascending, 2 for descending).
	 * @param sortVariable The variable to be used for sorting (e.g., "name",
	 *                     "price").
	 * @return The String with the sorted list of products within the specified
	 * interval.
	 */
	public String searchInInterval(String startPrefix, String finalPrefix, int senseSort, int sortVariable) {
		ArrayList<Product> matches = mercadoLibre.searchInInterval("name", startPrefix, finalPrefix, senseSort,
				sortVariable(sortVariable));
		return printProductList(matches);
	}

	/**
	 * Returns the corresponding string representation of the given variable code.
	 *
	 * @param variable The variable code.
	 * @return The string representation of the variable.
	 * @throws IllegalStateException If an invalid variable code is provided.
	 */
	private String sortVariable(int variable) throws IllegalStateException {
		return switch (variable) {
			case 1 -> "name";
			case 2 -> "price";
			case 3 -> "category";
			case 4 -> "timesPurchased";
			default -> throw new IllegalStateException("Error. Invalid sort variable.");
		};
	}

	/**
	 * Generates a string representation of the given list of products.
	 *
	 * @param list The list of products.
	 * @return The string representation of the product list.
	 */
	private String printProductList(ArrayList<Product> list) {
		StringBuilder msg = new StringBuilder();
		for (Product product : list) {
			msg.append(product).append("\n");
		}
		return (msg.length() == 0) ? "No matches were found for the criteria provided." : msg.toString();
	}

}