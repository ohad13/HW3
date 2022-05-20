package Domain.Users;



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

    public boolean availableDate(Date date) {
        return !dates.contains(date);
    }

    public boolean setDate(Date date) {
        if (availableDate(date)) {
            dates.add(date);
            return true;
        }
        return false;
    }

    public boolean addGame(String gameID) {
        if (!games.contains(gameID)) {
            games.add(gameID);
            return true;
        }
        return false;
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
    @Override
    public boolean isFa() {
        return false;
    }
}
