package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class StoreTest {

	private final Product product1 = new Product("Gamer headphones", "High-quality headphones with immersive sound.", 100.0, 15, Category.ELECTRONIC, 0);
	private final Product product2 = new Product("Olympic bar", "Sturdy barbell for weightlifting and strength training.", 149.99, 5, Category.SPORTS, 0);
	private final Product product3 = new Product("Diapers", "Disposable diapers for babies.", 19.99, 50, Category.APPAREL_AND_ACCESSORIES, 0);
	private final Product product4 = new Product("Barbie", "Classic Barbie doll for imaginative play.", 24.99, 20, Category.TOYS, 0);
	private final Product product5 = new Product("Lipstick", "Long-lasting lipstick in a vibrant shade.", 12.99, 15, Category.BEAUTY, 0);
	private ArrayList<Product> productsList;
	private Store store;

	public void setupStage1() {
		store = new Store();
		productsList = new ArrayList<>();
		productsList.add(product1);
		productsList.add(product2);
		productsList.add(product3);
		productsList.add(product4);
		productsList.add(product5);
	}

	@Test
	public void testSortSearchResults1() {
		setupStage1();
		productsList = store.sortSearchResults(productsList, 1, "name");
		Assertions.assertEquals(product4, productsList.get(0));
		Assertions.assertEquals(product3, productsList.get(1));
		Assertions.assertEquals(product1, productsList.get(2));
		Assertions.assertEquals(product5, productsList.get(3));
		Assertions.assertEquals(product2, productsList.get(4));
	}

	@Test
	public void testSortSearchResults2() {
		setupStage1();
		store.sortSearchResults(productsList, 1, "price");
		Assertions.assertEquals(product5, productsList.get(0));
		Assertions.assertEquals(product3, productsList.get(1));
		Assertions.assertEquals(product4, productsList.get(2));
		Assertions.assertEquals(product1, productsList.get(3));
		Assertions.assertEquals(product2, productsList.get(4));
	}

	@Test
	public void testSortSearchResults3() {
		setupStage1();
		productsList = store.sortSearchResults(productsList, 2, "name");
		Assertions.assertEquals(product2, productsList.get(0));
		Assertions.assertEquals(product5, productsList.get(1));
		Assertions.assertEquals(product1, productsList.get(2));
		Assertions.assertEquals(product3, productsList.get(3));
		Assertions.assertEquals(product4, productsList.get(4));
	}

	@Test
	public void testSortSearchResults4() {
		setupStage1();
		store.sortSearchResults(productsList, 2, "price");
		Assertions.assertEquals(product2, productsList.get(0));
		Assertions.assertEquals(product1, productsList.get(1));
		Assertions.assertEquals(product4, productsList.get(2));
		Assertions.assertEquals(product3, productsList.get(3));
		Assertions.assertEquals(product5, productsList.get(4));
	}

}
