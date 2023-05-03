package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class SearcherTest {

	private final Product product1 = new Product("Cboc Two", "An incredible video game console.", 120000.0, 2, Category.ELECTRONIC, 6);
	private final Product product2 = new Product("Ball", "Soccer ball", 200000.0, 7, Category.SPORTS, 4);
	private final Product product3 = new Product("Miguel in Wonderland", "An unexpected adventure awaits Miguel in wonderland. Join him on his journey.", 300000.0, 2, Category.BOOKS, 5);
	// private final Order order1 = new Order();

	private ArrayList<Product> productsList;
	private Searcher<Product, String> searcherByString;
	private Searcher<Product, Double> searcherByNumber;
	private Searcher<Product, Category> searcherByCategory;

	public void setupStage1() {
		productsList = new ArrayList<>();
		searcherByString = new Searcher<>();
		searcherByNumber = new Searcher<>();
		searcherByCategory = new Searcher<>();
		productsList.add(product1);
		productsList.add(product2);
		productsList.add(product3);
	}

	public void setupStage2() {
		productsList = new ArrayList<>();
		searcherByString = new Searcher<>();
	}

	@Test
	public void searchProductByNameTest1() {
		setupStage1();
		Product product = searcherByString.binarySearch(productsList, "name", "Harry Potter and the sorcerer's stone");
		Assertions.assertNull(product);
	}

	@Test
	public void searchProductByPriceTest2() {
		setupStage1();
		Product product = searcherByNumber.binarySearch(productsList, "price", 300000.0);
		Assertions.assertEquals(product3, product);
	}

	@Test
	public void searchProductByCategoryTest3() {
		setupStage1();
		Product product = searcherByCategory.binarySearch(productsList, "category", Category.ELECTRONIC);
		Assertions.assertEquals(product1, product);
	}

	@Test
	public void searchProductTest4() {
		setupStage2();
		Assertions.assertNull(searcherByString.binarySearch(productsList, "name", "Cboc"));
	}

	@Test
	public void searchProductTest5() {
		setupStage1();
		Assertions.assertThrows(IllegalArgumentException.class, () -> searcherByNumber.binarySearch(productsList, "pricess", 100.0));
	}

}
