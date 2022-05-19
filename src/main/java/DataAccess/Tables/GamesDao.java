package DataAccess.Tables;

import DataAccess.DBConnector;
import Domain.Game;
import Domain.TeamSeason;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class GamesDao{

    public Game get(String id) {
//        try {
//            Connection conn = DBConnector.getConnection();
//            // create league and season from DB
//            String sqlLeague = "SELECT * FROM game";
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
        return null;
    }

    public HashMap<String,Game> getAll(HashMap<String, TeamSeason> teamMap) {

        HashMap<String,Game> res = new HashMap<String, Game>();
        try {
            Connection conn = DBConnector.getConnection();
            String all_games_sql = "SELECT * FROM games";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(all_games_sql);
            while (rs.next()) {
                String gameID = rs.getString("gameID");
                String date = rs.getString("date");
                String home = rs.getString("teamHome");
                String away = rs.getString("teamAway");
                String field = rs.getString("field");
                String seasonLeagueID = rs.getString("seasonLeagueID");

                Date parsedDate = null;
                if (date != null) {
                    parsedDate = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy").parse(date);
                }

                Game newGame = new Game(gameID, (Date) parsedDate, teamMap.get(home), teamMap.get(away), seasonLeagueID, field);
                res.put(gameID,newGame);

                // add games to the teams
                teamMap.get(home).addGame(newGame);
                teamMap.get(away).addGame(newGame);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ParseException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

    public void updateGamesDB(HashMap<String, Game> gameMap){
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            for(Game game : gameMap.values()){

                String date = "NULL";
                if(game.getDate() != null) {
                    date = "\""+game.getDate().toString()+"\"";
                }



                String field = "NULL";
                if(game.getField() != null){
                    field = game.getField();
                }

                String sql = String.format("INSERT OR REPLACE INTO games (gameID,date,teamHome,teamAway,field,seasonLeagueID) VALUES(%s,%s,%s,%s,%s,\"%s\")",
                        game.getId(),date,game.getHome().getId(),game.getAway().getId(),field,game.getSeasonLeague());
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(sql);

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
