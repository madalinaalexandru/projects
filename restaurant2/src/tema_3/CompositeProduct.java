package tema_3;

import java.util.*;

public class CompositeProduct extends MenuItem {

	private ArrayList<MenuItem> compositeP;
	
	public CompositeProduct(String name) {
		this.name = name;
		this.price = 0;
		this.compositeP = new ArrayList<MenuItem>();
	}
	
	public float computePrice() {
		
		float price = 0;
		
		for(int i = 0; i < this.compositeP.size(); i++) {
			price += this.compositeP.get(i).getPrice();
		}
		
		this.price = price;
		return this.price;
	}
	
	public void add(MenuItem item) {
		
		this.compositeP.add(item);
		this.computePrice();
	}
	
	public String getName() {
		return this.name;
	}
	
	public float getPrice() {
		return this.price;
	}
	
	public ArrayList<MenuItem> getCompositeP() {
		return compositeP;
	}

	public void setCompositeP(ArrayList<MenuItem> compositeP) {
		this.compositeP = compositeP;
	}

	public void setName(String name) {
		this.name = name;
	}
}
