package Dao;

import Models.User;
import java.util.ArrayList;
import java.util.logging.Logger;
import Connection.ConnectionFactory;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.lang.String;

public class UserDAO {

    protected static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO user (email, pass, nume, active, loyal)" + " VALUES (?,?,?,?,?)";
    private final static String findStatementString = "SELECT * FROM user where email = ? and pass = ?";
    private static final String deleteStatementString = "UPDATE user SET active=? WHERE id_user=?";
    private static final String updateStatementString = "UPDATE user SET email=?, pass=?, nume=?, active=?,loyal=? WHERE id_user=?";
    private static final String showAllStatementString = "SELECT * FROM user";

    public static User findUser(User user)
    {
        User toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try{
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setString(1, user.getEmail());
            findStatement.setString(2, user.getPassword());
            rs = findStatement.executeQuery();
            rs.next();
            int id_user = rs.getInt("id_user");
            String email = rs.getString("email");
            String password = rs.getString("pass");
            String nume = rs.getString("nume");
            boolean active = rs.getBoolean("active");
            boolean loyal = rs.getBoolean("loyal");
            toReturn = new User(id_user, password, email, nume, active, loyal);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"UserDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
    public static int insertUser(User user)
    {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, user.getEmail());
            insertStatement.setString(2, user.getPassword());
            insertStatement.setString(3, user.getNume());
            insertStatement.setBoolean(4, true);
            insertStatement.setBoolean(5, false);

            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "UserDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }
    public static void deleteUser(User user)
    {
        {
            Connection dbConnection = ConnectionFactory.getConnection();
            PreparedStatement deleteStatement = null;

            try {
                deleteStatement = dbConnection.prepareStatement(deleteStatementString);
                deleteStatement.setBoolean(1, false);
                deleteStatement.setInt(2, user.getId());

                deleteStatement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "UserDAO:delete " + e.getMessage());
            } finally {
                ConnectionFactory.close(deleteStatement);
                ConnectionFactory.close(dbConnection);
            }
        }
    }
    public static void updateUser(User user)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString);
            updateStatement.setString(1, user.getEmail());
            updateStatement.setString(2, user.getPassword());
            updateStatement.setString(3, user.getNume());
            updateStatement.setBoolean(4, user.getActive());
            updateStatement.setBoolean(5, user.getLoyal());
            updateStatement.setInt(6, user.getId());

            updateStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "UserDAO:update " + e.getMessage());
            JOptionPane.showMessageDialog(null, "This email is already used");
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
    public static ArrayList<User> getUsers()
    {
        ArrayList <User> users = new ArrayList <User>();
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement showStatement = null;
        try {
            showStatement = dbConnection.prepareStatement(showAllStatementString);
            ResultSet rs = showStatement.executeQuery();

            while(rs.next())
            {
                User user = new User(rs.getInt("id_user"),rs.getString("pass"),rs.getString("email"),rs.getString("nume"),rs.getBoolean("active"), rs.getBoolean("loyal"));
                users.add(user);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:show " + e.getMessage());
        } finally {
            ConnectionFactory.close(showStatement);
            ConnectionFactory.close(dbConnection);
        }
        return users;
    }
}
