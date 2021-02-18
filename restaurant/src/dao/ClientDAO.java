package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Client;

public class ClientDAO {

	private final static String findStatementString = "SELECT * FROM client where id = ?";
	private static final String insertStatementString = "INSERT INTO client (id,name,address,email,phone_number)" + " VALUES (?,?,?,?,?)";
	private static final String deleteStatementString = "DELETE FROM client where id = ?";
	private static final String updateStatementString = "UPDATE client SET id = ?, name = ?, address = ?, email = ?, phone_number = ? where id = ?";
	private static final String selectAll = "SELECT * FROM client";
	
	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	
	public static Client findById(int clientId) {
		Client toReturn = null;
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1,  clientId);
			rs = findStatement.executeQuery();
			rs.next();
			
			String name = rs.getString("name");
			String address = rs.getString("address");
			String email = rs.getString("email");
			String phoneNb = rs.getString("phone_number");
			toReturn = new Client(clientId, name, address, email, phoneNb);
		}
		catch(SQLException e) {
			LOGGER.log(Level.WARNING,"StudentDAO:findById " + e.getMessage());
		}
		finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return toReturn;
	}
	
	public static int insert(Client client) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, client.getId());
			insertStatement.setString(2, client.getName());
			insertStatement.setString(3, client.getAddress());
			insertStatement.setString(4, client.getEmail());
			insertStatement.setString(5, client.getPhoneNb());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
	
	public static void delete(int clientId) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement deleteStatement = null;

		try {
			deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
			deleteStatement.setInt(1, clientId);
			deleteStatement.execute();
	
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	
	public static void update(int clientId, Client client) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement updateStatement = null;
		
		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
			updateStatement.setInt(1, client.getId());
			updateStatement.setString(2, client.getName());
			updateStatement.setString(3, client.getAddress());
			updateStatement.setString(4, client.getEmail());
			updateStatement.setString(5, client.getPhoneNb());
			updateStatement.setInt(6, clientId);
			updateStatement.executeUpdate();
	
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	
	public static ArrayList<Client> selectAll(){
		ArrayList<Client> allClients = new ArrayList<Client>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet rs = null;
		
		try {
			selectStatement = dbConnection.prepareStatement(selectAll, Statement.RETURN_GENERATED_KEYS);
			rs = selectStatement.executeQuery();
			
			 while(rs.next()){
				 allClients.add(new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));				
			  }
	
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:select " + e.getMessage());
		} finally {
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
			ConnectionFactory.close(rs);
		}
		
		return allClients;
	}
}
