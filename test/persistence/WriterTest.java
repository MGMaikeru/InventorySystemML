package persistence;

import com.google.gson.Gson;
import model.Category;
import model.Order;
import model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {
	private final String relativePath = "\\testData";
	private ArrayList<Product> productsList;
	private ArrayList<Order> ordersList;
	private Gson gson;
	private final Product product1 = new Product("Cboc Two", "An incredible video game console.", 120000.0, 2, Category.ELECTRONIC, 6);
	private final Product product2 = new Product("Ball", "Soccer ball", 200000.0, 7,
			Category.SPORTS, 4);
	private final Product product3 = new Product("Miguel in Wonderland", "An unexpected adventure awaits Miguel in wonderland. Join him on his journey.",
			300000.0, 2, Category.BOOKS, 5);
	private final Writer writer = new Writer();

	public void setupStage1() {
		productsList = new ArrayList<>();
		ordersList = new ArrayList<>();
		gson = new Gson();
		productsList.add(product1);
		productsList.add(product2);
		productsList.add(product3);
	}

	public void setupStage2() {
		productsList = new ArrayList<>();
		ordersList = new ArrayList<>();
		gson = new Gson();
		productsList.add(product1);
		productsList.add(product2);
		productsList.add(product3);
		Order order1 = new Order("Miguel", productsList, Calendar.getInstance());
		ordersList.add(order1);
		try {
			writer.saveProducts(relativePath + "\\productsTest2.json", productsList);
			writer.saveOrders(relativePath + "\\ordersTest2.json", ordersList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void saveTest1() {
		setupStage1();
		File productFile = null, ordersFile = null;
		Product[] productsStored = null;
		try {
			writer.saveProducts(relativePath + "\\productsTest1.json", productsList);
			writer.saveOrders(relativePath + "\\ordersTest1.json", ordersList);
			productFile = new File(System.getProperty("user.dir") + "\\data\\testData\\productsTest1.json");
			ordersFile = new File(System.getProperty("user.dir") + "\\data\\testData\\ordersTest1.json");
			String productsJson = new String(java.nio.file.Files.readAllBytes(productFile.toPath()));
			productsStored = gson.fromJson(productsJson, Product[].class);
		} catch (Exception e) {
			fail("It was expected that the read save method would not throw any exception.");
			e.printStackTrace();
		}
		Assertions.assertTrue(productFile.exists());
		Assertions.assertTrue(ordersFile.exists());
		Assertions.assertEquals(3, productsStored.length);
		Assertions.assertEquals(product1.getName(), productsStored[0].getName());
		Assertions.assertEquals(product2.getName(), productsStored[1].getName());
		Assertions.assertEquals(product3.getName(), productsStored[2].getName());
	}

	@Test
	public void saveTest2() {
		setupStage2();
		try {
			writer.saveProducts(relativePath + "\\productsTest2.json", productsList);
			writer.saveOrders(relativePath + "\\ordersTest2.json", ordersList);
		} catch (IOException e) {
			fail("It was expected that the read save method would not throw any exception.");
			e.printStackTrace();
		}
	}

}
