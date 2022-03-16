package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.ArrayList;

// This class represents a list of sports teams
public class ListOfTeams implements Writable {
    private ArrayList<Team> teams;

    //EFFECTS: creates a list of teams
    public ListOfTeams() {

        teams = new ArrayList<>();

    }

    //EFFECTS: adds Team to ListOfTeams
    //MODIFIES: teams
    public void addTeam(Team team) {
        teams.add(team);
    }

    public Team getTeam(int i) {
        return this.teams.get(i);
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public ArrayList<Team> getTeams() {
        return this.teams;
    }

    public String toString() {
        String listOfTeams = "";
        for (Team t : teams) {
            listOfTeams += t.getName() + " " + t.getSport() + ", ";
        }
        return listOfTeams;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("teams", teamsToJson());
        return json;
    }

    // EFFECTS: returns teams in list of teams as a JSON array
    private JSONArray teamsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Team t : teams) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }

}