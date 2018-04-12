package Dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.ConnectionFactory;
import Models.Istoric;
import Models.Order;

public class OrderDAO
{

    protected static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO orderr (total, id_user,adress, payment, timp)" + " VALUES (?,?,?,?,?)";
    private final static String findStatementString = "SELECT * FROM orderr where id_order = ?";
    private final static String findByIdUserStatementString = "SELECT * FROM orderr where id_user = ?";
    private static final String updateStatementString = "UPDATE orderr SET total=? WHERE id_order=?";
    public static Order findOrderById(int id_Order)
    {
        Order toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try{
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, id_Order);
            rs = findStatement.executeQuery();
            rs.next();

            int total = rs.getInt("total");
            int id_user = rs.getInt("id_user");
            String adress = rs.getString("adress");
            String payment = rs.getString("payment");
            Timestamp time = rs.getTimestamp("timp");
            toReturn = new Order(id_Order, adress, payment, total, id_user, time);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"OrderDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
    public static ArrayList<Order> findOrderByIdUser(int id_user)
    {
        ArrayList <Order> list = new ArrayList <Order>();
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement showStatement = null;
        try {
            showStatement = dbConnection.prepareStatement(findByIdUserStatementString);
            showStatement.setInt(1, id_user);
            ResultSet rs = showStatement.executeQuery();
            Order order;
            while(rs.next())
            {
                order = new Order(rs.getInt("id_order"),rs.getString("adress"),rs.getString("payment"),rs.getInt("total"),rs.getInt("id_user"), rs.getTimestamp("timp"));
                list.add(order);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:show " + e.getMessage());
        } finally {
            ConnectionFactory.close(showStatement);
            ConnectionFactory.close(dbConnection);
        }
        return list;
    }
    public static int insert(Order order)
    {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, order.getTotal());
            insertStatement.setInt(2, order.getId_user());
            insertStatement.setString(3, order.getAdress());
            insertStatement.setString(4, order.getPayment());
            insertStatement.setTimestamp(5, order.getTime());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:create " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }
/*
    public static void update(Order order)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString);
            updateStatement.setInt(1, order.getTotal());
            updateStatement.setInt(2, order.getId());

            updateStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
    }*/
}

