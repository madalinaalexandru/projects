package start;

import java.util.ArrayList;

import dao.ProductDAO;
import model.Product;
import presentation.*;

public class MainClass {

	public static void main(String[] args) {
		
		try {
			GUI g = new GUI();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
