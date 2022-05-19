package Domain;


import Domain.Users.FArepresentative;

import java.util.ArrayList;

public class SeasonLeague {
    String id;
    Season season;
    League league;
    ArrayList<Game> games;
    ArrayList<FArepresentative> FAs;
    AssignmentPolicy AssignmentPolicy;
    PointPolicy pointPolicy;
    ArrayList<TeamSeason> teamsInSeason;

    public AssignmentPolicy getPolicy() {
        return AssignmentPolicy;
    }

    public SeasonLeague(String seasonLeagueKey, Season season, League league, AssignmentPolicy AssignmentPolicy) {
        this.games = new ArrayList<Game>();
        this.id = seasonLeagueKey;
        this.season = season;
        this.league = league;
        this.AssignmentPolicy = AssignmentPolicy;
        this.FAs = new ArrayList<FArepresentative>(); // RELATED FA's TO SEASON LEAGUE
        this.teamsInSeason = new ArrayList<TeamSeason>(); // todo: check if needed
    }

    public Season getSeason() {
        return season;
    }

    public League getLeague() {
        return league;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    public void setGame(Game game) {
        this.games.add(game);
    }

    public String getId() {
        return id;
    }
}
