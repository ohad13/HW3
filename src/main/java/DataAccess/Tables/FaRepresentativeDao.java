package DataAccess.Tables;


import DataAccess.DBConnector;
import Domain.Users.FArepresentative;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class FaRepresentativeDao{
    public FArepresentative get(String id) {
        return null;
    }

    public HashMap<String, FArepresentative> getAll() {
        HashMap<String, FArepresentative> faReps = new HashMap<String, FArepresentative>();
        String sql = "SELECT * FROM users JOIN FaRepresentatives ON users.username = FaRepresentatives.username";

        try {
            Connection conn = DBConnector.getConnection();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                String userName = rs.getString("username");
                String name = rs.getString("name");
                String pass = rs.getString("password");
                faReps.put(userName,new FArepresentative(name,userName,pass));
            }

            try {
                if (conn != null) {
                    conn.close();

                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return faReps;
    }
//
//    @Override
//    public void save(Object o) throws SQLException {
//
//    }
//
//    @Override
//    public void update(Object o, String[] params) {
//
//    }
//
//    @Override
//    public void delete(Object o) {
//
//    }
}
