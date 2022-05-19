package Domain;

import Domain.Users.*;

import java.util.ArrayList;
import java.util.Date;

public class Game {
    public String id;
    ArrayList<Referee> referees;
    java.util.Date date;
    TeamSeason home;
    TeamSeason away;
    String seasonLeague;
    String field;
    EventLogger gameLogger;

    public void setDate(Date date) {
        this.date = date;
    }

    public Game(String id, Date date, TeamSeason home, TeamSeason away, String seasonLeague, String field) {
        // can't create game with the same team(season)
        if (home.equals(away)) {
            return;
        }
        this.id = id;
        this.date = date;
        this.home = home;
        this.away = away;
        this.seasonLeague = seasonLeague;
        this.field = field;
        this.referees = new ArrayList<Referee>();
    }

    public Date getDate() {
        return date;
    }

    public boolean setReferee(Referee ref) {
        if (referees.size() < 3 && !referees.contains(ref)) {
            referees.add(ref);
            return true;
        }
        return false;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Referee> getReferees() {
        return referees;
    }

    public TeamSeason getHome() {
        return home;
    }

    public TeamSeason getAway() {
        return away;
    }

    public String getSeasonLeague() {
        return seasonLeague;
    }

    public String getField() {
        return field;
    }
}

