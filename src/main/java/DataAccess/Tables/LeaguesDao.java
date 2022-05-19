package DataAccess.Tables;

import DataAccess.DBConnector;
import Domain.League;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class LeaguesDao{

//    public League get(String id) {
//        try {
//            Connection conn = DBConnector.getConnection();
//            // create league and season from DB
//            String sqlLeague = "SELECT * FROM leagues WHERE id = "+id;
//            Statement stmtLeague = conn.createStatement();
//            ResultSet rsLeague = stmtLeague.executeQuery(sqlLeague);
//            while (rsLeague.next()) {
//                String sleagueID = rsLeague.getString("id");
//                String sleagueName = rsLeague.getString("name");
//                League newL = new League(sleagueID,sleagueName);
//                return newL;
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return null;
//    }

    public HashMap<String, League> getAll() {
        HashMap<String,League> res = new HashMap<String, League>();

        try {
            Connection conn = DBConnector.getConnection();
            // create league and season from DB
            String sqlLeague = "SELECT * FROM leagues";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlLeague);
            while (rs.next()) {
                String leagueID = rs.getString("id");
                String leagueName = rs.getString("name");
                League newL = new League(leagueID,leagueName);
                res.put(leagueID,newL);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

//    @Override
//    public HashMap getAll() {
//
//    }
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
