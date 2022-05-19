
import Domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//import java.util.Date;
import java.sql.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SeasonTest {
    Date date = new Date(1, 2, 3);
    Season s1 = new Season("1", date);
    League l1 = new League("2", "ASA");
    SeasonLeague sl1 = new SeasonLeague("1_2", s1, l1, new AssignmentPolicy("random"));

    @Test
    @DisplayName("test the constructor of Season entity.")
    void constructorTest() {
        assertEquals("1", s1.getSeasonID());
        assertEquals(date, s1.getStartDate());
    }

    @Test
    @DisplayName("test adding 2 different season-league.")
    void addSeasonTest1() {
        // add first season league
        s1.addSeasonLeague(sl1);
        assertEquals(1, s1.getLeaguesInSeason().size());

        // add second season league
        Season s2 = new Season("2", date);
        League l2 = new League("2", "ASA");
        SeasonLeague sl2 = new SeasonLeague("2_2", s2, l2, new AssignmentPolicy("random"));

        // try to add the same SL
        s1.addSeasonLeague(sl2);
        assertEquals(1, s1.getLeaguesInSeason().size());
    }

    @Test
    @DisplayName("test adding same season-league.")
    void addSameSeasonTest() {
        // add first season league
        s1.addSeasonLeague(sl1);
        s1.addSeasonLeague(sl1);
        assertEquals(1, s1.getLeaguesInSeason().size());
    }

    @Test
    @DisplayName("test adding 2 different team-season.")
    void differentTeamsTest() {
        TeamSeason ts1 = new TeamSeason("0", s1, new Team("0", "Maccabi Haifa", "0"));

        // add first team season
        s1.addTeamToSeason(ts1);
        assertEquals(1, s1.getTeamsInSeason().size());

        TeamSeason ts2 = new TeamSeason("1", s1, new Team("1", "Hapoel Beer-Sheva", "1"));

        // add second team season
        s1.addTeamToSeason(ts2);
        assertEquals(2, s1.getTeamsInSeason().size());
    }
    @Test
    @DisplayName("test adding same team-season.")
    void sameTeamsTest() {
        TeamSeason ts1 = new TeamSeason("0", s1, new Team("0", "Maccabi Haifa", "0"));

        // add first team season
        s1.addTeamToSeason(ts1);
        s1.addTeamToSeason(ts1);
        assertEquals(1, s1.getTeamsInSeason().size());
    }
}