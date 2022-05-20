package Domain.Users;

public class Coach extends Subscriber{
    public Coach(String name, String userName, String password) {
        super(name, userName, password);
    }
    @Override
    public boolean isFa() {
        return false;
    }
}
