package Domain.Users;
public class TeamOwner extends Subscriber{
    public TeamOwner(String name, String userName, String password) {
        super(name, userName, password);
    }
    @Override
    public boolean isFa() {
        return false;
    }
}
