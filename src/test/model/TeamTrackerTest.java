package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Display;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Test class
class TeamTrackerTest {

    Display display = new Display();
    ListOfTeams teams;
    Team team;
    Person player;

    @BeforeEach
    void runBefore() {
        teams = new ListOfTeams();
        team = new Team("Ice Raiders");
        player = new Player("Jason");
    }

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

    @Test
    void testAddTeam() {
        teams.addTeam(team);
        ArrayList<Team> alt = new ArrayList<>();
        alt.add(team);
        assertEquals(teams.getTeams(), alt);
        Team t = new Team("Raptors");
        teams.addTeam(t);
        alt.add(t);
        assertEquals(teams.getTeams(), alt);
    }

    //{"assists":5,"name":"jason","goals":5}

    @Test
    void testPlayerToJson() {
        assertEquals(player.toJson().toString(), "{\"assists\":0,\"name\":\"Jason\",\"goals\":0}");
    }

    @Test
    void testTeamToJson() {
        team.addPerson(player);
        team.setSport("Hockey");
        assertEquals(team.toJson().toString(),
                "{\"players\":[{\"assists\":0,\"name\":\"Jason\"," +
                        "\"goals\":0}],\"name\":\"Ice Raiders\",\"sport\":\"Hockey\"}");
    }

    @Test
    void testListOfTeamsToJson() {
        Team team1 = new Team("Raptors");
        team1.setSport("Basketball");
        team.addPerson(player);
        team.setSport("Hockey");
        teams.addTeam(team);
        teams.addTeam(team1);
        assertEquals(teams.toJson().toString(), "{\"teams\":[{\"players\":[{\"assists\":0,\"name\":\"Jason\"," +
                "\"goals\":0}],\"name\":\"Ice Raiders\",\"sport\":\"Hockey\"},{\"players\":[],\"name\":\"Raptors\"," +
                "\"sport\":\"Basketball\"}]}");
    }

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

    @Test
    void testPlayersToJson() {
        team.addPerson(player);
        assertEquals(team.toJson().toString(), "{\"players\":[{\"assists\":0,\"name\":\"Jason\",\"goals\":0}]," +
                "\"name\":\"Ice Raiders\"}");
        Person player1 = new Player("Emma");
        team.addPerson(player1);
        assertEquals(team.toJson().toString(), "{\"players\":[{\"assists\":0,\"name\":\"Jason\",\"goals\":0}," +
                "{\"assists\":0,\"name\":\"Emma\",\"goals\":0}],\"name\":\"Ice Raiders\"}");
    }

    @Test
    void testTeamsToJson() {
        teams.addTeam(team);
        assertEquals(teams.toJson().toString(), "{\"teams\":[{\"players\":[],\"name\":\"Ice Raiders\"}]}");
        Team team1 = new Team("Raptors");
        team1.addPerson(player);
        teams.addTeam(team1);
        assertEquals(teams.toJson().toString(), "{\"teams\":[{\"players\":[],\"name\":\"Ice Raiders\"}," +
                "{\"players\":[{\"assists\":0,\"name\":\"Jason\",\"goals\":0}],\"name\":\"Raptors\"}]}");
    }

}