package persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import model.Order;
import model.Product;

import java.io.*;
import java.util.ArrayList;

public class Writer {
	private final String PROJECT_PATH = System.getProperty("user.dir") + "\\data";

	public void save(ArrayList<Product> products, ArrayList<Order> orders) {
		try {
			String RELATIVE_PATH = "\\systemData";
			saveProducts(RELATIVE_PATH + "\\products.json", products);
			saveOrders(RELATIVE_PATH + "\\orders.json", orders);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveProducts(String relativePath, ArrayList<Product> products) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try (FileWriter writer = new FileWriter(PROJECT_PATH + relativePath)) {
			// Serialize the productList to JSON and write to the file
			gson.toJson(products, writer);
		} catch (JsonIOException | IOException e) {
			e.printStackTrace();
		}
	}

	public void saveOrders(String relativePath, ArrayList<Order> orders) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try (FileWriter writer = new FileWriter(PROJECT_PATH + relativePath)) {
			gson.toJson(orders, writer);
		} catch (JsonIOException | IOException e) {
			e.printStackTrace();
		}
	}

}
