package Dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Bll.ProductValidators;
import Connection.ConnectionFactory;
import Models.Cos;
import Models.Product;
import Models.Order;

public class CosDAO
{

    protected static final Logger LOGGER = Logger.getLogger(CosDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO coss (id_product, id_order, quantity)" + " VALUES (?,?,?)";
    private final static String showCurrentStatementString = "SELECT * FROM coss where id_cos=?";
    private static final String showAllStatementString = "SELECT * FROM coss";
    public static ArrayList<Cos> findByOrder(int id_Order)
    {
        ArrayList <Cos> list = new ArrayList <Cos>();
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement showStatement = null;
        try {
            showStatement = dbConnection.prepareStatement(showCurrentStatementString);
            showStatement.setLong(1, id_Order);
            ResultSet rs = showStatement.executeQuery();
            Cos cos;
            while(rs.next())
            {
                cos = new Cos(rs.getInt("id_ProductOrder"),rs.getInt("id_Product"),rs.getInt("id_Order"),rs.getInt("Quantity"));
                list.add(cos);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:show " + e.getMessage());
        } finally {
            ConnectionFactory.close(showStatement);
            ConnectionFactory.close(dbConnection);
        }
        return list;
    }
    public static int insert(Cos cos)
    {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, cos.getId_product());
            insertStatement.setInt(2, cos.getId_order());
            insertStatement.setInt(3, cos.getQuantity());
            insertStatement.executeUpdate();

           // Product product = ProductValidators.findProduct(productOrder.getId_Product());
            //product.setQuantity(product.getQuantity()-productOrder.getQuantity());
            //ProductDAO.update(product);

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductOrderDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }
    public static ArrayList<Cos> getCosList()
    {
        ArrayList <Cos> list = new ArrayList <Cos>();
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement showStatement = null;
        try {
            showStatement = dbConnection.prepareStatement(showAllStatementString);
            ResultSet rs = showStatement.executeQuery();
            Cos cos;
            while(rs.next())
            {
                cos = new Cos(rs.getInt("id_ProductOrder"),rs.getInt("id_product"),rs.getInt("id_Order"),rs.getInt("Quantity"));
                list.add(cos);
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
