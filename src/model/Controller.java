import java.util.ArrayList;

public class Controller {

	private Store store;

	public Controller() {
		store = new Store();
	}

	/**
	 * 
	 * @param productName
	 * @param description
	 * @param price
	 * @param quantity
	 * @param category
	 */
	public String addProduct(String productName, String description, int price, int quantity, int category) {
		// TODO - implement Controller.addProduct
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param buyerName
	 * @param id
	 * @param productList
	 */
	public void addOrder(String buyerName, String id, ArrayList<String> productList) {
		store.addOrder(new Order(buyerName, id, productList));
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