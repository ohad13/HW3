package DataAccess;

import Domain.*;
import Domain.Users.FArepresentative;
import Domain.Users.Referee;
import Domain.Users.User;
import DataAccess.Tables.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;

public class DataAccessController {

    static DataAccessController instance = null;

    // DB tables
    FaRepresentativeDao faDao;
    GamesDao gamesDao;
    LeaguesDao leaguesDao;
    RefereeDao refereeDao;
    SeasonDao seasonDao;
    SeasonLeagueDao seasonLeagueDao;
    TeamsDao teamsDao;
    TeamSeasonDao teamSeasonDao;
    UsersDao usersDao;

    // data
    HashMap<String, TeamSeason> teamSeasonMap;
    HashMap<String, Team> teamsMap;
    HashMap<String, Game> gameMap;
    HashMap<String, Season> seasonMap;
    HashMap<String, SeasonLeague> seasonLeagueMap;
    HashMap<String, Referee> refereeMap;
    HashMap<String, FArepresentative> faMap;
    HashMap<String, League> leaguesMap;
    HashMap<String, User> usersMap;

    private DataAccessController() throws SQLException, ParseException {
        faDao = new FaRepresentativeDao();
        gamesDao = new GamesDao();
        leaguesDao = new LeaguesDao();
        refereeDao = new RefereeDao();
        seasonDao = new SeasonDao();
        seasonLeagueDao = new SeasonLeagueDao();
        teamsDao = new TeamsDao();
        leaguesDao = new LeaguesDao();
        teamSeasonDao = new TeamSeasonDao();
        usersDao = new UsersDao();

        teamsMap = teamsDao.getAll();
        seasonMap = seasonDao.getAll();
        teamSeasonMap = teamSeasonDao.getAll(seasonMap,teamsMap);
        gameMap = gamesDao.getAll(teamSeasonMap);
        leaguesMap = leaguesDao.getAll();
        seasonLeagueMap = seasonLeagueDao.getAll(gameMap,seasonMap,leaguesMap);

        refereeMap = refereeDao.getAll();
        updateRefereeGames(refereeMap,gameMap);

        faMap = faDao.getAll();
        usersMap = usersDao.getAll(refereeMap,faMap);
    }

    private void updateRefereeGames(HashMap<String, Referee> refereeMap, HashMap<String, Game> gameMap) {
        for(Referee ref : refereeMap.values()){
            for(String gameID : ref.getGames()){
                ref.setDate(gameMap.get(gameID).getDate());
                gameMap.get(gameID).setReferee(ref);
            }
        }
    }

    public HashMap<String, User> getUsersMap() {
        return usersMap;
    }

    public static DataAccessController getInstance(){
        if(instance == null){
            try {
                instance = new DataAccessController();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public HashMap<String, TeamSeason> getTeamSeasonMap() {
        return teamSeasonMap;
    }

    public HashMap<String, Team> getTeamsMap() {
        return teamsMap;
    }

    public HashMap<String, Game> getGameMap() {
        return gameMap;
    }

    public HashMap<String, Season> getSeasonMap() {
        return seasonMap;
    }

    public HashMap<String, SeasonLeague> getSeasonLeagueMap() {
        return seasonLeagueMap;
    }

    public HashMap<String, Referee> getRefereeMap() {
        return refereeMap;
    }

    public HashMap<String, FArepresentative> getFaMap() {
        return faMap;
    }

    public HashMap<String, League> getLeaguesMap() {
        return leaguesMap;
    }

    public void updateRefereeGamesDB(HashMap<String, Referee> refereeMap) {
        refereeDao.updateRefereeGameDB(refereeMap);
    }

    public void updateGamesDB(HashMap<String, Game> gameMap) {
        gamesDao.updateGamesDB(gameMap);
    }
}
