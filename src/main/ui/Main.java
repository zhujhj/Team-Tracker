package ui;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Display display = new Display();
        Scanner scan = new Scanner(System.in);
        String input = "";

        while (!input.equals("x")) {
            display.begin();
            input = scan.nextLine();
            if (input.equals("1")) {
                display.addTeam();
            } else if (input.equals("2")) {
                display.addPlayer();
            } else if (input.equals("3")) {
                display.displayTeams();
            } else if (input.equals("4")) {
                display.displayPlayers();
            }
            System.out.println();
        }

    }
}
