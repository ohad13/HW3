package Domain.Users;

public class Player extends Subscriber {
    public Player(String name, String userName, String password) {
        super(name, userName, password);
    }
    @Override
    public boolean isFa() {
        return false;
    }
}
