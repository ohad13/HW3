
import Domain.*;
import Domain.Users.Referee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class AutoSignTest {

    @Test
    @DisplayName("test random sign.")
    public void AutoSignTest1(){

        Controller c = Controller.getInstance();
        c.autoSignGames("1","1",new AssignmentPolicy("random"));// delete the flag

        HashMap<String,Game> gameMap = c.getDataControl().getGameMap();
        for(Game game : gameMap.values()){
            if(c.getSeasonLeagues().get(game.getSeasonLeague()).getSeason().getSeasonID().equals("1") && c.getSeasonLeagues().get(game.getSeasonLeague()).getLeague().getId().equals("1")){
                assertNotNull(game.getDate());
                assertNotNull(game.getField());
                assertEquals(3,game.getReferees().size());
            }
        }
    }

    @Test
    @DisplayName("test serial sign.")
    public void AutoSignTest2(){

        Controller c = Controller.getInstance();
        c.autoSignGames("2","2",new AssignmentPolicy("serial"));// delete the flag

        HashMap<String,Game> gameMap = c.getDataControl().getGameMap();
        Date start = c.getSeasonLeagues().get("2_2").getSeason().getStartDate();
        //copy date
        Date startCopy = (Date) start.clone();
        Calendar cal = Calendar.getInstance();
        cal.setTime(startCopy);
        for(Game game : gameMap.values()){
            if(c.getSeasonLeagues().get(game.getSeasonLeague()).getSeason().getSeasonID().equals("2") && c.getSeasonLeagues().get(game.getSeasonLeague()).getLeague().getId().equals("2")){
                assertEquals(startCopy,game.getDate());
                cal.add(Calendar.DAY_OF_MONTH, 1);
                startCopy =  cal.getTime();
            }
        }
    }
}