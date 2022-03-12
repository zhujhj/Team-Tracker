package persistence;

import model.ListOfTeams;
import model.Team;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ListOfTeams teams = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/team.json");
        try {
            ListOfTeams teams = reader.read();
//            assertEquals("My work room", teams.getName());
//            assertEquals(0, teams.numThingies());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/team.json");
        try {
            ListOfTeams teams = reader.read();
//            assertEquals("My work room", teams.getName());
            List<Team> listOfTeams = teams.getTeams();
            assertEquals(2, listOfTeams.size());
//            checkThingy("needle", Category.STITCHING, listOfTeams.get(0));
//            checkThingy("saw", Category.WOODWORK, listOfTeams.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
