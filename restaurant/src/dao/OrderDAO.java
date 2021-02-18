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
import model.Order;

public class OrderDAO {

	private final static String findStatementString = "SELECT * FROM order_table where orderid = ?";
	private static final String insertStatementString = "INSERT INTO order_table (orderid,final_price,quantity,client_id,product_id)" + " VALUES (?,?,?,?,?)";
	private static final String deleteStatementString = "DELETE FROM order_table where orderid = ?";
	private static final String updateStatementString = "UPDATE order_table SET orderid = ?, final_price = ?, quantity = ?, client_id = ?, product_id = ? where orderid = ?";
	private static final String selectAll = "SELECT * FROM order_table";
	
	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	
	public static Order findById(int orderId) {
		Order toReturn = null;
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1,  orderId);
			rs = findStatement.executeQuery();
			rs.next();
			
			double total = rs.getDouble("final_price");
			int quantity = rs.getInt("quantity");
			int clientId = rs.getInt("client_id");
			int productId = rs.getInt("product_id");
			toReturn = new Order(orderId, clientId, quantity, productId, total);
		}
		catch(SQLException e) {
			LOGGER.log(Level.WARNING,"OrderDAO:findById " + e.getMessage());
		}
		finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return toReturn;
	}
	
	public static int insert(Order order) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, order.getOrderId());
			insertStatement.setDouble(2, order.getFinalPrice());
			insertStatement.setInt(3, order.getQuantity());
			insertStatement.setInt(4, order.getClientId());
			insertStatement.setInt(5, order.getProductId());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "OrderDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
	
	public static void delete(int orderId) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement deleteStatement = null;

		try {
			deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
			deleteStatement.setInt(1, orderId);
			deleteStatement.execute();
	
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "OrderDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	
	public static void update(int orderId, Order order) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement updateStatement = null;
		
		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
			updateStatement.setInt(1, order.getOrderId());
			updateStatement.setDouble(2, order.getFinalPrice());
			updateStatement.setInt(3, order.getQuantity());
			updateStatement.setInt(4, order.getClientId());
			updateStatement.setInt(5, order.getProductId());
			updateStatement.setInt(6, orderId);
			updateStatement.executeUpdate();
	
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "OrderDAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	
	public static ArrayList<Order> selectAll(){
		ArrayList<Order> allOrders = new ArrayList<Order>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet rs = null;
		
		try {
			selectStatement = dbConnection.prepareStatement(selectAll, Statement.RETURN_GENERATED_KEYS);
			rs = selectStatement.executeQuery();
			
			 while(rs.next()){
				 allOrders.add(new Order(rs.getInt(1), rs.getInt(4), rs.getInt(3), rs.getInt(5), rs.getDouble(2)));				
			  }
	
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "OrderDAO:select " + e.getMessage());
		} finally {
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
			ConnectionFactory.close(rs);
		}
		
		return allOrders;
	}
}
