package ui;

import model.Person;
import model.Player;
import model.Team;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Team team;
        ArrayList<Team> teams = new ArrayList<Team>();
        String input = "";
        int teamCounter = 0;
        int counter = 0;

        while (!input.equals("x")) {
            System.out.println("___________________________________________________________");
            System.out.println("Welcome to your Team Tracker, enter x at any time to quit.");
            System.out.println("Enter 1 to add new team");
            System.out.println("Enter 2 to add a player to a team");
            System.out.println("Enter 3 to list all teams");
            System.out.println("Enter 4 to list/edit all players of a team and their stats");
            input = scan.nextLine();
            if (input.equals("1")) {
                System.out.println("Enter team name: ");
                team = new Team(scan.nextLine());
                teams.add(team);
                System.out.println(teams.get(teamCounter).getName());
                System.out.println("Enter team sport: ");
                teams.get(teamCounter).setSport(scan.nextLine());
                System.out.println(teams.get(teamCounter).getSport());
            } else if (input.equals("2")) {
                System.out.println("Which team would you like to add to? ");
                counter = 0;
                for (Team t : teams) {
                    System.out.println(counter + " " + t.getName());
                    counter++;
                }
                int whichTeam = Integer.parseInt(scan.nextLine());
                team = teams.get(whichTeam);
                System.out.println(team.getName());
                System.out.println("What is the player name?");
                Person person = new Player(scan.nextLine());
                team.addPerson(person);
                team.printPeople();
            } else if (input.equals("3")) {
                counter = 0;
                for (Team t : teams) {
                    System.out.println(counter + " " + t.getName());
                    counter++;
                }
            } else if (input.equals("4")) {
                System.out.println("Which team? ");
                int whichTeam = Integer.parseInt(scan.nextLine());
                team = teams.get(whichTeam);
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
            System.out.println("");
        }

    }
}
