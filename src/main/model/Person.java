package model;

import org.json.JSONObject;
import persistence.Writable;

// Person can be one of Player, Coach, or Manager (some will be implemented in the future)
public class Person implements Writable {

    private String name;

    //EFFECTS: creates Person and gives them a name
    public Person(String name) {

        this.name = name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public JSONObject toJson() {
        return null;
    }
}
