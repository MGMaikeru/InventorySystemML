package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ControllerTest {

	private Controller controller;
	private ArrayList<Product> products;
	private final Product product1 = new Product("Miguel in wonderland", "An unexpected adventure awaits Miguel in" +
			" wonderland. Join him on his journey.", 100000, 7, Category.BOOKS, 0);
	private final Product product2 = new Product("Cboc Two", "An incredible video game console.", 2000000,
			9, Category.ELECTRONIC, 0);
	private final Product product3 = new Product("HD laptop", "Intel Core, 2 Ram, 500GB HDD", 1200000,
			7, Category.ELECTRONIC, 0);


	private ArrayList<String> productsAndQuantitiesList;

	public void setupStage1() {
		controller = new Controller();
	}

	public void setupStage2() {
		controller = new Controller();
		products = new ArrayList<>();
		products.add(product1);
		products.add(product2);
		products.add(product3);
		Store store = controller.getStore();
		store.addProduct(product1);
		store.addProduct(product2);
		store.addProduct(product3);
		productsAndQuantitiesList = new ArrayList<>();
	}

	@Test
	public void addProductTest1() {
		setupStage1();
		String expected = "Product added!";
		Assertions.assertEquals(expected, controller.addProduct("Miguel in wonderland", "An unexpected adventure awaits Miguel in wonderland. " +
				"Join him on his journey", 100000.0, 7, 1, 0));
	}

	@Test
	public void addProductTest2() {
		setupStage1();
		String expected = "Product added!";
		Assertions.assertEquals(expected, controller.addProduct("HD laptop", "Intel Core, 2 Ram, 500GB HDD", 1200000,
				7, 2, 0));
	}

	@Test
	public void addProductTest3() {
		setupStage1();
		try {
			controller.addProduct("", "An unexpected adventure awaits Miguel in wonderland. " +
					"Join him on his journey", 100000, 7, 1, 0);
			Assertions.fail("An exception is expected as the name is empty.");
		} catch (RuntimeException e) {
			Assertions.assertEquals("Error. The name of the product is empty.", e.getMessage());
		}
	}

	@Test
	public void addProductTest4() {
		setupStage1();
		try {
			controller.addProduct("Miguel in wonderland", "An unexpected adventure awaits Miguel in wonderland. " +
					"Join him on his journey", 100000, -5, 1, 0);
			Assertions.fail("An exception is expected as the quantity is negative.");
		} catch (RuntimeException e) {
			Assertions.assertEquals("Error. The quantity of product in inventory cannot be negative.", e.getMessage());
		}
	}

	@Test
	public void addProductTest5() {
		setupStage1();
		try {
			controller.addProduct("Miguel in wonderland", "An unexpected adventure awaits Miguel in wonderland. " +
					"Join him on his journey", 100000, 7, 10, 0);
			Assertions.fail("An exception is expected because the category don't exist");
		} catch (RuntimeException e) {
			Assertions.assertEquals("Error. Invalid category.", e.getMessage());
		}
	}

	@Test
	public void addOrderTest1() {
		setupStage2();
		productsAndQuantitiesList.add("Miguel in wonderland");
		productsAndQuantitiesList.add("3");
		productsAndQuantitiesList.add("Cboc Two");
		productsAndQuantitiesList.add("5");

		String result = controller.addOrder("Miguel", productsAndQuantitiesList);
		Assertions.assertEquals("Order added!", result);
		assert 4 == product1.getQuantityAvailable();
		assert 4 == product2.getQuantityAvailable();
	}

	@Test
	public void addOrderTest2() {
		setupStage2();
		productsAndQuantitiesList.add("Miguel in wonderland");
		productsAndQuantitiesList.add("3");
		productsAndQuantitiesList.add("Cboc Two");
		productsAndQuantitiesList.add("5");
		productsAndQuantitiesList.add("HD laptop");
		productsAndQuantitiesList.add("2");

		String result = controller.addOrder("Miguel", productsAndQuantitiesList);
		Assertions.assertEquals("Order added!", result);
		assert 4 == product1.getQuantityAvailable();
		assert 4 == product2.getQuantityAvailable();
		assert 5 == product3.getQuantityAvailable();
	}

	@Test
	public void addOrderTest3() {
		setupStage2();
		try {
			productsAndQuantitiesList.add("Miguel in wonderland");
			productsAndQuantitiesList.add("3");
			productsAndQuantitiesList.add("Cboc Two");
			productsAndQuantitiesList.add("5");
			productsAndQuantitiesList.add("HD laptop");
			productsAndQuantitiesList.add("2");

			controller.addOrder("", productsAndQuantitiesList);
			Assertions.fail("An exception is expected as the buyer name is empty.");
		} catch (RuntimeException e) {
			Assertions.assertEquals("Error. The name of buyer is empty.", e.getMessage());
		}
	}

	@Test
	public void addOrderTest4() {
		setupStage2();
		try {
			productsAndQuantitiesList.add("Miguel in wonderland");
			productsAndQuantitiesList.add("50");
			productsAndQuantitiesList.add("Cboc Two");
			productsAndQuantitiesList.add("5");
			productsAndQuantitiesList.add("HD laptop");
			productsAndQuantitiesList.add("2");

			controller.addOrder("Miguel", productsAndQuantitiesList);
			Assertions.fail("An exception is expected as the product -Miguel in wonderland- exceeds the stock of the product.");
		} catch (RuntimeException e) {
			Assertions.assertEquals("Error. One of the products exceeds the quantity available.", e.getMessage());
		}
	}

	@Test
	public void addOrderTest5() {
		setupStage2();
		try {
			controller.addOrder("Miguel", productsAndQuantitiesList);
			Assertions.fail("An exception is expected as the product list is empty.");
		} catch (RuntimeException e) {
			String expected = "Error. It is not possible to create an order with an empty product list.";
			Assertions.assertEquals(expected, e.getMessage());
		}
	}

	@Test
	public void increaseQuantityTest1() {
		setupStage1();
		controller.increaseQuantity("HD laptop", 3);
		assert 10 == product3.getQuantityAvailable();
	}

	@Test
	public void increaseQuantityTest2() {
		setupStage1();
		controller.increaseQuantity("HD laptop", 0);
		assert 7 == product3.getQuantityAvailable();
	}

	@Test
	public void increaseQuantityTest3() {
		setupStage1();
		try {
			controller.increaseQuantity("HP laptop", -2);
			Assertions.fail("An exception is expected because the increase amount is negative.");
		} catch (RuntimeException e) {
			Assertions.assertEquals("Error. The amount to be added must be greater or equals to zero.", e.getMessage());
			assert 7 == product3.getQuantityAvailable();
		}
	}

	@Test
	public void increaseQuantityTest4() {
		setupStage2();
		try {
			controller.increaseQuantity("", 5);
			Assertions.fail("An exception is expected because the product name is empty.");
		} catch (RuntimeException e) {
			Assertions.assertEquals("Error. The name of the product to which the quantity is to be increased cannot be empty.", e.getMessage());
			assert 7 == product3.getQuantityAvailable();
		}
	}

}
