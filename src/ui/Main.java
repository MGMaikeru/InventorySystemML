package ui;

import model.Category;
import model.Controller;

public class Main {
	private Controller controller;

	public Main() {
		controller = new Controller();
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.controller.addProduct("Product 2", "Description 2", 10.99, 8, 2, 5);
		main.controller.addProduct("Product 3", "Description 3", 24.99, 3, 3, 10);
		main.controller.addProduct("Product 1", "Description 1", 7.49, 5, 1, 2);
		main.controller.addProduct("Product 4", "Description 4", 15.99, 4, 4, 7);
		main.controller.addProduct("Product 5", "Description 5", 12.49, 6, 5, 3);
		main.controller.addProduct("Product 6", "Description 6", 9.99, 2, 6, 8);
		main.controller.addProduct("Product 7", "Description 7", 18.99, 7, 7, 4);
		main.controller.addProduct("Product 8", "Description 8", 5.99, 1, 8, 1);
		main.controller.addProduct("Product 9", "Description 9", 11.99, 3, 5, 6);
		main.controller.addProduct("Product 10", "Description 10", 8.99, 5, 1, 5);

		System.out.println(main.controller.searchProduct(2, 10));

		System.out.println(main.controller.searchInRange(4, 8));
		System.out.println(main.controller.searchInInterval(1, "Product 1", "Product 8"));

	}

}