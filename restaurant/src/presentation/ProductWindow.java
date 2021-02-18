package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import dao.ProductDAO;
import model.Product;

public class ProductWindow {

	static ProductTable productTable;
	
	public ProductWindow() throws IllegalArgumentException, IllegalAccessException{
		
		JFrame frame = new JFrame("Products");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 400);
		JPanel panel = new JPanel();
		
		JPanel productPanel = new JPanel();
		productTable = new ProductTable();
		JScrollPane productScroll = new JScrollPane(productTable);
		JLabel products = new JLabel("PRODUCTS");
		
		ArrayList<Product> listOfProducts = ProductDAO.selectAll();
		
		loadProducts(productTable);
		
		JPanel productInfoPanel = new JPanel();
		
		JLabel productId = new JLabel("ID = ");
		JLabel productName = new JLabel("Name = ");
		JLabel productPrice = new JLabel("Price = ");
		JLabel productStock = new JLabel("Number of products left = ");
		
		JTextField inProductId = new JTextField(10);
		JTextField inProductName = new JTextField(10);
		JTextField inProductPrice = new JTextField(10);
		JTextField inProductStock = new JTextField(10);
		
		JPanel productOptions = new JPanel();
		
		JButton findproduct = new JButton("Find");
		
		findproduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Product p = new Product();
				
				int id = Integer.parseInt(inProductId.getText());
				
				p = ProductDAO.findById(id);
				
				removeAllRows(productTable);
				
				productTable.addProduct(p);
				
			}
		});
		
		JButton addProduct = new JButton("Add");
		
		addProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.parseInt(inProductId.getText());
				String name = new String(inProductName.getText());
				double price = Double.parseDouble(inProductPrice.getText());
				int stock = Integer.parseInt(inProductStock.getText());
				
				Product product = new Product(id, price, name, stock);
				
				ProductDAO.insert(product);
				
				removeAllRows(productTable);
				
				loadProducts(productTable);
			}
		});
		
		JButton removeProduct = new JButton("Remove");
		
		removeProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.parseInt(inProductId.getText());
				
				ProductDAO.delete(id);
				
				removeAllRows(productTable);
				
				loadProducts(productTable);
			}
		});
		
		JButton updateProductData = new JButton("Update");
		
		updateProductData.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.parseInt(inProductId.getText());
				String name = new String(inProductName.getText());
				double price = Double.parseDouble(inProductPrice.getText());
				int stock = Integer.parseInt(inProductStock.getText());
				
				Product product = new Product(id, price, name, stock);
				
				ProductDAO.update(id, product);
				
				removeAllRows(productTable);
				
				loadProducts(productTable);
			}
		});
		
		JButton listProducts = new JButton("List all");
		
		listProducts.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				removeAllRows(productTable);
				
				loadProducts(productTable);
			}
		});
		
		productOptions.add(findproduct);
		productOptions.add(addProduct);
		productOptions.add(removeProduct);
		productOptions.add(updateProductData);
		productOptions.add(listProducts);
		
		productInfoPanel.add(productId);
		productInfoPanel.add(inProductId);
		productInfoPanel.add(productName);
		productInfoPanel.add(inProductName);
		productInfoPanel.add(productPrice);
		productInfoPanel.add(inProductPrice);
		productInfoPanel.add(productStock);
		productInfoPanel.add(inProductStock);
		
		productPanel.add(products);
		productPanel.add(productScroll);
		productPanel.add(productInfoPanel);
		productPanel.add(productOptions);
		
		productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
		
		panel.add(productPanel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		frame.setContentPane(panel);
		frame.setVisible(true);
	}
	
	private static void loadProducts(ProductTable productTable){
		
		ArrayList<Product> allProducts = ProductDAO.selectAll();
		
		for(Product p : allProducts)
		{
			productTable.addProduct(p);
		}
	}
	
	private static void removeAllRows(JTable jtable){
		
		DefaultTableModel dm = (DefaultTableModel) jtable.getModel();
		
		for (int i = dm.getRowCount() - 1; i >= 0; i--) {
		    dm.removeRow(i);
		}
	}

	public static ProductTable getProductTable() {
		return productTable;
	}

	public static void setProductTable(ProductTable productTable) {
		ProductWindow.productTable = productTable;
	}
}
