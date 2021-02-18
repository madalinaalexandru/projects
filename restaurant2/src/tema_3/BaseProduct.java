package tema_3;

public class BaseProduct extends MenuItem {

	public BaseProduct(String name, float price) {
		this.name = name;
		this.price = price;
	}
	
	public void setName(String name) {
		
		this.name = name;
	}
	
	public void setPrice(float price) {
		
		this.price = price;
	}
	
	public String getName() {
		return this.name;
	}
	
	public float getPrice() {
		return this.price;
	}
	
	public float computePrice() {
		
		return this.getPrice();
	}
}
