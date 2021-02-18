package tema_3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class Restaurant extends Observable implements Serializable{

	private ArrayList<MenuItem> menu;
	private HashMap<Order, ArrayList<MenuItem>> orders;
	
	public Restaurant(){
	
		try {
	         FileInputStream fileIn = new FileInputStream("menu.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         this.menu = (ArrayList<MenuItem>) in.readObject();
	         in.close();
	         fileIn.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	         return;
	      } catch (ClassNotFoundException c) {
	         System.out.println("not found");
	         c.printStackTrace();
	         return;
	      }
	
		this.orders = new HashMap<Order, ArrayList<MenuItem>>();
	}

	public ArrayList<MenuItem> getMenu() {
		return menu;
	}
	
	public void changed()
	{
		setChanged();
	}

	public void setMenu(ArrayList<MenuItem> menu) {
		this.menu = menu;
	}

	public HashMap<Order, ArrayList<MenuItem>> getOrders() {
		return orders;
	}

	public void setOrders(HashMap<Order, ArrayList<MenuItem>> orders) {
		this.orders = orders;
	}
	
	public void addProduct(MenuItem m, Order o) {
		ArrayList<MenuItem> arr = orders.get(o);
		arr.add(m);
		orders.put(o, arr);
	}
}
