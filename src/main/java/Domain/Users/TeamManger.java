package Domain.Users;
public class TeamManger extends Subscriber{
    public TeamManger(String name, String userName, String password) {
        super(name, userName, password);
    }
    @Override
    public boolean isFa() {
        return false;
    }
}
