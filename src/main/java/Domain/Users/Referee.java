package Domain.Users;

//
//import src.Domain.Event;
//import src.Domain.Game;
//
//import java.sql.Time;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.Set;

import Domain.Event;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Referee extends Subscriber {
    ArrayList<String> games;
    Set<Date> dates;
    ArrayList<Event> events;

    public Referee(String name, String userName, String password) {
        super(name, userName, password);
        games = new ArrayList<String>();
        dates = new HashSet<java.util.Date>();
    }

    public boolean availableDate(java.util.Date date) {
        return !dates.contains(date);
    }

    public boolean setDate(java.util.Date date) {
        if (availableDate(date)) {
            dates.add(date);
            return true;
        }
        return false;
    }

    public void addGame(String gameID) {
        if (!games.contains(gameID)) {
            games.add(gameID);
        }
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<String> getGames() {
        return games;
    }

    public Set<java.util.Date> getDates() {
        return dates;
    }
}
