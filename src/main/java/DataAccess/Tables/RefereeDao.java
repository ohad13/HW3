package DataAccess.Tables;


import DataAccess.DBConnector;
import Domain.Users.Referee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class RefereeDao{
    public Referee get(String id) {
        return null;
    }

    public HashMap<String,Referee> getAll() {
        HashMap<String,Referee> referees = new HashMap<String, Referee>();
        String sql = "SELECT * FROM users JOIN referees ON users.usersUsername = referees.username";

        try {
            Connection conn = DBConnector.getConnection();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                String refUserName = rs.getString("usersUsername");
                String refName = rs.getString("name");
                String refPass = rs.getString("password");
                referees.put(refUserName,new Referee(refName,refUserName,refPass));
            }
                String game_history_sql = "SELECT * FROM refereesInGames";
                stmt = conn.createStatement();
                rs  = stmt.executeQuery(game_history_sql);
                while (rs.next()) {
                    // read refereesInGames
                    // 1. read the last games assigned
                    // 2. add games to referees lists
                    String refID = rs.getString("refereeUsername");
                    String gameID = rs.getString("gameID");

                    referees.get(refID).addGame(gameID);
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

        return referees;
    }

    public void updateRefereeGameDB(HashMap<String, Referee> refereeMap) {
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            for(Referee ref : refereeMap.values()){
                ArrayList<String> refGames = ref.getGames();
                for(String gameID : refGames){
                    String sql = String.format("INSERT OR REPLACE INTO refereesInGames (gameID,refereeUsername) VALUES(%s,\"%s\")",gameID,ref.getUserName());
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate(sql);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
//
//    @Override
//    public void save(Referee referee) throws SQLException {
//        try {
//
//            Connection connection = DBConnector.getConnection();
//            Statement stmt = connection.createStatement();
//
//            String sql = "INSERT INTO referees " +
//                    "VALUES (" + referee.getName().hashCode() + ",'" + referee.getName() + "');";
//            System.out.println(sql);
//            stmt.executeUpdate(sql);
//        } catch (java.sql.SQLException e) {
//            System.out.println(e.toString());
//        }
//    }
//
//    @Override
//    public void update(Referee referee, String[] params) {
//
//    }
//
//    @Override
//    public void delete(Referee referee) {
//
//    }
}
