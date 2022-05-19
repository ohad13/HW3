
import Domain.*;
import Domain.Users.Referee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//import java.util.Date;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Date date = new Date(1, 2, 3);
    Season s1 = new Season("0", date);
    League l1 = new League("0", "ASA");
    SeasonLeague sl = new SeasonLeague("0_0", s1, l1, new AssignmentPolicy("random"));
    TeamSeason ts1 = new TeamSeason("0", s1, new Team("0", "Maccabi Haifa", "0"));
    TeamSeason ts2 = new TeamSeason("1", s1, new Team("1", "Hapoel Beer-Sheva", "1"));

    Game game = new Game("1", date, ts1, ts2, sl.getId(), ts1.getField());

    @Test
    @DisplayName("test no more than 3 referees.")
    void max3RefInGame() {
        // create 4 new referees.
        Referee referee1 = new Referee("Dor1", "DorL", "123456");
        Referee referee2 = new Referee("Dor2", "DorL", "123456");
        Referee referee3 = new Referee("Dor3", "DorL", "123456");
        Referee referee4 = new Referee("Dor4", "DorL", "123456");

        game.setReferee(referee1);
        game.setReferee(referee2);
        game.setReferee(referee3);
        assertEquals(3, game.getReferees().size());
        //make sure cannot add more than 3 referee
        assertFalse(game.setReferee(referee4));
    }
    @Test
    @DisplayName("test not double referee.")
    void doubleRefereesTest() {
        Referee referee1 = new Referee("Dor1", "DorL", "123456");
        // first insert of ref1.
        game.setReferee(referee1);

        //check that cannot add the same referee
        game.setReferee(referee1);
        assertEquals(1, game.getReferees().size());
    }

    @Test
    @DisplayName("test game have 2 different teams.")
    void sameTeamsTest() {
        // try to create game with the same team(season) and get null -> Illegal!
        Game gg = new Game("1", date, ts1, ts1, "1_1", "1");
        assertNull(gg.getId());
        assertNull(gg.getDate());
        assertNull(gg.getSeasonLeague());
    }
}