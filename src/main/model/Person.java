package model;

public class Person { // Person can be one of Player, Coach, or Manager (some will be implemented in the future)

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

}
