package Dao;


import Models.Istoric;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Connection.ConnectionFactory;
import Models.Order;

public class IstoricDAO {
    protected static final Logger LOGGER = Logger.getLogger(IstoricDAO.class.getName());
    private final static String findCurrentStatementString = "SELECT * FROM istoric where id_user=?";
    private static final String insertStatementString = "INSERT INTO istoric (id_user, id_order)" + " VALUES (?,?)";

    public static ArrayList<Istoric> getIstoricByIdUser(int id_user)
    {
        ArrayList <Istoric> list = new ArrayList <Istoric>();
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement showStatement = null;
        try {
            showStatement = dbConnection.prepareStatement(findCurrentStatementString);
            showStatement.setInt(1, id_user);
            ResultSet rs = showStatement.executeQuery();
            Istoric istoric;
            while(rs.next())
            {
                istoric = new Istoric(rs.getInt("id_istoric"),rs.getInt("id_user"),rs.getInt("id_order"));
                list.add(istoric);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:show " + e.getMessage());
        } finally {
            ConnectionFactory.close(showStatement);
            ConnectionFactory.close(dbConnection);
        }
        return list;
    }
    public static void insert(Istoric istoric)
    {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, istoric.getId_User());
            insertStatement.setInt(2, istoric.getId_Order());

            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "IstoricDAO:create " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
       // return insertedId;
    }
}
