package DataAccess.Tables;


import DataAccess.DBConnector;
import Domain.Season;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class SeasonDao {
//
//    public Season get(String id) {
//        try {
//            Connection conn = DBConnector.getConnection();
//            // create league and season from DB
//            String sqlLeague = "SELECT * FROM seasons WHERE id = "+id;
//            Statement stmtLeague = conn.createStatement();
//            ResultSet rsLeague = stmtLeague.executeQuery(sqlLeague);
//            while (rsLeague.next()) {
//                String seasonID = rsLeague.getString("id");
//                String startDate = rsLeague.getString("startDate");
//                Date parsedDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
//
//                Season newS = new Season(seasonID,parsedDate);
//                return newS;
//            }
//
//        } catch (SQLException | ParseException throwables) {
//            throwables.printStackTrace();
//        }
//        return null;
//    }

    public HashMap<String, Season> getAll() {
        HashMap<String, Season> seasons = new HashMap<String, Season>();
        try {
        Connection conn = DBConnector.getConnection();
        // create league and season from DB
        String sqlLeague = "SELECT * FROM seasons";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlLeague);
            while (rs.next()) {
                String seasonID = rs.getString("id");
                String startDate = rs.getString("startDate");
                Date parsedDate = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(startDate);

                Season newS = new Season(seasonID, parsedDate);
                seasons.put(seasonID,newS);
            }
        } catch (SQLException throwables) {
        throwables.printStackTrace();
        } catch (ParseException throwables) {
        throwables.printStackTrace();
        }
        return seasons;
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

