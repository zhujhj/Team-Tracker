package model;

import org.junit.jupiter.api.Test;
import ui.Display;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeamTrackerTest {

    Display display = new Display();
    ArrayList<Team> teams = new ArrayList<>();
    Team team = new Team("Ice Raiders");
    Person player;

    @Test
    void testAddPerson() {
        player = new Player("Jason");
        team.addPerson(player);
        ArrayList<Person> ap = new ArrayList<>();
        ap.add(player);

        assertEquals(team.getPeople(), ap);

        player = new Player("Emma");
        team.addPerson(player);
        ap.add(player);

        assertEquals(team.getPeople(), ap);
    }

//    @Test
//    void testPrintPeople() {
//
//    }

    @Test
    void testAddGoals() {
        player = new Player("Jason");
        ((Player) player).addGoals(5);
        assertEquals(((Player) player).getGoals(), 5);

        ((Player) player).addGoals(0);
        assertEquals(((Player) player).getGoals(), 5);

        ((Player) player).addGoals(-5);
        assertEquals(((Player) player).getGoals(), 0);

    }

    @Test
    void testAddAssists() {
        player = new Player("Jason");
        ((Player) player).addAssists(5);
        assertEquals(((Player) player).getAssists(), 5);

        ((Player) player).addAssists(0);
        assertEquals(((Player) player).getAssists(), 5);

        ((Player) player).addAssists(-5);
        assertEquals(((Player) player).getAssists(), 0);
    }

}