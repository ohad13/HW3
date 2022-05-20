package Service;


import Domain.AssignmentPolicy;
import Domain.Controller;
import Domain.Game;
import Domain.Users.FArepresentative;
import Domain.Users.Referee;
import Domain.Users.User;

public class UserApplication {
    User currentUser = null;

    // todo: NO NEEDED!!
    public boolean SignUpRefereeToGame(String userName, String userPass, String gameID, String refID) {

        Controller controller = Controller.getInstance();
        // sign in FA representative to system
        FArepresentative fa = (FArepresentative) controller.login(userName, userPass);
        if (fa == null) {
            return false;
        }

        Game game = controller.getGameById(gameID);
        Referee ref = controller.getRefereeById(refID);

        return controller.assignRefereeToGame(game, ref);
    }

    public String autoSignGames(String seasonYear, String league, AssignmentPolicy policy) {
        // sign in FA representative to system
        if (currentUser == null) {
            return "";
        }
        if (currentUser.isFa()) {
            FArepresentative fa = (FArepresentative) currentUser;
            return fa.autoSignGames(seasonYear, league, policy);
        }
        return "";
    }

    public User login(String username, String password) {
        Controller controller = Controller.getInstance();
        this.currentUser = controller.login(username, password);
        return currentUser;
    }
}
