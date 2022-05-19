package Domain.Users;

public class Guest extends User {
    public Guest() {
        super("guest", "guest", "none");
    }

    @Override
    public String getName() {
        return this.name;
    }
}
