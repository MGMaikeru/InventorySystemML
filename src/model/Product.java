public class Product {

	private String productName;
	private String description;
	private int quanAvailable;
	private int price;
	private int purchases;

	public Product(String productName, String description, int quanAvailable, int price) {
		this.productName = productName;
		this.description = description;
		this.quanAvailable = quanAvailable;
		this.price = price;
		this.purchases = 0;
	}

	public int getPrice() {
		return price;
	}

	public int getQuanAvailable() {
		return quanAvailable;
	}

	public void setQuanAvailable(int quanAvailable) {
		this.quanAvailable = quanAvailable;
	}
}