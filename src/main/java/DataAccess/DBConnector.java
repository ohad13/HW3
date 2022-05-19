package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnector {

    private DBConnector() {}
    //private constructor to avoid client applications to use constructor
    public static DBConnector getInstance(){
        return instance;
    }
    private static final DBConnector instance = new DBConnector();



    /**
     * Connect to a sample database
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:C:/sqlite/hw3Imp.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
            return conn;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}