package DataAccess.Tables;

import DataAccess.DBConnector;
import Domain.Season;
import Domain.Team;
import Domain.TeamSeason;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class TeamSeasonDao{

    public HashMap<String, TeamSeason> getAll(HashMap<String, Season> seasonMap, HashMap<String,Team> teamsMap){

        HashMap<String, TeamSeason> teamSeasonMap = new HashMap<String, TeamSeason>();
        try {
            Connection conn = DBConnector.getConnection();

            String sqlTeams = "SELECT * FROM teamSeason";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlTeams);
            while (rs.next()) {
                String relatedTeamID = rs.getString("relatedTeamID");
                String teamSeasonID = rs.getString("teamSeasonID");
                String relatedSeasonID = rs.getString("relatedSeasonID");

                // create team
                Team relatedTeam = teamsMap.get(relatedTeamID);
                Season relatedSeason = seasonMap.get(relatedSeasonID);

                TeamSeason ts = new TeamSeason(teamSeasonID,relatedSeason,relatedTeam);
                teamSeasonMap.put(ts.getId(), ts);

                // add teamSeason to their season
                seasonMap.get(relatedSeasonID).addTeamToSeason(ts);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return teamSeasonMap;
    }
}
