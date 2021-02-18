package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.ClientDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import model.Client;
import model.Order;
import model.Product;

public class OrderWindow {

	static OrderTable orderTable;
	
	public OrderWindow() throws IllegalArgumentException, IllegalAccessException{
		
		JFrame frame = new JFrame("Orders");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 400);
		JPanel panel = new JPanel();
		
		JPanel orderPanel = new JPanel();
		orderTable = new OrderTable();
		JScrollPane orderScroll = new JScrollPane(orderTable);
		JLabel orders = new JLabel("ORDERS");
		
		ArrayList<Order> listOfOrders = OrderDAO.selectAll();
		
		loadOrders(orderTable);
		
		JPanel orderInfoPanel = new JPanel();
		
		JLabel orderId = new JLabel("ID = ");
		JLabel orderQuantity = new JLabel("Quantity = ");
		JLabel orderProduct = new JLabel("Product = ");
		JLabel orderClient = new JLabel("Client = ");
		
		JTextField inOrderId = new JTextField(10);
		JTextField inOrderQuantity = new JTextField(10);
		JTextField inOrderProduct = new JTextField(10);
		JTextField inOrderClient = new JTextField(10);
		
		JPanel orderOptions = new JPanel();
		
		JButton findorder = new JButton("Find");
		
		findorder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Order p = new Order();
				
				int id = Integer.parseInt(inOrderId.getText());
				
				p = OrderDAO.findById(id);
				
				removeAllRows(orderTable);
				
				orderTable.addOrder(p);
				
			}
		});
		
		JButton addOrder = new JButton("Add");
		
		addOrder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.parseInt(inOrderId.getText());
				int quantity = Integer.parseInt(inOrderQuantity.getText());
				int product = Integer.parseInt(inOrderProduct.getText());
				int client = Integer.parseInt(inOrderClient.getText());
				
				Product p = ProductDAO.findById(product);
				
				Order order = new Order(id, client, quantity, p);
				
				if(p.getStock() < quantity) {
					System.out.println("Not enough products in stock: Could not place order");
				}
				else {
					OrderDAO.insert(order);
					
					removeAllRows(orderTable);
					
					loadOrders(orderTable);
					
					p.setStock(p.getStock() - quantity);
					ProductDAO.update(p.getProductId(), p);
					
					DefaultTableModel dm = (DefaultTableModel) ProductWindow.getProductTable().getModel();
					
					for (int i = dm.getRowCount() - 1; i >= 0; i--) {
					    dm.removeRow(i);
					}
					
					ArrayList<Product> allProducts = ProductDAO.selectAll();
					
					for(Product pr : allProducts)
					{
						ProductWindow.getProductTable().addProduct(pr);
					}
				}
			}
		});
		
		JButton removeOrder = new JButton("Remove");
		
		removeOrder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.parseInt(inOrderId.getText());
				
				OrderDAO.delete(id);
				
				removeAllRows(orderTable);
				
				loadOrders(orderTable);
			}
		});
		
		JButton updateOrderData = new JButton("Update");
		
		updateOrderData.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.parseInt(inOrderId.getText());
				int quantity = Integer.parseInt(inOrderQuantity.getText());
				int product = Integer.parseInt(inOrderProduct.getText());
				int client = Integer.parseInt(inOrderClient.getText());
				
				Order o = OrderDAO.findById(id); 
				Product pPrev = ProductDAO.findById(o.getProductId());
				
				pPrev.setStock(pPrev.getStock() + o.getQuantity());
				ProductDAO.update(pPrev.getProductId(), pPrev);
				
				Product p = ProductDAO.findById(product);
				
				Order order = new Order(id, client, quantity, p);
				
				if(p.getStock() < quantity) {
					System.out.println("Not enough products in stock: Could not place order");
				}
				else {
					OrderDAO.update(id, order);
					
					removeAllRows(orderTable);
					
					loadOrders(orderTable);
					
					p.setStock(p.getStock() - quantity);
					ProductDAO.update(p.getProductId(), p);
					
					DefaultTableModel dm = (DefaultTableModel) ProductWindow.getProductTable().getModel();
					
					for (int i = dm.getRowCount() - 1; i >= 0; i--) {
					    dm.removeRow(i);
					}
					
					ArrayList<Product> allProducts = ProductDAO.selectAll();
					
					for(Product pr : allProducts)
					{
						ProductWindow.getProductTable().addProduct(pr);
					}
				}
			}
		});
		
		JButton listOrders = new JButton("List all");
		
		listOrders.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				removeAllRows(orderTable);
				
				loadOrders(orderTable);
			}
		});
		
		JButton bill = new JButton("Finalize order");
		
		bill.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.parseInt(inOrderId.getText());
				
				Order o = OrderDAO.findById(id);
				Product p = ProductDAO.findById(o.getProductId());
				Client c = ClientDAO.findById(o.getClientId());
				
				FileWriter fileWriter = null;
				
				try {
					fileWriter = new FileWriter("bill.txt");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			    PrintWriter printWriter = new PrintWriter(fileWriter);
				printWriter.append("BILL:\r\nProduct name: " + p.getProductName() + " x " + o.getQuantity() + " -> TOTAL = " + o.getFinalPrice() + "\r\nOrdered by: " + c.getName());
				
				OrderDAO.delete(id);
				
				removeAllRows(orderTable);
				loadOrders(orderTable);
				
				printWriter.close();
			}
		});
		
		orderOptions.add(findorder);
		orderOptions.add(addOrder);
		orderOptions.add(removeOrder);
		orderOptions.add(updateOrderData);
		orderOptions.add(bill);
		orderOptions.add(listOrders);
		
		orderInfoPanel.add(orderId);
		orderInfoPanel.add(inOrderId);
		orderInfoPanel.add(orderQuantity);
		orderInfoPanel.add(inOrderQuantity);
		orderInfoPanel.add(orderProduct);
		orderInfoPanel.add(inOrderProduct);
		orderInfoPanel.add(orderClient);
		orderInfoPanel.add(inOrderClient);
		
		orderPanel.add(orders);
		orderPanel.add(orderScroll);
		orderPanel.add(orderInfoPanel);
		orderPanel.add(orderOptions);
		
		orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
		
		panel.add(orderPanel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		frame.setContentPane(panel);
		frame.setVisible(true);
	}
	
	private static void loadOrders(OrderTable orderTable){
		
		ArrayList<Order> allOrders = OrderDAO.selectAll();
		
		for(Order p : allOrders)
		{
			orderTable.addOrder(p);
		}
	}
	
	private static void removeAllRows(JTable jtable){
		
		DefaultTableModel dm = (DefaultTableModel) jtable.getModel();
		
		for (int i = dm.getRowCount() - 1; i >= 0; i--) {
		    dm.removeRow(i);
		}
	}
}
