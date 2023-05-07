package ui;

import model.Controller;

import java.text.ParseException;
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
		} catch (RuntimeException | ParseException e) {
			System.err.println(e.getMessage());
		}
		if (option != 6) displayStartMenu();
	}

	public void registerProduct() {
		System.out.print("Type product name: ");
		input.nextLine();
		String productName = input.nextLine();
		System.out.println("Type product description: ");
		String description = input.nextLine();
		System.out.print("Type product price (if the price is not integer use ',' for the decimals): ");
		double price = isDouble(input);
		System.out.print("Type the available quantity: ");
		int availableQuantity = isInteger(input);
		System.out.print("""
				Choose a category:
				 1) Books
				 2) Electronic
				 3) Apparel and accessories
				 4) Foods and beverages
				 5) Stationary
				 6) Sports
				 7) Beauty
				 8) Toys\s
				Selected:\s""");
		int productCategory = isInteger(input);
		System.out.println(controller.addProduct(productName, description, price, availableQuantity, productCategory, 0));
	}

	public void registerOrder() {
		System.out.print("Type buyer's name: ");
		input.nextLine();
		String buyerName = input.nextLine();
		String isContinue = "Y";
		ArrayList<String> products = new ArrayList<>();
		while (isContinue.equals("Y")) {
			System.out.println("Type product: ");
			String productName = input.nextLine();
			System.out.println("Type quantity: ");
			int productQuantity = isInteger(input);
			if (!controller.checkProduct(productName, productQuantity))
				System.out.println("Product doesn't exist or product out of stock!");
			else products.add(productName);
			products.add(productQuantity + "");
			System.out.println("¿Want you add another product? Y/N");
			input.nextLine();
			isContinue = input.nextLine();
		}
		System.out.println(controller.addOrder(buyerName, products));
	}

	public void increaseProductQuantity() {
		System.out.println("Type the product name to witch increase: ");
		input.nextLine();
		String productName = input.nextLine();
		System.out.println("Type the amount to increase: ");
		int increaseAmount = isInteger(input);
		System.out.println(controller.increaseQuantity(productName, increaseAmount));
	}

	public void searchProduct() {
		System.out.println("""
				Choose a option:\s
				1)Search a specific product\s
				2)Search by range\s
				3)Search by interval\s
				Select:\s""");
		int option = isInteger(input);
		switch (option) {
			case 1 -> System.out.println(searchSpecificProduct());
			case 2 -> System.out.println(searchByRange());
			case 3 -> System.out.println(searchByInterval());
		}
	}

	public String searchSpecificProduct() {
		System.out.print("""
				Choose a option:\s
				1)Search by product name\s
				2)Search by times purchased\s
				3)Search by product price \s
				4)Search by product category\s
				Select:\s""");
		int option = isInteger(input);
		switch (option) {
			case 1 -> {
				System.out.println("Type product name to search: ");
				input.nextLine();
				String productName = input.nextLine();
				return controller.searchProduct(productName);
			}
			case 2 -> {
				System.out.println("Type times purchased to search: ");
				double timesPurchased = isInteger(input);
				return controller.searchProduct(2, timesPurchased);
			}
			case 3 -> {
				System.out.println("Type product price to search: ");
				double productPrice = isDouble(input);
				return controller.searchProduct(1, productPrice);
			}
			case 4 -> {
				System.out.println("""
						Choose a category:
						 1)Books
						 2)Electronic
						 3)Apparel and accessories
						 4)Foods and beverages
						 5)Stationary
						 6)Sports
						 7)Beauty
						 8)Toys\s
						Selected:\s""");
				int option2 = isInteger(input);
				return controller.searchProduct(option2);
			}
			default -> {
				return "Invalid option!";
			}
		}
	}

	public String searchByRange() {
		System.out.println("""
				Choose a option:\s
				1)Search by product price\s
				2)Search by purchased times\s
				3)Search by available quantity \s
				Select:\s""");
		int option = isInteger(input);
		System.out.println("Type the minimum value: ");
		double minValue = isDouble(input);
		System.out.println("Type the maximum value: ");
		double maxValue = isDouble(input);
		System.out.println("""
				Choose order sort:\s
				1) Descending order\s
				2) Ascending order
				Option:\s""");
		int orderOption = isInteger(input);
		return switch (option) {
			case 1 -> controller.searchInRange(1, minValue, maxValue, orderOption, 2);
			case 2 -> controller.searchInRange(2, minValue, maxValue, orderOption, 4);
			case 3 -> controller.searchInRange(3, minValue, maxValue, orderOption, 5);
			default -> "Invalid option!";
		};
	}

	public String searchByInterval() {
		System.out.println("Type the start prefix: ");
		input.nextLine();
		String startPrefix = input.nextLine();
		System.out.println("Type the final prefix: ");
		String finalPrefix = input.nextLine();
		System.out.println("""
				Choose order sort:\s
				1) Descending order\s
				2) Ascending order
				Option:\s""");
		int orderOption = isInteger(input);
		return controller.searchInInterval(startPrefix, finalPrefix, orderOption);
	}

	public String searchOrder() throws ParseException {
		System.out.println("\nChoose a option to search: \n1)Search by buyer's name \n2)Search by total price \n3)Search by date \nSelect: ");
		int option = isInteger(input);
		switch (option) {
			case 1 -> {
				System.out.println("Type order buyer's name to search: ");
				input.nextLine();
				String buyerName = input.nextLine();
				return controller.searchOrder(buyerName);
			}
			case 2 -> {
				System.out.println("Type order total price: ");
				double totalPrice = isDouble(input);
				return controller.searchOrder(totalPrice);
			}
			case 3 -> {
				System.out.println("Type order date (yyyy-MM-dd): ");
				input.nextLine();
				String date = input.nextLine();
				System.out.println("Type order hour (HH:mm): ");
				String hour = input.nextLine();
				return controller.searchOrder(date, hour);
			}
			default -> {
				return "Invalid option!";
			}
		}
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
			input.nextLine();
			return isInteger(input);
		}
	}

	public static Double isDouble(Scanner input) {
		if (input.hasNextDouble()) {
			return input.nextDouble();
		} else {
			System.out.print("Invalid input! try again: ");
			input.nextLine();
			return isDouble(input);
		}
	}
}