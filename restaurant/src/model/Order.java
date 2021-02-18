package model;

public class Order extends Object {

	int orderId;
	int clientId;
	int productId;
	double finalPrice;
	int quantity;
	
	public Order() {
		super();
		this.orderId = 0;
		this.clientId = 0;
		this.productId = 0;
		this.finalPrice = 0;
		this.quantity = 0;
	}
	
	public Order(int orderId, int clientId, int quantity, Product p) {
		super();
		this.orderId = orderId;
		this.clientId = clientId;
		this.quantity = quantity;
		this.productId = p.getProductId();
		
		for(int i = 0; i < quantity; i++) {
			this.finalPrice += p.getPrice();
		}
	}
	
	public Order(int orderId, int clientId, int quantity, int productId, double total) {
		super();
		this.orderId = orderId;
		this.clientId = clientId;
		this.quantity = quantity;
		this.productId = productId;
		this.finalPrice = total;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "Order [id=" + orderId + ", client ID=" + clientId + ", product ID=" + productId + ", quantity=" + quantity + ", final price=" + finalPrice + "]";
	}
}
