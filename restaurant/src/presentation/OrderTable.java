package presentation;

import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.Order;

public class OrderTable extends JTable {
	
	static DefaultTableModel model;
	
	OrderTable() throws IllegalArgumentException, IllegalAccessException
	{
		Order order = new Order();
		Vector<String> columns = GUI.getFields(order);
		
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        this.setModel(model);
	}
	
	public static void addOrder(Order order)
	{
		Object[] row = new Object[5];
		row[0] = order.getOrderId();
		row[1] = order.getClientId();
		row[2] = order.getProductId();
		row[3] = order.getFinalPrice();
		row[4] = order.getQuantity();
		model.addRow(row);
	}
}
