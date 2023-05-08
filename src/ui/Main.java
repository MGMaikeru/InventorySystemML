package ui;

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
		System.out.println("<<<<< Ultimate Inventory System Manager >>>>>");
		Main main = new Main();
		main.displayStartMenu();
	}

	public void displayStartMenu() {
		System.out.print("""
				    
				1) Register product\s
				2) Register new order\s
				3) Increase quantity of registered product\s
				4) Search product
				5) Search order\s
				6) Exit\s
				Selected:\s""");
		int option = isInteger(input);
		executeOption(option);
	}

	public void executeOption(int option) {
		try {
			switch (option) {
				case 1 -> registerProduct();
				case 2 -> registerOrder();
				case 3 -> increaseProductQuantity();
				case 4 -> searchProduct();
				case 5 -> System.out.println(searchOrder());
				case 6 -> closeProgram();
				default -> System.out.println("Error. Type a valid option.\n");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		if (option != 6) displayStartMenu();
	}

	public void registerProduct() {
		System.out.print("Type the product name: ");
		input.nextLine();
		String productName = input.nextLine();
		System.out.println("Type the product description: ");
		String description = input.nextLine();
		System.out.print("Type the product price (if the price is not integer use ',' for the decimals): ");
		double price = isDouble(input);
		System.out.print("Type the available quantity: ");
		int availableQuantity = isInteger(input);
		printCategoryOptions();
		int productCategory = isInteger(input);
		System.out.println("Number of times purchased: ");
		int timesPurchased = isInteger(input);
		System.out.println(controller.addProduct(productName, description, price, availableQuantity, productCategory, timesPurchased));
	}

	public void registerOrder() {
		System.out.print("Type buyer's name: ");
		input.nextLine();
		String buyerName = input.nextLine();
		char selected = 'Y';
		ArrayList<String> products = new ArrayList<>();
		while (selected == 'Y') {
			System.out.print("Type the product: ");
			String productName = input.nextLine();
			System.out.print("Type the quantity: ");
			int productQuantity = isInteger(input);
			input.nextLine();
			if (!controller.checkProduct(productName, productQuantity))
				System.out.println("Product doesn't exist or product out of stock!");
			else {
				products.add(productName);
				products.add(productQuantity + "");
			}
			do {
				System.out.print("¿Want you add another product? (Y/N): ");
				selected = input.nextLine().toUpperCase().charAt(0);
				if (selected != 'Y' && selected != 'N') System.out.println("Type a valid letter.");
			} while (selected != 'Y' && selected != 'N');
		}
		int priceForm, totalPrice = 0;
		do {
			System.out.print("Do you want to enter the total price of the order (1) or delegate the responsibility to the system (2)?: ");
			priceForm = isInteger(input);
			if (priceForm == 1) {
				System.out.print("Enter the total price of the order: ");
				totalPrice = isInteger(input);
			}
			input.nextLine();
			if (priceForm != 1 && priceForm != 2) System.out.println("Error. Type a valid option.");
		} while (priceForm != 1 && priceForm != 2);
		System.out.println(priceForm == 1 ? controller.addOrder(buyerName, products, totalPrice) : controller.addOrder(buyerName, products, -1));
	}

	public void increaseProductQuantity() {
		System.out.print("Type the product name to witch increase: ");
		input.nextLine();
		String productName = input.nextLine();
		System.out.print("Type the amount to increase: ");
		int increaseAmount = isInteger(input);
		System.out.println(controller.increaseQuantity(productName, increaseAmount));
	}

	public void searchProduct() {
		System.out.print("""
				Choose a option:
				1) Search a specific product
				2) Search by range
				3) Search by interval
				Select:\s""");
		int option = isInteger(input);
		switch (option) {
			case 1 -> System.out.println(searchSpecificProduct());
			case 2 -> System.out.println(searchByRange());
			case 3 -> System.out.println(searchByInterval());
			default -> throw new RuntimeException("Error. Invalid search selection.");
		}
	}

	public String searchSpecificProduct() {
		System.out.print("""
				Choose a option:
				1) Search by product name
				2) Search by product price
				3) Search by times purchased
				4) Search by product category
				Select:\s""");
		int option = isInteger(input);
		switch (option) {
			case 1 -> {
				System.out.print("Type product name to search: ");
				input.nextLine();
				String productName = input.nextLine();
				return controller.searchProduct(productName);
			}
			case 2 -> {
				System.out.print("Type product price to search: ");
				double productPrice = isDouble(input);
				return controller.searchProduct(1, productPrice);
			}
			case 3 -> {
				System.out.print("Type times purchased to search: ");
				int timesPurchased = isInteger(input);
				return controller.searchProduct(2, timesPurchased);
			}
			case 4 -> {
				printCategoryOptions();
				int option2 = isInteger(input);
				return controller.searchProduct(3, option2);
			}
			default -> {
				return "Invalid option!";
			}
		}
	}

	public String searchByRange() {
		int option;
		do {
			System.out.print("""
					Choose a option:
					1) Search by product price
					2) Search by purchased times
					3) Search by available quantity
					Select:\s""");
			option = isInteger(input);
			if (option != 1 && option != 2 && option != 3) System.out.println("Error. Type a valid option.");
		} while (option != 1 && option != 2 && option != 3);
		System.out.print("Type the minimum value: ");
		double minValue = isDouble(input);
		System.out.print("Type the maximum value: ");
		double maxValue = isDouble(input);
		printSortOptions();
		int orderOption = isInteger(input);
		return switch (option) {
			case 1 -> controller.searchInRange(1, minValue, maxValue, orderOption, 2);
			case 2 -> controller.searchInRange(2, minValue, maxValue, orderOption, 4);
			case 3 -> controller.searchInRange(3, minValue, maxValue, orderOption, 5);
			default -> "Error. Invalid option!";
		};
	}

	public String searchByInterval() {
		System.out.print("Type the start prefix: ");
		input.nextLine();
		String startPrefix = input.nextLine();
		System.out.print("Type the final prefix: ");
		String finalPrefix = input.nextLine();
		printSortOptions();
		int orderOption = isInteger(input);
		return controller.searchInInterval(startPrefix, finalPrefix, orderOption);
	}

	public String searchOrder() {
		int option;
		do {
			System.out.print("""
					Choose a option to search:
					1) Search by buyer's name
					2) Search by total price
					3) Search by date
					Select:\s""");
			option = isInteger(input);
			if (option != 1 && option != 2 && option != 3) System.out.println("Error. Type a valid option.");
		} while (option != 1 && option != 2 && option != 3);
		switch (option) {
			case 1 -> {
				System.out.print("Type the order buyer's name to search: ");
				input.nextLine();
				String buyerName = input.nextLine();
				return controller.searchOrder(1, buyerName);
			}
			case 2 -> {
				System.out.print("Type the order total price: ");
				double totalPrice = isDouble(input);
				return controller.searchOrder(totalPrice);
			}
			case 3 -> {
				System.out.print("Type the order date (in format 'yyyy-MM-dd HH:mm:ss'): ");
				input.nextLine();
				String date = input.nextLine();
				return controller.searchOrder(2, date);
			}
			default -> {
				return "Invalid option!";
			}
		}
	}

	public void printCategoryOptions() {
		System.out.print("""
				Choose a category:
				 1) Books
				 2) Electronic
				 3) Apparel and accessories
				 4) Foods and beverages
				 5) Stationary
				 6) Sports
				 7) Beauty
				 8) Toys
				Selected:\s""");
	}

	public void printSortOptions() {
		System.out.print("""
				Choose order sort:
				1) Ascending order
				2) Descending order
				Option:\s""");
	}

	public void closeProgram() {
		System.out.println("\nThanks for using the program. Until next time!");
		controller.save();
	}

	public static Integer isInteger(Scanner input) {
		if (input.hasNextInt()) {
			return input.nextInt();
		} else {
			System.out.print("Invalid input! try again: ");
			input.next();
			return isInteger(input);
		}
	}

	public static Double isDouble(Scanner input) {
		if (input.hasNextDouble()) {
			return input.nextDouble();
		} else {
			System.out.print("Invalid input! try again: ");
			input.next();
			return isDouble(input);
		}
	}
}