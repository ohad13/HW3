
import Domain.*;
import Domain.Users.Referee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Controller c = Controller.getInstance();

    @Test
    @DisplayName("test no more than 3 referees.")
    void max3RefInGame() {
        Map<String, Game> gamesMap = c.getGameMap();
        for (String gameID : gamesMap.keySet()) {
            Game g = gamesMap.get(gameID);
            assertTrue(g.getReferees().size() < 4);
        }
    }

    @Test
    @DisplayName("test not double referee.")
    void doubleRefereesTest() {
        Map<String, Game> gamesMap = c.getGameMap();
        for (String gameID : gamesMap.keySet()) {
            Game g = gamesMap.get(gameID);
            ArrayList<Referee> refs = g.getReferees();
            if (refs.size() == 3) {
                Referee r1 = refs.get(0);
                Referee r2 = refs.get(1);
                Referee r3 = refs.get(2);
                assertTrue(r1 != r2 && r1 != r3 && r2 != r3);
            }
        }
    }

    @Test
    @DisplayName("test game have 2 different teams.")
    void sameTeamsTest() {
//        Controller c = Controller.getInstance();
        Map<String, Game> gamesMap = c.getGameMap();
        for (String gameID : gamesMap.keySet()) {
            Game g = gamesMap.get(gameID);
            assertNotSame(g.getHome(), g.getAway());
        }
    }
}