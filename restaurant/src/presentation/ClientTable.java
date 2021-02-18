package presentation;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Client;

public class ClientTable extends JTable {

	static DefaultTableModel model;
	
	ClientTable() throws IllegalArgumentException, IllegalAccessException
	{
		Client client = new Client();
		Vector<String> columns = GUI.getFields(client);
		
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        this.setModel(model);
	}
	
	public static void addClient(Client client)
	{
		Object[] row = new Object[5];
		row[0] = client.getId();
		row[1] = client.getName();
		row[2] = client.getAddress();
		row[3] = client.getEmail();
		row[4] = client.getPhoneNb();
		model.addRow(row);
	}
	
}
