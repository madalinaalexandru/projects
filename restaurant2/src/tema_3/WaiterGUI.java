package tema_3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;


import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class WaiterGUI {
	
	public WaiterGUI(Restaurant r) {
		JFrame frame = new JFrame("Waiter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1020, 700);
		
		JPanel panel = new JPanel();
		
		JTable table = new JTable();
		Vector<String> columns = new Vector<String>();
		columns.add("ID");
		columns.add("Date");
		columns.add("Table");
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		
		JButton newOrder = new JButton("New Order");
		JLabel id = new JLabel("ID:");
		JTextField idText= new JTextField(5);
		JLabel date = new JLabel("Date:");
		JTextField dateText= new JTextField(10);
		JLabel tab = new JLabel("Table:");
		JTextField tableText = new JTextField(5);
		
		JLabel product = new JLabel("Product:");
		JTextField inProduct = new JTextField(10);
		JButton addProduct = new JButton("Add product");
		JButton generateBill = new JButton("BILL");
		
		JPanel orders = new JPanel();
		JPanel products = new JPanel();
		
		orders.add(id);
		orders.add(idText);
		orders.add(date);
		orders.add(dateText);
		orders.add(tab);
		orders.add(tableText);
		orders.add(newOrder);
		products.add(product);
		products.add(inProduct);
		products.add(addProduct);
		
		newOrder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				r.changed();
				r.notifyObservers();
				
				int id = Integer.parseInt(idText.getText());
				
				String date = dateText.getText();
				int table = Integer.parseInt(tableText.getText());
				
				Order newOrder = new Order(id, date, table);
				r.getOrders().put(newOrder, new ArrayList<MenuItem>());
				
				for(int i = model.getRowCount() - 1; i >= 0; i--)
				{
					model.removeRow(i);
				}
				
				for(Order o : r.getOrders().keySet())
				{
					Vector<Object> row = new Vector<Object>();
					row.add(o.getId());
					row.add(o.getDate());
					row.add(o.getTable());
					model.addRow(row);
				}
			}
		});
		
		addProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.parseInt(idText.getText());
				String name = new String(inProduct.getText());
				Order x = null;
				MenuItem item = null;
				
				for(Order o : r.getOrders().keySet()){
					if(o.getId() == id) {
						x = o;
						break;
					}
				}
				//System.out.println(r.getMenu().get(0).getName());
				for(int i = 0; i < r.getMenu().size(); i++) {
					if(r.getMenu().get(i).getName().equals(name)) {
						item = r.getMenu().get(i);
						
						break;
					}
				}
				
				r.addProduct(item, x);
				
				for(int i = model.getRowCount() - 1; i >= 0; i--)
				{
					model.removeRow(i);
				}
				
				for(Order o : r.getOrders().keySet())
				{
					Vector<Object> row = new Vector<Object>();
					row.add(o.getId());
					row.add(o.getDate());
					row.add(o.getTable());
					model.addRow(row);
				}
			}
		});
		
		generateBill.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.parseInt(idText.getText());
				float finalPrice = 0;
				FileWriter fileWriter = null;
				
				try {
					fileWriter = new FileWriter("bill.txt");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    PrintWriter printWriter = new PrintWriter(fileWriter);
			    
				for(Order o : r.getOrders().keySet()){
					if(o.getId() == id) {
						
						printWriter.println("Order no: " + o.getId());
						
						for(MenuItem m : r.getOrders().get(o)) {
							printWriter.println("Product name: " + m.getName() + " " + "Price: " + m.getPrice());
							finalPrice += m.getPrice();
						}
						
						printWriter.println("Total: " + finalPrice);
						break;
					}
				}
				
				
			    printWriter.close();
			}
		});
		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	
				try {
					FileOutputStream fileOut = new FileOutputStream("menu.ser");
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeObject(r.getMenu());
					out.close();
					fileOut.close();
				} catch (IOException i) {
					i.printStackTrace();
				}
		    }
		});
		
		JScrollPane scroll = new JScrollPane(table);
		panel.add(scroll);
		panel.add(orders);
		panel.add(products);
		panel.add(generateBill);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		frame.setContentPane(panel);
		frame.setVisible(true);
	}
}
