package tema_3;

import java.io.Serializable;

public abstract class MenuItem implements Serializable{

	public String name;
	public float price;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	abstract float computePrice();
}
