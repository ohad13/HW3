package Domain;

//import java.sql.Date;
import java.util.ArrayList;
import java.util.Date;

public class Season {
    Date startDate;
    String SeasonID;
    ArrayList<TeamSeason> teamsInSeason;
    ArrayList<SeasonLeague> leaguesInSeason;

    public Season(String seasonID, Date startDate) {
        this.startDate = startDate;
        this.SeasonID = seasonID;
        this.teamsInSeason = new ArrayList<TeamSeason>();
        this.leaguesInSeason = new ArrayList<SeasonLeague>();
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getSeasonID() {
        return SeasonID;
    }

    public void addTeamToSeason(TeamSeason ts) {
        if (!teamsInSeason.contains(ts)) {
            teamsInSeason.add(ts);
        }
    }

    public void addSeasonLeague(SeasonLeague newSL) {
        String[] ids = newSL.getId().split("_");
        if (!ids[0].equals(this.getSeasonID()))
            return;
        if (leaguesInSeason.contains(newSL))
            return;
        leaguesInSeason.add(newSL);
    }

    public ArrayList<TeamSeason> getTeamsInSeason() {
        return teamsInSeason;
    }

    public ArrayList<SeasonLeague> getLeaguesInSeason() {
        return leaguesInSeason;
    }
}
