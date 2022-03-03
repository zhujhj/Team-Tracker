package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.ArrayList;

// This class represents a sport team with a name and its members
public class ListOfTeams implements Writable {

//    private String sport; // one of ice hockey, soccer, basketball, football, or baseball
//    private String name; // team name
    private ArrayList<Team> teams; // list of people on the team

    //EFFECTS: creates Team and gives it a name
    public ListOfTeams() {

        teams = new ArrayList<>();

    }

    //EFFECTS: adds Person to Team
    //MODIFIES: people
    public void addTeam(Team team) {
        teams.add(team);
    }

    //EFFECTS: prints the people on the team as well as their goals and assists
    public void printTeams() {
        int counter = 0;
        for (Team t : teams) {
            System.out.println(counter + " " + t.getName());
            counter++;
        }
    }

    public void setTeams(ArrayList<Person> people) {
        this.teams = teams;
    }

    public ArrayList<Team> getTeams() {
        return this.teams;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
//        json.put("name", name);
        json.put("teams", teamsToJson());
        return json;
    }

    // EFFECTS: returns players in this workroom as a JSON array
    private JSONArray teamsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Team t : teams) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

}