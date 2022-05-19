import Domain.Controller;
import Domain.Users.FArepresentative;
import Domain.Users.Referee;
import Domain.Users.User;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LoginTest {
    Referee referee1 = new Referee("Dor1", "DorL", "123456");
    FArepresentative fa1 = new FArepresentative("ohad", "ohad1", "987654");

    @Test
    @DisplayName("test login with exists users.")
    public void loginTest1() {
        Controller c = Controller.getInstance1();
        c.setUsers(referee1.getName(), referee1);
        c.setUsers(fa1.getName(), fa1);

        // check the first user
        User us1 = c.login(referee1.getName(), referee1.getPassword());
        assertEquals(referee1.getName(), us1.getName());
        assertEquals(referee1.getPassword(), us1.getPassword());

        // check the second user
        User us2 = c.login(fa1.getName(), fa1.getPassword());
        assertEquals(fa1.getName(), us2.getName());
        assertEquals(fa1.getPassword(), us2.getPassword());
    }
    @Test
    @DisplayName("test login with not exist user.")
    public void loginTest2() {
        Controller c = Controller.getInstance1();
        // user that does not exist
        assertNull(c.login("name", "password"));
    }

}
