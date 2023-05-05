package ui;

import model.Category;
import model.Controller;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private final Controller controller;
	private final Scanner input;

	public Main() {
		input = new Scanner(System.in);
		controller = new Controller();
	}

	public static void main(String[] args) {
		System.out.println("<<<<< Passenger Sort System >>>>>");
		Main m = new Main();
		m.displayStartMenu();
	}

	public void displayStartMenu() {
		System.out.print("1) \n2)Register new order \n3) \n4) \n5) Exit\nSelected: ");
		int option = input.nextInt();
		executeOption(option);
	}

	public void executeOption(int option) {
		switch (option) {
			case 1:

				break;
			case 2:
				registerProduct();
				break;
			case 3:

				break;
			case 4:

				break;
			case 5:
				System.out.println("\nThanks for using the program. Until next time!");
				break;
			default:
				System.out.println("Error. Type a valid option.\n");
				break;
		}
		if (option != 5)
			displayStartMenu();

	}

	public void registerProduct() {
		System.out.println("Type buyer's name: ");
		input.nextLine();
		String buyerName = input.nextLine();
		String isContinue = "Y";
		ArrayList<String> products = new ArrayList<>();
		while (isContinue.equals("Y")) {
			System.out.println("Type product: ");
			String productName = input.nextLine();
			System.out.println("Type quantity: ");
			int productQuantity = input.nextInt();
			if(!controller.checkProduct(productName, productQuantity))
				System.out.println("Product doesn't exist or product out of stock!");
			System.out.println("¿Want you add another product? Y/N");
			input.nextLine();
			isContinue = input.nextLine();
		}
		controller.addOrder(buyerName, products);
	}

	/*public static void main(String[] args) {
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
		// main.controller.addProduct("Product 10", "Description 10", 8.99, 5, 1, 5);

		System.out.println(main.controller.searchProduct(2, 10));
		System.out.println();
		// System.out.println(main.controller.searchInRange(4, 8, 1, 1));
		System.out.println(main.controller.searchInInterval("Product 1", "Product 5", 1, 1));
		// System.out.println(main.controller.searchInInterval(1, "Product 1", "Product 8"));

	}*/

}