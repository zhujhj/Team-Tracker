package persistence;

import model.Player;
import model.Team;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkTeam(String name, Team team, Player thingy) {
        assertEquals(name, thingy.getName());
//        assertEquals(category, thingy.getCategory());
    }
}
