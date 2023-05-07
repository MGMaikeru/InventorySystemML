package persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import model.Order;
import model.Product;

import java.io.*;
import java.util.ArrayList;

public class Writer {
	public void save(ArrayList<Product> products, ArrayList<Order> orders) {
		try {
			saveProducts(products);
			saveOrders(orders);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void saveProducts(ArrayList<Product> products) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try (FileWriter writer = new FileWriter(System.getProperty("user.dir") + "\\data\\products.json")) {
			// Serialize the productList to JSON and write to the file
			gson.toJson(products, writer);
		} catch (JsonIOException | IOException e) {
			e.printStackTrace();
		}
	}

	private void saveOrders(ArrayList<Order> orders) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try (FileWriter writer = new FileWriter(System.getProperty("user.dir") + "\\data\\orders.json")) {
			gson.toJson(orders, writer);
		} catch (JsonIOException | IOException e) {
			e.printStackTrace();
		}
	}

}
