package Domain.Users;

import Domain.AssignmentPolicy;
import Domain.Controller;

public class FArepresentative extends Subscriber{
    public FArepresentative(String name, String userName, String password) {
        super(name, userName, password);
    }

    public boolean autoSignGames(String seasonYear, String league, AssignmentPolicy policy) {
        Controller c = Controller.getInstance();
        return c.autoSignGames(seasonYear,league,policy,true);
    }
}
