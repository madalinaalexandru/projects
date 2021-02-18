package model;

public class Product extends Object {

	int productId;
	double price;
	String productName;
	int stock;
	
	public Product() {
		super();
		this.productId = 0;
		this.price = 0;
		this.productName = null;
		this.stock = 0;
	}
	
	public Product(int id, double price, String name, int stock) {
		super();
		this.price = price;
		this.productId = id;
		this.productName = name;
		this.stock = stock;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Product [id=" + productId + ", name=" + productName + ", price=" + price + ", stock=" + stock + "]";
	}
}
