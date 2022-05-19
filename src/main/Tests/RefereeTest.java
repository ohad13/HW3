
import Domain.Users.Referee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Calendar;
//import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RefereeTest {
    Referee refDor = new Referee("Dor", "DorL", "123456");

    @Test
    @DisplayName("test constructor of Referee.")
    public void constructTest() {
        assertEquals("Dor", refDor.getName());
        assertEquals("DorL", refDor.getUserName());
        assertEquals("123456", refDor.getPassword());
        assertEquals(0, refDor.getGames().size());
        assertEquals(0, refDor.getDates().size());
    }

    @Test
    @DisplayName("test game adding.")
    public void addGameTest() {
        // add 1 game and check that there is only 1 game.
        refDor.addGame("1");
        assertEquals("1", refDor.getGames().get(0));
        // try to add the same game, test that can't.
        refDor.addGame("1");
        assertEquals("1", refDor.getGames().get(0));
    }

    @Test
    @DisplayName("test double dates after insert.")
    public void setDoubleDateTest() {
        Date D = new Date(2022, Calendar.DECEMBER, 9);
        Date D1 = new Date(2022, Calendar.DECEMBER, 9);
        refDor.setDate(D);
        assertTrue(refDor.getDates().remove(D)); // date was exist

        // check for double insertion
        refDor.setDate(D);
        assertFalse(refDor.setDate(D));
        assertFalse(refDor.setDate(D1));// remember that D and D1 have the same date !
    }

    @Test
    @DisplayName("test date is not open after add it.")
    public void availableDateTest() {
        Date D = new Date(2022, Calendar.DECEMBER, 9);
        Date D2 = new Date(2022, Calendar.NOVEMBER, 4);

        // try to set the same date
        refDor.setDate(D);
        refDor.setDate(D);
        assertEquals(1, refDor.getDates().size()); // date was exist

        // make sure this date is occupied
        assertFalse(refDor.availableDate(D));

        // this date is open
        assertTrue(refDor.availableDate(D2));

        // add new date and than check it is occupied
        refDor.setDate(D2);
        assertFalse(refDor.availableDate(D2));
    }
}