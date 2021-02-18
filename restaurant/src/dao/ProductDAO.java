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
import model.Product;

public class ProductDAO {

	private final static String findStatementString = "SELECT * FROM product where product_id = ?";
	private static final String insertStatementString = "INSERT INTO product (product_id,price,product_name,stock)" + " VALUES (?,?,?,?)";
	private static final String deleteStatementString = "DELETE FROM product where product_id = ?";
	private static final String updateStatementString = "UPDATE product SET product_id = ?, price = ?, product_name = ?, stock = ? where product_id = ?";
	private static final String selectAll = "SELECT * FROM product";
	
	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	
	public static Product findById(int productId) {
		Product toReturn = null;
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1,  productId);
			rs = findStatement.executeQuery();
			rs.next();
			
			double price = rs.getDouble("price");
			String product_name = rs.getString("product_name");
			int stock = rs.getInt("stock");
			toReturn = new Product(productId, price, product_name, stock);
		}
		catch(SQLException e) {
			LOGGER.log(Level.WARNING,"ProductDAO:findById " + e.getMessage());
		}
		finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return toReturn;
	}
	
	public static int insert(Product product) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, product.getProductId());
			insertStatement.setDouble(2, product.getPrice());
			insertStatement.setString(3, product.getProductName());
			insertStatement.setInt(4, product.getStock());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
	
	public static void delete(int productId) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement deleteStatement = null;

		try {
			deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
			deleteStatement.setInt(1, productId);
			deleteStatement.execute();
	
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	
	public static void update(int productId, Product product) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement updateStatement = null;
		
		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
			updateStatement.setInt(1, product.getProductId());
			updateStatement.setDouble(2, product.getPrice());
			updateStatement.setString(3, product.getProductName());
			updateStatement.setInt(4, product.getStock());
			updateStatement.setInt(5, productId);
			updateStatement.executeUpdate();
	
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	
	public static ArrayList<Product> selectAll(){
		ArrayList<Product> allProducts = new ArrayList<Product>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet rs = null;
		
		try {
			selectStatement = dbConnection.prepareStatement(selectAll, Statement.RETURN_GENERATED_KEYS);
			rs = selectStatement.executeQuery();
			
			 while(rs.next()){
				 allProducts.add(new Product(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getInt(4)));				
			  }
	
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:select " + e.getMessage());
		} finally {
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
			ConnectionFactory.close(rs);
		}
		
		return allProducts;
	}
}
