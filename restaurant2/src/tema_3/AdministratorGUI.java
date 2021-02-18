package tema_3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdministratorGUI {
	
	public AdministratorGUI(Restaurant r) {
		
		JFrame frame = new JFrame("Administrator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1020, 700);
		
		JPanel panel = new JPanel();
		
		JTable table = new JTable();
		Vector<String> columns = new Vector<String>();
		columns.add("Name");
		columns.add("Price");
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		
		for(int i = model.getRowCount() - 1; i >= 0; i--)
		{
			model.removeRow(i);
		}
		
		for(MenuItem m : r.getMenu())
		{
			Vector<Object> row = new Vector<Object>();
			row.add(m.getName());
			row.add(m.getPrice());
			model.addRow(row);
		}

		JPanel addMenuItem = new JPanel();
		
		JPanel addBaseProduct = new JPanel(); 

		JLabel baseNameLabel = new JLabel("Name:");
		JLabel basePriceLabel = new JLabel("Price:");
		JTextField baseName = new JTextField(10);
		JTextField basePrice = new JTextField(10);
		
		JButton createBaseProduct = new JButton("Add Base Product");
		
		JButton editBaseProduct = new JButton("Edit");
		JButton deleteBaseProduct = new JButton("Delete");
		JTextField newBaseProductName = new JTextField(10);
		JTextField newBaseProductPrice = new JTextField(10);
		JLabel newNameBaseProduct = new JLabel("New name:");
		JLabel newPrice = new JLabel("New price:");
		
		addBaseProduct.add(baseNameLabel);
		addBaseProduct.add(baseName);
		addBaseProduct.add(basePriceLabel);
		addBaseProduct.add(basePrice);
		addBaseProduct.add(createBaseProduct);
		addBaseProduct.add(newNameBaseProduct);
		addBaseProduct.add(newBaseProductName);
		addBaseProduct.add(newPrice);
		addBaseProduct.add(newBaseProductPrice);
		addBaseProduct.add(editBaseProduct);
		addBaseProduct.add(deleteBaseProduct);
		addMenuItem.add(addBaseProduct);
		
		createBaseProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				float price = Float.parseFloat(basePrice.getText());
				String name = baseName.getText();
				
				MenuItem item = new BaseProduct(name, price);
				
				r.getMenu().add(item);
				
				for(int i = model.getRowCount() - 1; i >= 0; i--)
				{
					model.removeRow(i);
				}
				
				for(MenuItem m : r.getMenu())
				{
					Vector<Object> row = new Vector<Object>();
					row.add(m.getName());
					row.add(m.getPrice());
					model.addRow(row);
				}
			}
		});
		
		deleteBaseProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = baseName.getText();
				
				for(int i = 0; i < r.getMenu().size(); i++) {
					if(r.getMenu().get(i).getName().equals(name)) {
						r.getMenu().remove(i);
					}
				}
				
				for(int i = model.getRowCount() - 1; i >= 0; i--)
				{
					model.removeRow(i);
				}
				
				for(MenuItem m : r.getMenu())
				{
					Vector<Object> row = new Vector<Object>();
					row.add(m.getName());
					row.add(m.getPrice());
					model.addRow(row);
				}
			}
		});
		
		editBaseProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = baseName.getText();
				
				String newName = newBaseProductName.getText();
				float newPrice = Float.parseFloat(newBaseProductPrice.getText());
				
				for(int i = 0; i < r.getMenu().size(); i++) {
					if(r.getMenu().get(i).getName().equals(name)) {
						r.getMenu().get(i).setName(newName);
						r.getMenu().get(i).setPrice(newPrice);
					}
				}
				
				for(int i = model.getRowCount() - 1; i >= 0; i--)
				{
					model.removeRow(i);
				}
				
				for(MenuItem m : r.getMenu())
				{
					Vector<Object> row = new Vector<Object>();
					row.add(m.getName());
					row.add(m.getPrice());
					model.addRow(row);
				}
			}
		});
		
		JPanel addCompositeProduct = new JPanel(); 

		JLabel compositeNameLabel = new JLabel("Name:");
		JTextField compositeName = new JTextField(10);
		
		JButton createCompositeProduct = new JButton("Create Composite Product");
		
		JButton editCompositeProduct = new JButton("Edit");
		JButton deleteCompositeProduct = new JButton("Delete");
		JTextField newCompositeProductName = new JTextField(10);
		JLabel newNameCompositeProduct = new JLabel("New name:");
		JButton addToCompositeProduct = new JButton("Add"); 
		JLabel addToComp = new JLabel("Add to composite product:");
		JTextField addToComposite = new JTextField(10);
		
		addCompositeProduct.add(compositeNameLabel);
		addCompositeProduct.add(compositeName);
		addCompositeProduct.add(createCompositeProduct);
		addCompositeProduct.add(newNameCompositeProduct);
		addCompositeProduct.add(newCompositeProductName);
		addCompositeProduct.add(editCompositeProduct);
		addCompositeProduct.add(addToComp);
		addCompositeProduct.add(addToComposite);
		addCompositeProduct.add(addToCompositeProduct);
		addCompositeProduct.add(deleteCompositeProduct);
		
		addMenuItem.add(addCompositeProduct);
		
		createCompositeProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String name = compositeName.getText();
				
				MenuItem item = new CompositeProduct(name);
				
				r.getMenu().add(item);
				
				for(int i = model.getRowCount() - 1; i >= 0; i--)
				{
					model.removeRow(i);
				}
				
				for(MenuItem m : r.getMenu())
				{
					Vector<Object> row = new Vector<Object>();
					row.add(m.getName());
					row.add(m.getPrice());
					model.addRow(row);
				}
			}
		});
		
		editCompositeProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = compositeName.getText();
				
				String newName = newCompositeProductName.getText();
				
				for(int i = 0; i < r.getMenu().size(); i++) {
					if(r.getMenu().get(i).getName().equals(name)) {
						r.getMenu().get(i).setName(newName);
					}
				}
				
				for(int i = model.getRowCount() - 1; i >= 0; i--)
				{
					model.removeRow(i);
				}
				
				for(MenuItem m : r.getMenu())
				{
					Vector<Object> row = new Vector<Object>();
					row.add(m.getName());
					row.add(m.getPrice());
					model.addRow(row);
				}
			}
		});
		
		deleteCompositeProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = compositeName.getText();
				
				for(int i = 0; i < r.getMenu().size(); i++) {
					if(r.getMenu().get(i).getName().equals(name)) {
						r.getMenu().remove(i);
					}
				}
				
				for(int i = model.getRowCount() - 1; i >= 0; i--)
				{
					model.removeRow(i);
				}
				
				for(MenuItem m : r.getMenu())
				{
					Vector<Object> row = new Vector<Object>();
					row.add(m.getName());
					row.add(m.getPrice());
					model.addRow(row);
				}
			}
		});
		
		addToCompositeProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String cName = compositeName.getText();
				String name = addToComposite.getText();
				
				for(int i = 0; i < r.getMenu().size(); i++) {
					if(r.getMenu().get(i).getName().equals(name)) {
						for(int j = 0; i < r.getMenu().size(); j++) {
							if(r.getMenu().get(j).getName().equals(cName)) {
								((CompositeProduct)r.getMenu().get(j)).add(r.getMenu().get(i));
								break;
							}
						}
					}
				}
				
				for(int i = model.getRowCount() - 1; i >= 0; i--)
				{
					model.removeRow(i);
				}
				
				for(MenuItem m : r.getMenu())
				{
					Vector<Object> row = new Vector<Object>();
					row.add(m.getName());
					row.add(m.getPrice());
					model.addRow(row);
				}
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
		panel.add(addMenuItem);
		addMenuItem.setLayout(new BoxLayout(addMenuItem, BoxLayout.Y_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		frame.setContentPane(panel);
		frame.setVisible(true);
	}
}