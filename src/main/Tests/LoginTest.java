import Domain.Users.User;
import Service.UserApplication;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LoginTest {
    UserApplication App = new UserApplication();

    @Test
    @DisplayName("test login with exist user.")
    public void loginTest1() {
        User us1 = App.login("dorle22", "1234");
        assertEquals("dorle22", us1.getUserName());
        assertEquals("1234", us1.getPassword());
    }

    @Test
    @DisplayName("test login with none exist user.")
    public void loginTest2() {
        assertNull(App.login("none", "exist"));
    }
}