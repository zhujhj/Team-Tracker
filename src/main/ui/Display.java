package ui;

import model.ListOfTeams;
import model.Person;
import model.Player;
import model.Team;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// This class provides the console application
public class Display {

    private static final String JSON_STORE = "./data/team.json";
    Scanner scan = new Scanner(System.in);
    Team team;
    ListOfTeams teams = new ListOfTeams();
    String input = "";
    int teamCounter = 0;
    int counter = 0;
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);

    //EFFECTS: begins the program
    //REQUIRES: an integer from 1-4 or x
    public void begin() {
        while (!input.equals("x")) {
            printMenu();
            input = scan.nextLine();
            if (input.equals("1")) {
                addTeam();
            } else if (input.equals("2")) {
                addPlayer();
            } else if (input.equals("3")) {
                displayTeams();
            } else if (input.equals("4")) {
                displayPlayers();
            } else if (input.equals("5")) {
                saveTeams();
            } else if (input.equals("6")) {
                loadTeams();
            }
            System.out.println();
        }
    }

    //EFFECTS: prints the menu
    public void printMenu() {
        System.out.println("___________________________________________________________");
        System.out.println("Welcome to your Team Tracker, enter x to quit.");
        System.out.println("Enter 1 to add new team");
        System.out.println("Enter 2 to add a player to a team");
        System.out.println("Enter 3 to list all teams");
        System.out.println("Enter 4 to list/edit all players of a team and their stats");
        System.out.println("Enter 5 to save team");
        System.out.println("Enter 6 to load team");
    }

    //EFFECTS: adds a Team to list of teams
    //MODIFIES: teams
    public void addTeam() {
        System.out.println("Enter team name: ");
        team = new Team(scan.nextLine());
        teams.addTeam(team);
        System.out.println(teams.getTeam(teamCounter).getName());
        System.out.println("Enter team sport: ");
        teams.getTeam(teamCounter).setSport(scan.nextLine());
        System.out.println(teams.getTeam(teamCounter).getSport());
        teamCounter++;
    }

    //EFFECTS: adds a Player to a specific Team
    //MODIFIES: a team specified by the user
    //REQUIRES: when selecting the team, user must input the number in front of the team
    public void addPlayer() {
        System.out.println("Which team would you like to add to? ");
        counter = 0;
        for (Team t : teams.getTeams()) {
            System.out.println(counter + " " + t.getName());
            counter++;
        }
        int whichTeam = Integer.parseInt(scan.nextLine());
        team = teams.getTeam(whichTeam);
        System.out.println(team.getName());
        System.out.println("What is the player name?");
        Person person = new Player(scan.nextLine());
        team.addPerson(person);
        team.printPeople();
    }

    //EFFECTS: prints out the teams
    public void displayTeams() {
        counter = 0;
        for (Team t : teams.getTeams()) {
            System.out.println(counter + " " + t.getName());
            counter++;
        }
    }

    //EFFECTS: prints out players and allows the user to edit the amount of goals or assists they have
    //MODIFIES: a Player specified by the user
    //REQUIRES: when selecting the player, user must input the number in front of the player
    public void displayPlayers() {
        System.out.println("Which team? ");
        int whichTeam = Integer.parseInt(scan.nextLine());
        team = teams.getTeam(whichTeam);
        System.out.println(team.getName() + " players: ");
        team.printPeople();
        System.out.println("Which player? ");
        Person whichPlayer = team.getPeople().get(Integer.parseInt(scan.nextLine()));
        System.out.println("Edit goals or assists? (g or a) ");
        input = scan.nextLine();
        if (input.equals("g")) {
            System.out.println("How many goals would you like to add? ");
            ((Player) whichPlayer).addGoals(Integer.parseInt(scan.nextLine()));
        } else if (input.equals("a")) {
            System.out.println("How many assists would you like to add?");
            ((Player) whichPlayer).addAssists(Integer.parseInt(scan.nextLine()));
        }
    }

    // EFFECTS: saves the workroom to file
//    private void saveTeam() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(team);
//            jsonWriter.close();
//            System.out.println("Saved " + team.getName() + " to " + JSON_STORE);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + JSON_STORE);
//        }
//    }
    private void saveTeams() {
        try {
            jsonWriter.open();
            jsonWriter.write(teams);
            jsonWriter.close();
            System.out.println("Saved " + team.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
//    private void loadTeam() {
//        try {
//            team = jsonReader.read();
//            teams.addTeam(team);
//            System.out.println("Loaded " + team.getName() + " from " + JSON_STORE);
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE);
//        }
//    }
    private void loadTeams() {
        try {
            teams = jsonReader.read();
//            teams.addTeam(team);
            System.out.println("Loaded teams from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
