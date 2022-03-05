package model;

import org.json.JSONObject;

// This class represents a Player who has goals and assists
public class Player extends Person {

    private int goals;
    private int assists;

    //EFFECTS: creates Player and gives them a name and 0 goals and assists
    public Player(String name) {
        super(name);
        this.goals = 0;
        this.assists = 0;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    //EFFECTS: adds goals to Player
    //MODIFIES: this
    public void addGoals(int goals) {
        this.goals += goals;
    }

    public int getGoals() {
        return this.goals;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    //EFFECTS: adds assists to Player
    //MODIFIES: this
    public void addAssists(int assists) {
        this.assists += assists;
    }

    public int getAssists() {
        return this.assists;
    }

    public String toString() {
        return getName() + ": " + goals + ": " + assists;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", getName());
        json.put("goals", goals);
        json.put("assists", assists);
        return json;
    }

}
