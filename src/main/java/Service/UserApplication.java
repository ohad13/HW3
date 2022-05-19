package Service;


import Domain.AssignmentPolicy;
import Domain.Controller;
import Domain.Game;
import Domain.Users.FArepresentative;
import Domain.Users.Referee;
import Domain.Users.User;

public class UserApplication {

    Controller controller; // api to domain layer

    public UserApplication(Controller controller) {
        this.controller = controller;
    }

    // todo: NO NEEDED!!
    public boolean SignUpRefereeToGame(String userName, String userPass, String gameID, String refID) {
        // sign in FA representative to system
        FArepresentative fa = (FArepresentative) controller.login(userName, userPass);
        if (fa == null) {
            return false;
        }

        Game game = controller.getGameById(gameID);
        Referee ref = controller.getRefereeById(refID);

        return controller.assignRefereeToGame(game, ref);
    }

    public boolean autoSignGames(String username, String password, String seasonYear, String league, AssignmentPolicy policy){
        // sign in FA representative to system
        FArepresentative fa = (FArepresentative) controller.login(username,password); // todo: login first and check
        if (fa == null) {
            return false;
        }
        return fa.autoSignGames(seasonYear,league,policy);
    }

    public User login(String username, String password) {
        return controller.login(username,password);
    }
}
