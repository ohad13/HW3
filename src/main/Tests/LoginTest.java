import Domain.Controller;
import Domain.Users.FArepresentative;
import Domain.Users.Referee;
import Domain.Users.User;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LoginTest {

    @Test
    @DisplayName("test login with exists users.")
    public void loginTest1() {
        Controller c = Controller.getInstance();

        // check the first user
        User us1 = c.login("dorle22", "1234");
        assertEquals("dorle22", us1.getUserName());
        assertEquals("1234", us1.getPassword());

    }
    @Test
    @DisplayName("test login with not exist user.")
    public void loginTest2() {
        Controller c = Controller.getInstance();
        // user that does not exist
        assertNull(c.login("name", "password"));
    }

}
