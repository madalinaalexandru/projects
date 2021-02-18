package tema_3;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainClass {

	public static void main(String[] args) {
		

		Restaurant r = new Restaurant();
		ChefGUI chef = new ChefGUI();
		r.addObserver(chef);
		AdministratorGUI admin = new AdministratorGUI(r);
		WaiterGUI waiter = new WaiterGUI(r);
		
	}

}
