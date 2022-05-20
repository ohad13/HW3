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

    HashMap<String, User> usersMap;
    HashMap<String, Referee> refereeMap;
    HashMap<String, Game> gameMap; //<game id,game>
    HashMap<String, SeasonLeague> seasonLeagues; //<season league id, season league>
    HashMap<String, TeamSeason> teamSeasonMap; // todo: might need to come for database
    HashMap<String, Team> teamsMap; // todo: might need to come for database
    HashMap<String, Admin> admins;
    SystemLogger logger;
    DataAccessController dataControl;

    public DataAccessController getDataControl() {
        return dataControl;
    }

    private static Controller instance = null;

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
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

    public HashMap<String, User> getUsersMap() {
        return usersMap;
    }

    public HashMap<String, Referee> getRefereeMap() {
        return refereeMap;
    }

    public HashMap<String, Game> getGameMap() {
        return gameMap;
    }

    public HashMap<String, SeasonLeague> getSeasonLeagues() {
        return seasonLeagues;
    }

    public HashMap<String, TeamSeason> getTeamSeasonMap() {
        return teamSeasonMap;
    }

    public HashMap<String, Team> getTeamsMap() {
        return teamsMap;
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

    public ArrayList<String> autoSignRefereeAndDates(ArrayList<Game> games, Date start, AssignmentPolicy policy) {
        ArrayList<String> gamesRes = new ArrayList<>();
        int amountTime = 1;
        if ("random".equals(policy.getValue())) {// shuffle games
            Random rand = new Random();
            amountTime = rand.nextInt(4) + 1;
        } else if ("serial".equals(policy.getValue())) {
        } else {
            return null;
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
                if(game.getHome().availableDate(startCopy) && game.getAway().availableDate(startCopy)) {
                    game.setDate(startCopy);
                    game.getHome().setDate(startCopy);
                    game.getAway().setDate(startCopy);
                    cal.add(Calendar.DAY_OF_MONTH, amountTime);
                    startCopy = cal.getTime();
                    gamesRes.add(game.getId());
                }
                else{return null;}
            } catch (Exception e) {
                e.printStackTrace();
                return null;
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
        return gamesRes;
    }

    /**
     * param- season: id of the season.
     * param- league: id of the league.
     * param- policy: assign according to this policy.
     * Goal - assign all games belong to this season-league according to the policy given.
     **/
    public String autoSignGames(String season, String league, AssignmentPolicy policy) {
        StringBuilder res = new StringBuilder();
        String key = league + "_" + season;
        if (seasonLeagues.containsKey(key)) {
            SeasonLeague seasonLeague = seasonLeagues.get(key);
            ArrayList<Game> games = seasonLeague.getGames();
            Date d1 = seasonLeague.getSeason().getStartDate();
            ArrayList<String> gamesChanged = autoSignRefereeAndDates(games, d1, policy);
            boolean field = autoSignField(games);

            if (gamesChanged.size() > 0 && field) { // insert to DB the changes.
                dataControl.updateRefereeGamesDB(refereeMap);
                dataControl.updateGamesDB(gameMap);
                for(String gID : gamesChanged){
                    res.append(gID).append(",");
                }
                return res.toString();
            }
        }
        // if there is no season-league in the system.
        return "";
    }

    public User login(String username, String password) {
        if (usersMap.containsKey(username) && usersMap.get(username).getPassword().equals(password)) {
            return usersMap.get(username);
        }
        return null;
    }
}