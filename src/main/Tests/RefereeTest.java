import Domain.Controller;
import Domain.Game;
import Domain.Users.Referee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RefereeTest {
    Controller c = Controller.getInstance();

    @Test
    @DisplayName("test game adding.")
    public void addGameTest() {
        // check add game to referee
        Game g = c.getGameById("0");
        Referee r = c.getRefereeById("maxim");
        r.addGame(g.getId());
        assertTrue(r.getGames().contains(g.getId()));
    }

    @Test
    @DisplayName("test double dates after insert.")
    public void setDoubleGameTest() {
        // check add game to referee
        Game g = c.getGameById("0");
        Referee r = c.getRefereeById("maxim");
        r.addGame(g.getId());
        assertFalse(r.addGame(g.getId()));
    }

    @Test
    @DisplayName("test date is not open after add it.")
    public void availableDateTest() {
        Referee r = c.getRefereeById("maxim");
        if (r.getGames().size() > 0) {
            Date gameDate = c.getGameById(r.getGames().get(0)).getDate();
            assertFalse(r.availableDate(gameDate));
        }
    }
}