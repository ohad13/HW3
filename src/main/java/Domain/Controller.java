package Domain;

import DataAccess.DataAccessController;
import Domain.Users.Admin;
import Domain.Users.Referee;
import Domain.Users.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

//import java.sql.Date;
import java.util.Random;
import java.util.*;

public class Controller {

    // todo: make private?
    HashMap<String, User> usersMap;
    HashMap<String, Referee> refereeMap;
    HashMap<String, Game> gameMap; //<game id,game>
    HashMap<String, SeasonLeague> seasonLeagues; //<season league id, season league>
    HashMap<String, TeamSeason> teamSeasonMap; // todo: might need to come for database
    HashMap<String, Team> teamsMap; // todo: might need to come for database
    HashMap<String, Admin> admins;
    SystemLogger logger;
    DataAccessController dataControl;

    private static Controller instance = null;

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public static Controller getInstance1() {
        instance = new Controller(1);// call for the second constructor.
        instance.usersMap = new HashMap<String, User>();
        instance.seasonLeagues = new HashMap<String, SeasonLeague>();
        instance.refereeMap = new HashMap<String, Referee>();
        return instance;
    }

    private Controller() {
        // get from db the data
        dataControl = DataAccessController.getInstance();
        this.refereeMap = dataControl.getRefereeMap();
        this.gameMap = dataControl.getGameMap();
        this.usersMap = new HashMap<String, User>();
        this.seasonLeagues = dataControl.getSeasonLeagueMap();
        this.usersMap = dataControl.getUsersMap();
    }

    private Controller(int x) {
    }

    public Game getGameById(String gameID) {
        return gameMap.getOrDefault(gameID, null);
    }

    public Referee getRefereeById(String refID) {
        return refereeMap.getOrDefault(refID, null);
    }

    public boolean assignRefereeToGame(Game game, Referee ref) {
        if (game == null || ref == null) {
            return false;
        }
        return game.setReferee(ref);
    }

    public boolean autoSignField(ArrayList<Game> games) {
        for (Game game : games
        ) {
            if (game.getField() != null) {
                continue;
            }
            game.setField(game.home.getField());
        }
        return true;
    }

    public boolean autoSignRefereeAndDates(ArrayList<Game> games, Date start, AssignmentPolicy policy) {
        int amountTime = 1;
        if ("random".equals(policy.getValue())) {// shuffle games
            Random rand = new Random();
            amountTime = rand.nextInt(4) + 1;
        } else if ("serial".equals(policy.getValue())) {}
        else {
            return false;
        }
        Date startCopy = (Date) start.clone();
        Calendar cal = Calendar.getInstance();
        cal.setTime(startCopy);
        for (Game game : games
        ) {
            try {
                // if this game already has date.
                if (game.getDate() != null) {
                    continue;
                }
                game.setDate(startCopy);// todo check !
                cal.add(Calendar.DAY_OF_MONTH, amountTime);
                startCopy =  cal.getTime();
            } catch (Exception e) {
                //e.printStackTrace();
                game.setDate(null);
                System.out.println("in error !!! ---------------------");
                //return false;
            }
        }
        // assign referees to games
        for (Game game : games
        ) {
            for (String refereeID : refereeMap.keySet()
            ) {
                Referee referee = refereeMap.get(refereeID);
                if (game.referees.size() < 3 && referee.availableDate(game.getDate())) {
                    game.setReferee(referee);
                    referee.setDate(game.getDate());
                    referee.addGame(game.id);
                    if (game.referees.size() == 3)
                        break;
                }
            }
        }
        return true;
    }

    /**
     * param- season: id of the season.
     * param- league: id of the league.
     * param- policy: assign according to this policy.
     * Goal - assign all games belong to this season-league according to the policy given.
     **/
    public boolean autoSignGames(String season, String league, AssignmentPolicy policy, boolean writeToDB) {
        String key = league + "_" + season;
        if (seasonLeagues.containsKey(key)) {
            SeasonLeague seasonLeague = seasonLeagues.get(key);
            ArrayList<Game> games = seasonLeague.getGames();
            Date d1 = seasonLeague.getSeason().getStartDate();
            boolean refAndDates = autoSignRefereeAndDates(games, d1, policy);
            boolean field = autoSignField(games);

            if (refAndDates && field && writeToDB) { // insert to DB the changes.
                dataControl.updateRefereeGamesDB(refereeMap);
                dataControl.updateGamesDB(gameMap);
                return true;
            }
        }
        // if there is no season-league in the system.
        return false;
    }

    public User login(String username, String password) {
        if (usersMap.containsKey(username) && usersMap.get(username).getPassword().equals(password)) {
            return usersMap.get(username);
        }
        return null;
    }

    // ----------------------------------------------------------------------------------------
    /* the next functions it used only by the testers !*/
    public void setUsers(String name, User user) {
        if (!usersMap.containsKey(name)) {
            usersMap.put(name, user);
        }
    }

    public void setSL(SeasonLeague SL) {
        String key = SL.getId();
        if (!seasonLeagues.containsKey(key)) {
            seasonLeagues.put(key, SL);
        }
    }

    public void setRef(Referee ref) {
        refereeMap.put(ref.getName(), ref);
    }
}