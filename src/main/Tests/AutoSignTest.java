
import Domain.*;
import Service.UserApplication;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class AutoSignTest {
    static UserApplication App = new UserApplication();
    Controller c = Controller.getInstance();

    @BeforeAll
    public static void init(){
        App.login("ohad","miller");
    }

    @Test
    @DisplayName("test random sign.")
    public void AutoSignTest1() {
        String gameAssigned = App.autoSignGames("1", "1", new AssignmentPolicy("random"));
        String[] games = gameAssigned.split(",");

        HashMap<String, Game> gameMap = c.getDataControl().getGameMap();
        for (Game game : gameMap.values()) {
            if (c.getSeasonLeagues().get(game.getSeasonLeague()).getSeason().getSeasonID().equals("1") && c.getSeasonLeagues().get(game.getSeasonLeague()).getLeague().getId().equals("1")) {
                assertNotNull(game.getDate());
                assertNotNull(game.getField());
                assertEquals(game.getReferees().size(), 3);
            }
        }

        System.out.println(Arrays.toString(games));
    }

    @Test
    @DisplayName("test serial sign.")
    public void AutoSignTest2() {

        String gameAssigned = App.autoSignGames("2", "2", new AssignmentPolicy("serial"));// delete the flag
        String[] games = gameAssigned.split(",");

        HashMap<String, Game> gameMap = c.getDataControl().getGameMap();
        Date start = c.getSeasonLeagues().get("2_2").getSeason().getStartDate();
        //copy date
        Date startCopy = (Date) start.clone();
        Calendar cal = Calendar.getInstance();
        cal.setTime(startCopy);
        for (Game game : gameMap.values()) {
            if (c.getSeasonLeagues().get(game.getSeasonLeague()).getSeason().getSeasonID().equals("2") && c.getSeasonLeagues().get(game.getSeasonLeague()).getLeague().getId().equals("2")) {
                assertEquals(startCopy, game.getDate());
                cal.add(Calendar.DAY_OF_MONTH, 1);
                startCopy = cal.getTime();
            }
        }
        System.out.println(Arrays.toString(games));
    }
}