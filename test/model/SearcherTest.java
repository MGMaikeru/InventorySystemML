package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;

public class SearcherTest {

	private final Product product1 = new Product("Cboc Two", "An incredible video game console.", 120000.0, 2, Category.ELECTRONIC, 6);
	private final Product product2 = new Product("Ball", "Soccer ball", 200000.0, 7,
			Category.SPORTS, 4);
	private final Product product3 = new Product("Miguel in Wonderland", "An unexpected adventure awaits Miguel in wonderland. Join him on his journey.",
			300000.0, 2, Category.BOOKS, 5);

	private final Product product4 = new Product("apple watch", "Smartwatch with various features.", 500000.0,
			3, Category.ELECTRONIC, 0);

	private final Product product5 = new Product("play state 3", "Video game console with advanced graphics.", 2500000.0,
			1, Category.ELECTRONIC, 0);

	private final Product product6 = new Product("wood chair", "Comfortable chair made of solid wood.", 100000.0,
			8, Category.BEAUTY, 0);

	private final Product product7 = new Product("wood table", "Sturdy table made of high-quality wood.", 250000.0,
			3, Category.BEAUTY, 0);

	private final Product product8 = new Product("HD laptop", "Intel Core, 2 Ram, 500GB HDD", 1200000.0,
			2, Category.ELECTRONIC, 0);

	private Order order2;

	private ArrayList<Product> productsList;
	private Searcher<Product, String> productSearcherByString;
	private Searcher<Product, Double> productSearcherByNumber;
	private Searcher<Product, Category> searcherByCategory;
	private ArrayList<Order> orderList;
	private Searcher<Order, String> orderSearcherByString;
	private Searcher<Order, Double> orderSearcherByNumber;
	private Searcher<Order, Calendar> orderSearcherByDate;
	private final Calendar date = Calendar.getInstance();

	public void setupStage1() {
		productsList = new ArrayList<>();
		productSearcherByString = new Searcher<>();
		productSearcherByNumber = new Searcher<>();
		searcherByCategory = new Searcher<>();
		productsList.add(product1);
		productsList.add(product2);
		productsList.add(product3);
		productsList.add(product4);
		productsList.add(product5);
		productsList.add(product6);
		productsList.add(product7);
		productsList.add(product8);
		productsList.sort(Comparator.comparing(Product::getName));
	}

	public void setupStage2() {
		productsList = new ArrayList<>();
		productSearcherByString = new Searcher<>();
		orderList = new ArrayList<>();
		orderSearcherByNumber = new Searcher<>();
	}

	public void setupStage3() {
		ArrayList<Product> productList1 = new ArrayList<>();
		productList1.add(product6);
		productList1.add(product7);
		Order order1 = new Order("Camilo", productList1, Calendar.getInstance());
		ArrayList<Product> productList2 = new ArrayList<>();
		productList2.add(product5);
		order2 = new Order("Esteban", productList2, date);
		ArrayList<Product> productList3 = new ArrayList<>();
		productList3.add(product4);
		productList3.add(product8);
		Order order3 = new Order("Sara", productList3, Calendar.getInstance());
		orderList = new ArrayList<>();
		orderList.add(order1);
		orderList.add(order2);
		orderList.add(order3);
		orderSearcherByString = new Searcher<>();
		orderSearcherByNumber = new Searcher<>();
		orderSearcherByDate = new Searcher<>();
	}

	@Test
	public void searchProductByNameTest1() {
		setupStage1();
		Product product = productSearcherByString.search(productsList, "name", "Harry Potter and the sorcerer's stone");
		Assertions.assertNull(product);
	}

	@Test
	public void searchProductByPriceTest2() {
		setupStage1();
		productsList.sort(Comparator.comparing(Product::getPrice));
		Product product = productSearcherByNumber.search(productsList, "price", 300000.0);
		Assertions.assertEquals(product3, product);
	}

	@Test
	public void searchProductByCategoryTest3() {
		setupStage1();
		productsList.sort(Comparator.comparing(Product::getCategory));
		Product product = searcherByCategory.search(productsList, "category", Category.ELECTRONIC);
		Assertions.assertEquals(product4, product);
	}

	@Test
	public void searchProductTest4() {
		setupStage2();
		Assertions.assertNull(productSearcherByString.search(productsList, "name", "Cboc"));
	}

	@Test
	public void searchProductTest5() {
		setupStage1();
		Assertions.assertThrows(IllegalArgumentException.class, () -> productSearcherByNumber.search(productsList, "pricess", 100.0));
	}

	@Test
	public void searchProductTest6() {
		setupStage1();
		Assertions.assertThrows(ClassCastException.class, () -> productSearcherByNumber.search(productsList, "name", 100.0));
	}

	@Test
	public void searchOrderByNameTest1() {
		setupStage3();
		Assertions.assertNull(orderSearcherByString.search(orderList, "buyerName", "Andres"));
	}

	@Test
	public void searchOrderByPriceTest2() {
		setupStage3();
		Assertions.assertEquals(order2, orderSearcherByNumber.search(orderList, "totalPrice", 2500000.0));
	}

	@Test
	public void searchOrderByDate3() {
		setupStage3();
		Assertions.assertEquals(order2, orderSearcherByDate.search(orderList, "date", date));
	}

	@Test
	public void searchOrderByNameTest4() {
		setupStage2();
		Assertions.assertNull(orderSearcherByNumber.search(orderList, "totalPrice", 100000.0));
	}

	@Test
	public void searchOrderTest5() {
		setupStage3();
		Assertions.assertThrows(IllegalArgumentException.class, () -> orderSearcherByString.search(orderList, "buyer’sName", "Sara"));
	}

	@Test
	public void searchOrderTest6() {
		setupStage3();
		Assertions.assertThrows(ClassCastException.class, () -> orderSearcherByNumber.search(orderList, "buyerName", 2500000.0));
	}

	@Test
	public void searchByRange1() {
		setupStage1();
		assert productSearcherByNumber.filterList(productsList, "price", 0.0, 2000.0).isEmpty();
	}

	@Test
	public void searchByRange2() {
		setupStage1();
		Assertions.assertEquals(productsList, productSearcherByNumber.filterList(productsList, "price", 0.0, 5000000.0));
	}

	@Test
	public void searchByRange3() {
		setupStage1();
		productsList.sort(Comparator.comparing(Product::getPrice));
		ArrayList<Product> matches = productSearcherByNumber.filterList(productsList, "price", 200000.0, 300000.0);
		Assertions.assertEquals(3, matches.size());
		Assertions.assertEquals(product2, matches.get(0));
		Assertions.assertEquals(product7, matches.get(1));
		Assertions.assertEquals(product3, matches.get(2));

	}

	@Test
	public void searchByRange4() {
		setupStage1();
		Assertions.assertThrows(RuntimeException.class, () -> productSearcherByNumber.filterList(null, "timesPurchased", 0.0, 10.0));
	}

	@Test
	public void searchByRange5() {
		setupStage1();
		productsList.sort(Comparator.comparing(Product::getPrice));
		Assertions.assertThrows(RuntimeException.class, () -> productSearcherByNumber.filterList(productsList, "price", 100000.0, 0.0));
	}

	@Test
	public void searchProductByInterval1() {
		setupStage1();
		assert productSearcherByString.filterList(productsList, "name", "Aa", "Au").isEmpty();
	}

	@Test
	public void searchProductByInterval2() {
		setupStage1();
		ArrayList<Product> matches = productSearcherByString.filterList(productsList, "name", "C", "H");
		Assertions.assertEquals(1, matches.size());
		Assertions.assertEquals(product1, matches.get(0));
	}

	@Test
	public void searchProductByInterval3() {
		setupStage1();
		ArrayList<Product> matches = productSearcherByString.filterList(productsList, "name", "C", "P");
		Assertions.assertEquals(3, matches.size());
		Assertions.assertEquals(product1, matches.get(0));
		Assertions.assertEquals(product8, matches.get(1));
		Assertions.assertEquals(product3, matches.get(2));
	}

	@Test
	public void searchProductByInterval4() {
		setupStage1();
		Assertions.assertThrows(RuntimeException.class, () -> productSearcherByString.filterList(productsList, "name", "Zz", "Aa"));
	}

	@Test
	public void searchProductByInterval5() {
		setupStage1();
		Assertions.assertThrows(RuntimeException.class, () -> productSearcherByString.filterList(productsList, "name", "Bb", "Aa"));
	}

}
