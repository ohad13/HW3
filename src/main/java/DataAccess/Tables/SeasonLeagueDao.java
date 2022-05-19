package DataAccess.Tables;


import DataAccess.DBConnector;
import Domain.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class SeasonLeagueDao{
    public SeasonLeague get(String id) {
        return null;
    }

    public HashMap<String, SeasonLeague> getAll(HashMap<String, Game> gameMap, HashMap<String, Season> seasonMap, HashMap<String,League> leagueMap) {
        HashMap<String, SeasonLeague> seasonLeagues = new HashMap<String, SeasonLeague>();
        String sql = "SELECT * FROM leagueInSeason";

        try {
            Connection conn = DBConnector.getConnection();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                String seasonID = rs.getString("leagueID");
                String leagueID = rs.getString("seasonID");
                String policyType = rs.getString("policyType");
                String seasonLeagueKey = leagueID + "_" + seasonID;

                // get league and season from DB

                League newL = leagueMap.get(leagueID);
                Season newS = seasonMap.get(seasonID);

                AssignmentPolicy policy = new AssignmentPolicy(policyType); // have!
                SeasonLeague newSL = new SeasonLeague(seasonLeagueKey,newS,newL,policy);
                seasonLeagues.put(seasonLeagueKey,newSL);

                newS.addSeasonLeague(newSL);
            }

            for(String gameID : gameMap.keySet()){
                Game game = gameMap.get(gameID);
                seasonLeagues.get(game.getSeasonLeague()).setGame(game);
            }

            try {
                if (conn != null) {
                    conn.close();

                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return seasonLeagues;
    }
//
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
