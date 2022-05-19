package DataAccess.Tables;


import DataAccess.DBConnector;
import Domain.Team;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
public class TeamsDao{

//    public TeamSeason get(String id) {
//
//    }

    public HashMap<String, Team> getAll(){

        HashMap<String, Team> teamsMap = new HashMap<String, Team>();
        try {
            Connection conn = DBConnector.getConnection();

            String sqlTeams = "SELECT * FROM teams";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlTeams);
            while (rs.next()) {
                String teamID = rs.getString("id");
                String teamName = rs.getString("name");
                String teamField = rs.getString("field");

                // create team
                Team team = new Team(teamID,teamName, teamField);

                teamsMap.put(teamID, team);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return teamsMap;
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
