package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.ClientDAO;
import model.Client;

public class ClientWindow {
	
	static ClientTable clientTable;
	
	public ClientWindow() throws IllegalArgumentException, IllegalAccessException{
		JFrame frame = new JFrame("Clients");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 400);
		JPanel panel = new JPanel();
		
		JPanel clientPanel = new JPanel();
		clientTable = new ClientTable();
		JScrollPane clientScroll = new JScrollPane(clientTable);
		JLabel clients = new JLabel("CLIENTS");
		
		ArrayList<Client> listOfClients = ClientDAO.selectAll();
		
		for(Client c : listOfClients)
		{
			clientTable.addClient(c);
		}
		
		JPanel clientInfoPanel = new JPanel();
		
		JLabel clientId = new JLabel("ID = ");
		JLabel clientName = new JLabel("Name = ");
		JLabel clientAddress = new JLabel("Address = ");
		JLabel clientEmail = new JLabel("Email = ");
		JLabel clientPhoneNb = new JLabel("Phone number = ");
		
		JTextField inClientId = new JTextField(10);
		JTextField inClientName = new JTextField(10);
		JTextField inClientAddress = new JTextField(10);
		JTextField inClientEmail = new JTextField(10);
		JTextField inClientPhoneNb = new JTextField(10);
		
		JPanel clientOptions = new JPanel();
		
		JButton findClient = new JButton("Find");
		
		findClient.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Client c = new Client();
				
				int id = Integer.parseInt(inClientId.getText());
				
				c = ClientDAO.findById(id);
				
				removeAllRows(clientTable);
				
				clientTable.addClient(c);
				
			}
		});
		
		JButton addClient = new JButton("Add");
		
		addClient.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.parseInt(inClientId.getText());
				String name = new String(inClientName.getText());
				String address = new String(inClientAddress.getText());
				String email = new String(inClientEmail.getText());
				String phoneNb = new String(inClientPhoneNb.getText());
				
				Client client = new Client(id, name, address, email, phoneNb);
				
				ClientDAO.insert(client);
				
				removeAllRows(clientTable);
				
				loadClients(clientTable);
			}
		});
		
		JButton removeClient = new JButton("Remove");
		
		removeClient.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.parseInt(inClientId.getText());
				
				ClientDAO.delete(id);
				
				removeAllRows(clientTable);
				
				loadClients(clientTable);
			}
		});
		
		JButton updateClientData = new JButton("Update");
		
		updateClientData.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.parseInt(inClientId.getText());
				String name = new String(inClientName.getText());
				String address = new String(inClientAddress.getText());
				String email = new String(inClientEmail.getText());
				String phoneNb = new String(inClientPhoneNb.getText());
				
				Client client = new Client(id, name, address, email, phoneNb);
				
				ClientDAO.update(id, client);
				
				removeAllRows(clientTable);
				
				loadClients(clientTable);
			}
		});
		
		JButton listClients = new JButton("List all");
		
		listClients.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				removeAllRows(clientTable);
				
				loadClients(clientTable);
			}
		});
		
		clientOptions.add(findClient);
		clientOptions.add(addClient);
		clientOptions.add(removeClient);
		clientOptions.add(updateClientData);
		clientOptions.add(listClients);
		
		clientInfoPanel.add(clientId);
		clientInfoPanel.add(inClientId);
		clientInfoPanel.add(clientName);
		clientInfoPanel.add(inClientName);
		clientInfoPanel.add(clientAddress);
		clientInfoPanel.add(inClientAddress);
		clientInfoPanel.add(clientEmail);
		clientInfoPanel.add(inClientEmail);
		clientInfoPanel.add(clientPhoneNb);
		clientInfoPanel.add(inClientPhoneNb);
		
		clientPanel.add(clients);
		clientPanel.add(clientScroll);
		clientPanel.add(clientInfoPanel);
		clientPanel.add(clientOptions);
		
		clientPanel.setLayout(new BoxLayout(clientPanel, BoxLayout.Y_AXIS));
		
		panel.add(clientPanel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		frame.setContentPane(panel);
		frame.setVisible(true);
	}
	
	private static void loadClients(ClientTable clientTable){
		
		ArrayList<Client> allClients = ClientDAO.selectAll();
		
		for(Client c : allClients)
		{
			clientTable.addClient(c);
		}
	}
	
	private static void removeAllRows(JTable jtable){
		
		DefaultTableModel dm = (DefaultTableModel) jtable.getModel();
		
		for (int i = dm.getRowCount() - 1; i >= 0; i--) {
		    dm.removeRow(i);
		}
	}
}
