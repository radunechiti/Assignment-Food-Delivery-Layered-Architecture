package Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.ConnectionFactory;
import Models.Product;


import java.util.ArrayList;

public class ProductDAO {

    protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO product (nume, price, quantity)" + " VALUES (?,?,?)";
    private final static String findStatementString = "SELECT * FROM product where id_product = ?";
    private static final String deleteStatementString = "DELETE FROM product where id_product = ?";
    private static final String updateStatementString = "UPDATE product SET nume=?, price=?, quantity=? WHERE id_product=?";
    private static final String showAllStatementString = "SELECT * FROM product";

    public static Product findProduct(Product product)
    {
        Product toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try{
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setInt(1, product.getId());
            rs = findStatement.executeQuery();
            rs.next();

            String name = rs.getString("nume");
            int price = rs.getInt("price");
            int quantity = rs.getInt("quantity");
            toReturn = new Product(product.getId(), name, price, quantity);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
    public static int insertProduct(Product product)
    {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, product.getName());
            insertStatement.setInt(2, product.getPrice());
            insertStatement.setInt(3, product.getQuantity());

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
    public static void deleteProduct(Product product)
    {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement deleteStatement = null;

        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString);
            deleteStatement.setInt(1, product.getId());

            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
    public static void updateProduct(Product product)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString);
            updateStatement.setString(1, product.getName());
            updateStatement.setInt(2, product.getPrice());
            updateStatement.setInt(3, product.getQuantity());
            updateStatement.setInt(4, product.getId());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
    public static ArrayList<Product> getProducts()
    {
        ArrayList <Product> list = new ArrayList <Product>();
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement showStatement = null;
        try {
            showStatement = dbConnection.prepareStatement(showAllStatementString);
            ResultSet rs = showStatement.executeQuery();

            while(rs.next())
            {
                Product product = new Product(rs.getInt("id_Product"),rs.getString("nume"),rs.getInt("Price"),rs.getInt("Quantity"));
                list.add(product);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:show " + e.getMessage());
        } finally {
            ConnectionFactory.close(showStatement);
            ConnectionFactory.close(dbConnection);
        }
        return list;
    }
}

