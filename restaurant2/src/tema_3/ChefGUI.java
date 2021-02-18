package tema_3;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

public class ChefGUI implements Observer{

	@Override
	public void update(Observable o, Object arg) {
		
		JOptionPane.showMessageDialog(null, "Chef notified of new order");
		
	}

}
