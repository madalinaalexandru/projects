package presentation;

import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.Product;

public class ProductTable extends JTable {
	
	static DefaultTableModel model;
	
	ProductTable() throws IllegalArgumentException, IllegalAccessException
	{
		Product product = new Product();
		Vector<String> columns = GUI.getFields(product);
		
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        this.setModel(model);
	}
	
	public static void addProduct(Product product)
	{
		Object[] row = new Object[4];
		row[0] = product.getProductId();
		row[1] = product.getPrice();
		row[2] = product.getProductName();
		row[3] = product.getStock();
		model.addRow(row);
	}
}
