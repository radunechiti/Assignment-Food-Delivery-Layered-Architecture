package Dao;

import Models.Admin;
import Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Connection.ConnectionFactory;

public class AdminDAO {
    protected static final Logger LOGGER = Logger.getLogger(AdminDAO.class.getName());
    private final static String findStatementString = "SELECT * FROM admin where email = ? and pass = ?";

    public static Admin find(Admin admin)
    {
        Admin toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try{
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setString(1, admin.getEmail());
            findStatement.setString(2, admin.getPassword());
            rs = findStatement.executeQuery();
            rs.next();
            int id_admin = rs.getInt("id_admin");
            String email = rs.getString("email");
            String password = rs.getString("pass");
            toReturn = new Admin(id_admin, email, password);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"AdminDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
}
