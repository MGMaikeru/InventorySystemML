package persistence;

import com.google.gson.Gson;
import model.Order;
import model.Product;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {
	private final String PROJECT_PATH = System.getProperty("user.dir") + "\\data";

	public void readGson(ArrayList<Product> products, ArrayList<Order> orders) {
		String RELATIVE_PATH = "\\systemData";
		readGson(RELATIVE_PATH, products, orders);
	}

	public void readGson(String dataRelativePath, ArrayList<Product> products, ArrayList<Order> orders) {
		Gson gson = new Gson();
		File dataDirectory = new File(PROJECT_PATH + dataRelativePath);
		File productsInformation = new File(dataDirectory + "\\products.json");
		File ordersInformation = new File(dataDirectory + "\\orders.json");
		try {
			if (!dataDirectory.exists()) dataDirectory.mkdir();
			if (!productsInformation.exists()) productsInformation.createNewFile();
			if (!ordersInformation.exists()) ordersInformation.createNewFile();

			String productsJson = new String(java.nio.file.Files.readAllBytes(productsInformation.toPath()));
			String ordersJson = new String(java.nio.file.Files.readAllBytes(ordersInformation.toPath()));
			Product[] productsStored = gson.fromJson(productsJson, Product[].class);
			Order[] ordersStored = gson.fromJson(ordersJson, Order[].class);
			if (productsStored != null) products.addAll(List.of(productsStored));
			if (ordersStored != null) orders.addAll(List.of(ordersStored));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
