package Domain;

import Domain.Users.Coach;
import Domain.Users.Player;
import Domain.Users.TeamManger;
import Domain.Users.TeamOwner;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class TeamSeason {
    String id;
    ArrayList<Game> games;
    Set<Date> dates;
    Team relatedTeam;
    Season relatedSeason;
    ArrayList<Player> players;
    TeamOwner owner;
    TeamManger manger;
    Coach coach;
    String personalPageID;

    public TeamSeason(String id,Season relatedSeason, Team relatedTeam) {
        this.id = id;
        this.games = new ArrayList<Game>();
        this.dates = new HashSet<Date>();
        this.relatedTeam = relatedTeam;
        this.relatedSeason = relatedSeason;
    }

    public boolean availableDate(Date date){
        return !dates.contains(date);
    }
    public void setDate(Date date){
        dates.add(date);
    }
    public void addGame(Game game){
        games.add(game);
    }

    public String getField() {
        return this.relatedTeam.getFieldID();
    }

    public String getId() {
        return id;
    }


    public ArrayList<Game> getGames() {
        return games;
    }

    public Set<Date> getDates() {
        return dates;
    }

    public Team getRelatedTeam() {
        return relatedTeam;
    }
}
