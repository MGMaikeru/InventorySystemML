package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SearcherTest {

	private final Product product1 = new Product("Cboc Two", "An incredible video game console.",
			120000.0, 2, Category.ELECTRONIC, 6);
	private final Product product2 = new Product("Ball", "Soccer ball", 200000.0, 7,
			Category.SPORTS, 4);
	private final Product product3 = new Product("Miguel in Wonderland", "An unexpected adventure awaits Miguel in wonderland. Join him on his journey.",
			300000.0, 2, Category.BOOKS, 5);

	private final Product product4 = new Product("apple watch", "Smartwatch with various features.", 500000,
			3, Category.ELECTRONIC, 0);

	private final Product product5 = new Product("play state 3", "Video game console with advanced graphics.", 2500000.0,
			1, Category.ELECTRONIC, 0);

	private final Product product6 = new Product("wood chair", "Comfortable chair made of solid wood.", 100000,
			8, Category.BEAUTY, 0);

	private final Product product7 = new Product("wood table", "Sturdy table made of high-quality wood.", 250000,
			3, Category.BEAUTY, 0);

	private final Product product8 = new Product("HD laptop", "Intel Core, 2 Ram, 500GB HDD", 1200000,
			2, Category.ELECTRONIC, 0);

	private final Product product9 = new Product("gamer headphones", "High-quality headphones with immersive sound.",
			100.0, 15, Category.ELECTRONIC, 0);

	private Order order1;

	private Order order2;

	private Order order3;

	private ArrayList<Product> productsList;
	private Searcher<Product, String> searcherByString;
	private Searcher<Product, Double> searcherByNumber;
	private Searcher<Product, Category> searcherByCategory;
	private ArrayList<Order> orderList;
	private Searcher<Order, String> orderSearcherByString;
	private Searcher<Order, Double> orderSearcherByNumber;
	private Searcher<Order, Calendar> orderSearcherByCalendar;

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
		orderList = new ArrayList<>();
		orderSearcherByNumber = new Searcher<>();
	}

	public void setupStage3() {
		ArrayList<Product> productList1 = new ArrayList<>();
		productList1.add(product6);
		productList1.add(product7);
		order1 = new Order("Camilo", productList1, Calendar.getInstance());
		ArrayList<Product> productList2 = new ArrayList<Product>();
		productList2.add(product5);
		order2 = new Order("Esteban", productList2, Calendar.getInstance());
		ArrayList<Product> productList3 = new ArrayList<Product>();
		productList3.add(product4);
		productList3.add(product8);
		order3 = new Order("Sara", productList3, Calendar.getInstance());
		orderList = new ArrayList<>();
		orderList.add(order1);
		orderList.add(order2);
		orderList.add(order3);
		orderSearcherByString = new Searcher<>();
		orderSearcherByNumber = new Searcher<>();
		orderSearcherByCalendar = new Searcher<>();
	}

	@Test
	public void searchProductByNameTest1() {
		setupStage1();
		Product product = searcherByString.search(productsList, "name", "Harry Potter and the sorcerer's stone");
		Assertions.assertNull(product);
	}

	@Test
	public void searchProductByPriceTest2() {
		setupStage1();
		Product product = searcherByNumber.search(productsList, "price", 300000.0);
		Assertions.assertEquals(product3, product);
	}

	@Test
	public void searchProductByCategoryTest3() {
		setupStage1();
		Product product = searcherByCategory.search(productsList, "category", Category.ELECTRONIC);
		Assertions.assertEquals(product1, product);
	}

	@Test
	public void searchProductTest4() {
		setupStage2();
		Assertions.assertNull(searcherByString.search(productsList, "name", "Cboc"));
	}

	@Test
	public void searchProductTest5() {
		setupStage1();
		Assertions.assertThrows(IllegalArgumentException.class, () -> searcherByNumber.search(productsList, "pricess", 100.0));
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
	public void searchOrderByDate3() throws ParseException {
		setupStage3();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String dateString = dateFormat.format(calendar.getTime());
		assertEquals(order2, orderSearcherByString.search(orderList, "date", dateString));
	}

	@Test
	public void searchOrderTest4() {
		setupStage2();
		orderSearcherByNumber.search(orderList, "buyerName", 2500000.0);
		fail("Exception is throw");

	}

	@Test
	public void searchOrderTest5() {
		setupStage3();
		orderSearcherByNumber.search(orderList, "buyerName", 2500000.0);
		fail("Exception is throw");
	}
}
