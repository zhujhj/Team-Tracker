package ui;

import model.ListOfTeams;
import model.Team;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Screen {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    Team team;
    ListOfTeams teams;
    private static final String JSON_STORE = "./data/team.json";
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);

//    private JPanel teamTracker;
//    private JPanel panel;

    public Screen() {

        teams = new ListOfTeams();
        createPanel();

    }

    public void createPanel() {
//        panel = new JPanel();
//        panel.setBounds(40,80,200,200);
//        JFrame f = new JFrame("Team Tracker");
        JFrame f = new JFrame("Team Tracker");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.pack();
        f.setResizable(true);
        f.setVisible(true);
        f.setSize(400, 400);

        JPanel panel = new JPanel();
        panel.setBounds(80,80,200,200);
        panel.setBackground(Color.gray);

        JButton b1 = new JButton("Add New Team");
        b1.setBounds(50,100,150,30);
        b1.setBackground(Color.yellow);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Add New Team");
                frame = new JFrame();
                String teamName = JOptionPane.showInputDialog(f,"Enter Team Name");
                String teamSport = JOptionPane.showInputDialog(f,"Enter Team Sport");
                team = new Team(teamName);
                team.setSport(teamSport);
                teams.addTeam(team);
                System.out.println(teams.toString());
            }
        });

        JButton b2 = new JButton("View All Teams");
        b2.setBounds(50,100,150,30);
        b2.setBackground(Color.green);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame();
                final JLabel label = new JLabel();
                label.setSize(500,100);
                JButton b = new JButton("Show");
                b.setBounds(75,150,80,30);
                final DefaultListModel<String> l1 = new DefaultListModel<>();
                for (Team t : teams.getTeams()) {
                    l1.addElement(t.getName());
                }
                final JList<String> list1 = new JList<>(l1);
                list1.setBounds(75,75, 75,75);

                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setViewportView(list1);
                list1.setLayoutOrientation(JList.VERTICAL);
                scrollPane.setBounds(50,50, 125,100);

                frame1.add(scrollPane);
//                frame1.add(list2);
                frame1.add(b);
                frame1.add(label);
                frame1.setSize(250,225);
                frame1.setLayout(null);
                frame1.setVisible(true);
                b.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String data = "";
                        if (list1.getSelectedIndex() != -1) {
                            data = "Team Selected: " + list1.getSelectedValue();
                            label.setText(data);
                            label.setBounds(50, 15, 200, 20);
                        }
//                        if (list2.getSelectedIndex() != -1) {
//                            data += ", FrameWork Selected: ";
//                            for (Object frame :list2.getSelectedValues()) {
//                                data += frame + " ";
//                            }
//                        }
                        label.setText(data);
                    }
                });
            }
        });

        JButton b3 = new JButton("Save Data");
        b3.setBounds(50,100,150,30);
        b3.setBackground(Color.green);
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveTeams();
            }
        });

        JButton b4 = new JButton("Load Data");
        b4.setBounds(50,100,150,30);
        b4.setBackground(Color.green);
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTeams();
            }
        });

        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
//        f.add(panel);
//        f.setSize(400,400);
//        f.setLayout(null);
//        f.setVisible(true);
        f.add(panel);
        f.setLayout(null);
        f.setVisible(true);
    }

    // EFFECTS: saves teams to file
    private void saveTeams() {
        try {
            jsonWriter.open();
            jsonWriter.write(teams);
            jsonWriter.close();
            System.out.println("Saved  teams  to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads list of teams from file

    private void loadTeams() {
        try {
            teams = jsonReader.read();
//            teams.addTeam(team);
            System.out.println("Loaded teams from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

//    public void createButton() {
//        button = new JButton();
//    }

//    public void createFrame() {
//        frame = new JFrame();
//    }

}
