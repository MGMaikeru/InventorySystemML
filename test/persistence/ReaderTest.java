package persistence;

import model.Order;
import model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

public class ReaderTest {

	private final String relativePath = "\\testData";
	private ArrayList<Product> productsList;
	private ArrayList<Order> ordersList;
	private Reader reader;

	public void setupStage1() {
		reader = new Reader();
		productsList = new ArrayList<>();
		ordersList = new ArrayList<>();
	}

	@Test
	public void readGsonTest1() {
		setupStage1();
		reader.readGson(relativePath, productsList, ordersList);
		File dataDirectory = new File(System.getProperty("user.dir") + "\\data" + relativePath);
		Assertions.assertTrue(dataDirectory.exists());
	}

	@Test
	public void readGsonTest2() {
		setupStage1();
		reader.readGson(relativePath, productsList, ordersList);
		Assertions.assertEquals(3, productsList.size());
		Assertions.assertEquals(1, ordersList.size());
	}

}
