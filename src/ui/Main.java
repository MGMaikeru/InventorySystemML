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
		Main m = new Main();
		m.displayStartMenu();
	}

	public void displayStartMenu() {
		System.out.print("1)Register product \n2)Register new order \n3)Increase quantity of registered product \n4)Search product " +
				"\n5)Search order \n6)Exit \nSelected: ");
		int option = isInteger(input);
		executeOption(option);
	}

	public void executeOption(int option) {
		switch (option) {
			case 1:
				registerProduct();
				break;
			case 2:
				registerOrder();
				break;
			case 3:
				increaseProductQuantity();
				break;
			case 4:
				searchProduct();
				break;
			case 5:
				System.out.println(searchOrder());
				break;
			case 6:
				System.out.println("\nThanks for using the program. Until next time!");
				break;
			default:
				System.out.println("Error. Type a valid option.\n");
				break;
		}
		if (option != 6)
			displayStartMenu();

	}

	public void registerProduct(){
		System.out.println("Type product name: ");
		input.nextLine();
		String productName = input.nextLine();
		System.out.println("Type product description: ");
		String description = input.nextLine();
		System.out.println("Type product price (if the price is not integer, use , for the decimals): ");
		double price = isDouble(input);
		System.out.println("Type available quantity: ");
		int availableQuantity = isInteger(input);
		System.out.println("Choose a category:\n 1)Books\n 2)Electronic\n 3)Apparel and accessories\n 4)Foods and beverages" +
				"\n 5)Stationary\n 6)Sports\n 7)Beauty\n 8)Toys \nSelected: ");
		int productCategory = isInteger(input);

		System.out.println(controller.addProduct(productName, description, price, availableQuantity, productCategory, 0));
	}

	public void registerOrder() {
		System.out.println("Type buyer's name: ");
		input.nextLine();
		String buyerName = input.nextLine();
		String isContinue = "Y";
		ArrayList<String> products = new ArrayList<>();
		while (isContinue.equals("Y")) {
			System.out.println("Type product: ");
			String productName = input.nextLine();
			System.out.println("Type quantity: ");
			int productQuantity = isInteger(input);
			if(!controller.checkProduct(productName, productQuantity))
				System.out.println("Product doesn't exist or product out of stock!");
			else
				products.add(productName);
				products.add("" + productQuantity);
			System.out.println("¿Want you add another product? Y/N");
			input.nextLine();
			isContinue = input.nextLine();
		}
		System.out.println(controller.addOrder(buyerName, products));
	}

	public void increaseProductQuantity(){
		System.out.println("Type the product name to witch increase: ");
		input.nextLine();
		String productName = input.nextLine();
		System.out.println("Type the amount to increase: ");
		int increaseAmount = isInteger(input);
		System.out.println(controller.increaseQuantity(productName, increaseAmount));
	}

	public void searchProduct(){
		System.out.println("Choose a option: \n1)Search a specific product \n2)Search by range \n3)Search by interval \nSelect: ");
		int option = isInteger(input);
		switch (option){
			case 1 -> System.out.println(searchSpecificProduct());
			case 2 -> System.out.println(searchByRange());
			case 3 -> System.out.println(searchByInterval());
		}
	}

	public String searchSpecificProduct(){
		System.out.println("\nChoose a option: \n1)Search by product name \n2)Search by times purchased \n3)Search by product price  \n4)Search by product category \nSelect: ");
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
				System.out.println("Choose a category:\n 1)Books\n 2)Electronic\n 3)Apparel and accessories\n 4)Foods and beverages" +
						"\n 5)Stationary\n 6)Sports\n 7)Beauty\n 8)Toys \nSelected: ");
				int option2 = isInteger(input);
				return controller.searchProduct(option2);
			}
			default -> {
				return "Invalid option!";
			}
		}
	}

	public String searchByRange(){
		System.out.println("\nChoose a option: \n1)Search by product price \n2)Search by purchased times \n3)Search by available quantity  \nSelect: ");
		int option = isInteger(input);
		System.out.println("Type the minimum value: ");
		double minValue = isDouble(input);
		System.out.println("Type the maximum value: ");
		double maxValue = isDouble(input);
		System.out.println("Choose order sort: 1)Descending order 2)Ascending order");
		int orderOption = isInteger(input);
		return switch (option){
			case 1 -> controller.searchInRange(1, minValue, maxValue, orderOption, 2);
			case 2 -> controller.searchInRange(2, minValue, maxValue, orderOption, 4);
			case 3 -> controller.searchInRange(3, minValue, maxValue, orderOption, 5);
			default -> "Invalid option!";
		};
	}

	public String searchByInterval(){
		System.out.println("Type the start prefix: ");
		input.nextLine();
		String startPrefix = input.nextLine();
		System.out.println("Type the final prefix: ");
		String finalPrefix = input.nextLine();
		System.out.println("Choose order sort: 1)Descending order 2)Ascending order");
		int orderOption = isInteger(input);
		return controller.searchInInterval(startPrefix, finalPrefix, orderOption);
	}

	public String searchOrder(){
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
				try {
					return controller.searchOrder(date, hour);
				} catch (ParseException e) {
					e.getMessage();
				}
			}
			default -> {
				return "Invalid option!";
			}
		}
		return null;
	}

	public static Integer isInteger(Scanner input) {
		if (input.hasNextInt()) {
			return input.nextInt();
		} else {
			System.out.println("Invalid input! try again:");
			input.next();
			return isInteger(input);
		}
	}

	public static Double isDouble(Scanner input) {
		if (input.hasNextDouble()) {
			return input.nextDouble();
		} else {
			System.out.println("Invalid input! try again:");
			input.next();
			return isDouble(input);
		}
	}
}