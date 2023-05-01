import java.util.ArrayList;

public class Store {

	private ArrayList<Order> orders;

	public Store() {
		orders = new ArrayList<>();
	}

	/**
	 * 
	 * @param variable
	 * @param value
	 */
	public Product searchProduct(int variable, int value) {
		// TODO - implement Store.searchProduct
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param variable
	 * @param value
	 */
	public Product searchProduct(int variable, String value) {
		// TODO - implement Store.searchProduct
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param variable
	 * @param value
	 */
	public Order searchOrder(int variable, String value) {
		// TODO - implement Store.searchOrder
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param variable
	 * @param value
	 */
	public Order searchOrder2(int variable, String value) {
		// TODO - implement Store.searchOrder2
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param variable
	 * @param min
	 * @param max
	 */
	public ArrayList<Product> searchInRange(int variable, int min, int max) {
		// TODO - implement Store.searchInRange
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param variable
	 * @param prefix
	 * @param suffix
	 */
	public ArrayList<Product> searchInInterval(int variable, String prefix, String suffix) {
		// TODO - implement Store.searchInInterval
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param sense
	 * @param variable
	 */
	public ArrayList<Product> sort(int sense, int variable) {
		// TODO - implement Store.sort
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param product
	 */
	public String addProduct(Product product) {
		// TODO - implement Store.addProduct
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param order
	 */
	public String addOrder(Order order) {
		if (order == null) return "Invalid order!";
		orders.add(order);
		return "Order number" + order.getId() + "has been successful registered!";
	}

	/**
	 * 
	 * @param productName
	 * @param newQuantity
	 */
	public String increaseQuantity(String productName, int newQuantity) {
		// TODO - implement Store.increaseQuantity
		throw new UnsupportedOperationException();
	}

}