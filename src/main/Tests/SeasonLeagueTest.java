
import Domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
//import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SeasonLeagueTest {
    Date date = new Date(2021, Calendar.JANUARY, 3);
    Season s1 = new Season("0", date);
    League l1 = new League("0", "ASA");
    SeasonLeague sl1 = new SeasonLeague("1_1", s1, l1, new AssignmentPolicy("random"));

    @Test
    @DisplayName("test constructor of Season-League.")
    public void constructorTest() {
        assertEquals("1_1", sl1.getId());
        assertEquals(0, sl1.getGames().size());
        assertEquals(s1.getSeasonID(), sl1.getSeason().getSeasonID());
        assertEquals(l1.getId(), sl1.getLeague().getId());
        assertEquals(sl1.getPolicy().getValue(), "random");
    }

    @Test
    @DisplayName("test adding and comparing games in Season-League.")
    public void addAndCompareTest() {
        // create what needed
        Date date1 = new Date(1, 2, 5);

        Team t1 = new Team("10", "Hapoel", "10");
        Team t2 = new Team("11", "Maccbi", "11");

        Season s2 = new Season("1", date);

        TeamSeason ts1 = new TeamSeason("1", s1, t1);
        TeamSeason ts2 = new TeamSeason("2", s2, t2);

        Game game1 = new Game("1", date, ts1, ts2, "1_1", ts1.getField());
        Game game2 = new Game("2", date1, ts2, ts1, "1_1", ts2.getField());

        // enter 2 games into array list
        ArrayList<Game> games = new ArrayList<>();
        games.add(game1);
        games.add(game2);

        // update the season league with those 2 games.
        sl1.setGames(games);

        //test if there is actually 2 games in this SL array games.
        assertEquals(2, sl1.getGames().size());

        //test if the games is actually the games created before.
        assertEquals(game1, sl1.getGames().get(0));
        assertEquals(game2, sl1.getGames().get(1));
    }
}