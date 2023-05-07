package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
	 * @param buyerName    The name of the buyer.
	 * @param productsList The list of products in the order.
	 * @return A message indicating the success or failure of the operation.
	 * @throws RuntimeException If the buyer name, order ID, or product list is empty.
	 */
	public String addOrder(String buyerName, ArrayList<String> productsList) {
		if (buyerName.isEmpty())
			throw new RuntimeException("Error. The name of buyer is empty.");
		if (productsList.isEmpty())
			throw new RuntimeException("Error. It is not possible to create an order with an empty product list.");

		ArrayList<Product> products = new ArrayList<>();

		for (int i = 0; i < productsList.size(); i += 2) {
			Product product = mercadoLibre.searchProduct("name", productsList.get(i));
			int quantity = Integer.parseInt(productsList.get(i + 1));
			product.setTimesPurchased(product.getTimesPurchased() + quantity);
			try {
				Product productClone = product.clone();
				productClone.setQuantityAvailable(quantity);
				products.add(productClone);
			} catch (CloneNotSupportedException e) {
				e.getMessage();
			}
		}
		return mercadoLibre.addOrder(new Order(buyerName, products, Calendar.getInstance()));
	}

	public boolean checkProduct(String productName, int quantity) {
		Product product = mercadoLibre.searchProduct("name", productName);
		return product != null && product.getQuantityAvailable() - quantity >= 0;
	}

	/**
	 * Increases the quantity of a product with the given name by the specified amount.
	 *
	 * @param productName The name of the product to increase the quantity.
	 * @param newQuantity The amount to be added to the product's quantity.
	 * @return A message indicating the success or failure of the operation.
	 * @throws RuntimeException If the new quantity is less than or equal to zero.
	 */
	public String increaseQuantity(String productName, int newQuantity) throws RuntimeException {
		if (productName.isEmpty())
			throw new RuntimeException("Error. The name of the product to which the quantity is to be increased cannot be empty.");
		if (newQuantity < 0)
			throw new RuntimeException("Error. The amount to be added must be greater or equals to zero.");
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
	 * Searches for a product based on a search variable and value.
	 *
	 * @param searchVariable the search variable
	 * @param value          the value to search for
	 * @return a string representing the found product
	 * @throws IllegalStateException if the search variable is unexpected
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
	 * Searches for a product with the given category value and returns its details.
	 *
	 * @param value The category value to search for.
	 * @return A string representation of the product's details.
	 */
	public String searchProduct(int value) {
		Product product = null;
		switch (value) {
			case 1 -> product = mercadoLibre.searchProduct("category", Category.BOOKS);
			case 2 -> product = mercadoLibre.searchProduct("category", Category.ELECTRONIC);
			case 3 -> product = mercadoLibre.searchProduct("category", Category.APPAREL_AND_ACCESSORIES);
			case 4 -> product = mercadoLibre.searchProduct("category", Category.FOODS_AND_BEVERAGES);
			case 5 -> product = mercadoLibre.searchProduct("category", Category.STATIONARY);
			case 6 -> product = mercadoLibre.searchProduct("category", Category.SPORTS);
			case 7 -> product = mercadoLibre.searchProduct("category", Category.BEAUTY);
			case 8 -> product = mercadoLibre.searchProduct("category", Category.TOYS);
			default -> throw new RuntimeException("Error. Invalid category.");
		}

		return printProduct(product);
	}

	/**
	 * Prints a product.
	 *
	 * @param product the product to print
	 * @return a string representation of the product
	 */
	private String printProduct(Product product) {
		return (product != null) ? product.toString() : "Product not found.";
	}

	/**
	 * Searches for an order based on a price.
	 *
	 * @param price the price to search for
	 * @return a string representing the found order
	 */
	public String searchOrder(double price) {
		Order order = mercadoLibre.searchOrder("totalPrice", price);
		return printOrder(order);
	}

	/**
	 * Searches for an order based on a date and time.
	 *
	 * @param dateString the date string in the format "yyyy-MM-dd"
	 * @param timeString the time string in the format "HH:mm"
	 * @return a string representing the found order
	 * @throws ParseException if the date or time string cannot be parsed
	 */
	public String searchOrder(String dateString, String timeString) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = dateFormat.parse(dateString);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		Date time = timeFormat.parse(timeString);
		calendar.set(Calendar.HOUR_OF_DAY, time.getHours());
		calendar.set(Calendar.MINUTE, time.getMinutes());
		Order order = mercadoLibre.searchOrder("date", calendar);
		return printOrder(order);
	}

	/**
	 * Searches for an order based on the buyer name.
	 *
	 * @param buyerName the buyer name
	 * @return a string representing the found order, or "Order not found." if no order was found.
	 */
	public String searchOrder(String buyerName) {
		Order order = mercadoLibre.searchOrder("buyerName", buyerName);
		return printOrder(order);
	}

	/**
	 * Prints an order.
	 *
	 * @param order the order to print
	 * @return a string representation of the order
	 */
	private String printOrder(Order order) {
		return (order != null) ? order.toString() : "Order not found.";
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
			case 1 ->
					mercadoLibre.searchInRange("price", minimum, maximum, senseSort, sortVariableForProducts(sortVariable));
			case 2 ->
					mercadoLibre.searchInRange("timesPurchased", minimum, maximum, senseSort, sortVariableForProducts(sortVariable));
			case 3 ->
					mercadoLibre.searchInRange("quantityAvailable", minimum, maximum, senseSort, sortVariableForProducts(sortVariable));
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
	 * @return The String with the sorted list of products within the specified
	 * interval.
	 */
	public String searchInInterval(String startPrefix, String finalPrefix, int senseSort) {
		ArrayList<Product> matches = mercadoLibre.searchInInterval("name", startPrefix, finalPrefix, senseSort,
				sortVariableForProducts(1));
		return printProductList(matches);
	}

	/**
	 * Returns the corresponding string representation of the given variable code.
	 *
	 * @param variable The variable code.
	 * @return The string representation of the variable.
	 * @throws IllegalStateException If an invalid variable code is provided.
	 */
	private String sortVariableForProducts(int variable) throws IllegalStateException {
		return switch (variable) {
			case 1 -> "name";
			case 2 -> "price";
			case 3 -> "category";
			case 4 -> "timesPurchased";
			case 5 -> "quantityAvailable";
			default -> throw new IllegalStateException("Error. Invalid sort variable.");
		};
	}

	private String sortVariableForOrders(int variable) throws IllegalStateException {
		return switch (variable) {
			case 1 -> "buyerName";
			case 2 -> "totalPrice";
			case 3 -> "date";
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

	public Store getStore() {
		return mercadoLibre;
	}
}