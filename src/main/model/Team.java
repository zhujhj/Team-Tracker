package model;

import java.util.ArrayList;

public class Team {

    private String sport; // one of ice hockey, soccer, basketball, football, or baseball
    private String name;
    private ArrayList<Person> people = new ArrayList<Person>(); // list of people on the team

    public Team(String name) {

        this.name = name;

    }

    public void addPerson(Person person) {
        people.add(person);
    }

    public void printPeople() {
        int counter = 0;
        for (Person p : people) {
            System.out.println(counter + " " + p.getName() + " has "
                    + ((Player) p).getGoals() + " goals and " + ((Player) p).getAssists() + " assists");
            counter++;
        }
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

}
