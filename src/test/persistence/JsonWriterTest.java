package persistence;

import model.ListOfTeams;
import model.Team;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            ListOfTeams teams = new ListOfTeams();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            ListOfTeams teams = new ListOfTeams();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(teams);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            teams = reader.read();
//            assertEquals("My work room", teams.getName());
            assertEquals(0, teams.getTeams().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            ListOfTeams teams = new ListOfTeams();
            teams.addTeam(new Team("raptors"));
            teams.addTeam(new Team("leafs"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(teams);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            teams = reader.read();
//            assertEquals("My work room", teams.getName());
            List<Team> listOfTeams = teams.getTeams();
            assertEquals(2, listOfTeams.size());
//            checkThingy("saw", Category.METALWORK, listOfTeams.get(0));
//            checkThingy("needle", Category.STITCHING, listOfTeams.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}