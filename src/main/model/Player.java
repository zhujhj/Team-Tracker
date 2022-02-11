package model;

public class Player extends Person {

    private int goals;
    private int assists;

    //EFFECTS: creates Player and gives them a name
    public Player(String name) {
        super(name);
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
        return goals;
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
        return assists;
    }

}
