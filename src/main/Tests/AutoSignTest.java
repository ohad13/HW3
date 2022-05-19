
import Domain.*;
import Domain.Users.Referee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.Calendar;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class AutoSignTest {

    @Test
    @DisplayName("test serial sign.")
    public void AutoSignTest1(){
        // entities for this test
        Date date = new Date(2021, Calendar.JANUARY, 1);
        Date date1 = new Date(2021, Calendar.JANUARY, 6);
        Date date2 = new Date(2021, Calendar.JANUARY, 9);
        Season s1 = new Season("1", date);
        League l1 = new League("1", "ASA");
        SeasonLeague sl1 = new SeasonLeague("1_1", s1, l1, new AssignmentPolicy("serial"));
        TeamSeason ts1 = new TeamSeason("1", s1, new Team("1", "Maccabi Haifa", "1"));
        TeamSeason ts2 = new TeamSeason("2", s1, new Team("2", "Hapoel Beer-Sheva", "2"));
        Referee referee1 = new Referee("Dor1", "DorL", "123456");
        Referee referee2 = new Referee("Dor2", "DorLe", "123456");
        Referee referee3 = new Referee("Dor3", "DorLev", "123456");
        Game game1 = new Game("1", null, ts1, ts2, sl1.getId(), null);
        Game game2 = new Game("2", null, ts2, ts1, sl1.getId(), null);

        // setup
        Controller c = Controller.getInstance1();
        sl1.setGame(game1);
        sl1.setGame(game2);
        c.setRef(referee1);
        c.setRef(referee2);
        c.setRef(referee3);
        c.setSL(sl1);

        // activate the auto sign games in season league !
        c.autoSignGames(s1.getSeasonID(),l1.getId(),sl1.getPolicy(),false);// delete the flag

        Date d1 = game1.getDate();
        Date d2 = game2.getDate();
        int ref1 = game1.getReferees().size();
        int ref2 = game2.getReferees().size();

        // now for the tests:
        // 1. both games with 3 referees.
        assertEquals(3,ref1); // both equals 3 ! // todo
        assertEquals(3,ref2); // both equals 3 ! // todo
        // 2. both games have fields.
        assertEquals(ts1.getField(),game1.getField());
        assertEquals(ts2.getField(),game2.getField());
        // 3. game 1 is before game 2(according to serial policy assignment).
        assertTrue(d1.before(d2));
        // 4. each referee have 2 games and 2 dates.
        assertEquals(2,referee1.getDates().size());
        assertEquals(2,referee1.getGames().size());
    }
}