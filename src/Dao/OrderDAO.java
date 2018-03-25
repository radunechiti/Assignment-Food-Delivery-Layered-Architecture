package Dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.ConnectionFactory;
import Models.Order;
import org.joda.time.DateTime;

public class OrderDAO
{

    protected static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO orderr (total, id_user,adress, payment, timp)" + " VALUES (?,?,?,?,?)";
    private final static String findStatementString = "SELECT * FROM orderr where id_order = ?";
    private static final String updateStatementString = "UPDATE orderr SET total=? WHERE id_order=?";
    //private static final String showAllStatementString = "SELECT * FROM Client";
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
            Date data = rs.getDate("timp");
            toReturn = new Order(id_Order, adress, total, id_user, data, payment);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"OrderDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
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
            insertStatement.setDate(5, (Date) order.getDate());
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
    }/*
	public static ArrayList<Client> getClientList()
	{
		ArrayList <Client> list = new ArrayList <Client>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement showStatement = null;
		try {
			showStatement = dbConnection.prepareStatement(showAllStatementString);
			ResultSet rs = showStatement.executeQuery();
			Client client;
			while(rs.next())
			{
				client = new Client(rs.getInt("id_Client"),rs.getString("Name"),rs.getString("Surname"),rs.getString("Email"),rs.getLong("Telephone"));
				list.add(client);
			}

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:show " + e.getMessage());
		} finally {
			ConnectionFactory.close(showStatement);
			ConnectionFactory.close(dbConnection);
		}
		return list;
	}*/
}

