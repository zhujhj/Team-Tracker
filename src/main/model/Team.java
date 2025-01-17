package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.ArrayList;

// This class represents a sport team with a name and its members
public class Team implements Writable {

    private String sport; // one of ice hockey, soccer, basketball, football, or baseball
    private String name; // team name
    private ArrayList<Person> people = new ArrayList<>(); // list of people on the team

    //EFFECTS: creates Team and gives it a name
    public Team(String name) {

        this.name = name;

    }

    //EFFECTS: adds Person to Team
    //MODIFIES: people
    public void addPerson(Person person) {
        people.add(person);
        EventLog.getInstance().logEvent(new Event("Person " + person.getName() + " added to " + name + "."));
    }

    public void setPeople(ArrayList<Person> people) {
        this.people = people;
    }

    public ArrayList<Person> getPeople() {
        return this.people;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getSport() {
        return this.sport;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("sport", sport);
        json.put("players", playersToJson());
        return json;
    }

    // EFFECTS: returns players in this team as a JSON array
    private JSONArray playersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Person p : people) {
            jsonArray.put(p.toJson());
        }
        System.out.println(jsonArray);
        return jsonArray;
    }

}
