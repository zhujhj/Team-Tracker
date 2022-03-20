package ui;

import model.ListOfTeams;
import model.Person;
import model.Player;
import model.Team;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Screen {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    Player player;
    Team team;
    ListOfTeams teams;
    private static final String JSON_STORE = "./data/team.json";
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);

    JFrame mainFrame;
    JPanel mainPanel;
    JButton newTeamButton;
    JButton viewTeamsButton;
    JButton saveDataButton;
    JButton loadDataButton;
    JFrame viewTeamFrame;
    JLabel viewTeamLabel;
    JButton selectTeamButton;
    DefaultListModel<String> listModelTeams;
    JList<String> jlistTeams;
    JScrollPane teamsScrollPane;
    JFrame viewPlayersFrame;
    DefaultListModel<String> listModelPlayers;
    JList<String> jlistPlayers;
    JButton newPlayerButton;
    JButton editPlayerButton;
    JScrollPane playerScrollPane;

    public Screen() {

        teams = new ListOfTeams();
        createTeamTracker();

    }

    public void createTeamTracker() {
        createMainFrame();
        createMainPanel();
        createButtons();

        addNewTeam();
        viewTeams();

        saveAndLoadData();
        addToMainPanel();
    }

    public void createMainFrame() {
        mainFrame = new JFrame("Team Tracker");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setResizable(true);
        mainFrame.setVisible(true);
        mainFrame.setSize(400, 400);
    }

    public void createMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setBounds(80,80,200,200);
        mainPanel.setBackground(Color.gray);
    }

    public void createButtons() {

        newTeamButton = new JButton("Add New Team");
        newTeamButton.setBounds(50,100,150,30);
        newTeamButton.setBackground(Color.yellow);

        viewTeamsButton = new JButton("View All Teams");
        viewTeamsButton.setBounds(50,100,150,30);
        viewTeamsButton.setBackground(Color.green);

        saveDataButton = new JButton("Save Data");
        saveDataButton.setBounds(50,100,150,30);
        saveDataButton.setBackground(Color.green);

        loadDataButton = new JButton("Load Data");
        loadDataButton.setBounds(50,100,150,30);
        loadDataButton.setBackground(Color.green);

    }

    public void addNewTeam() {
        newTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String teamName = JOptionPane.showInputDialog(mainFrame,"Enter Team Name");
                String teamSport = JOptionPane.showInputDialog(mainFrame,"Enter Team Sport");
                team = new Team(teamName);
                team.setSport(teamSport);
                teams.addTeam(team);
                System.out.println(teams.toString());
            }
        });
    }

    public void viewTeams() {
        viewTeamsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                createViewTeamFrame();

                selectTeamButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String data = "";
                        if (jlistTeams.getSelectedIndex() != -1) {

                            createViewPlayersFrame();

                            addNewPlayer();
                            editPlayer();

                        }
                        viewTeamLabel.setText(data);
                    }
                });
            }
        });
    }

    public void createViewTeamFrame() {
        viewTeamFrame = new JFrame();
        viewTeamLabel = new JLabel();
        viewTeamLabel.setSize(500,100);
        selectTeamButton = new JButton("Select");
        selectTeamButton.setBounds(75,150,80,30);
        listModelTeams = new DefaultListModel<>();
        for (Team t : teams.getTeams()) {
            listModelTeams.addElement(t.getName());
        }
        jlistTeams = new JList<>(listModelTeams);
        jlistTeams.setBounds(75,75, 75,75);

        teamsScrollPane = new JScrollPane();
        teamsScrollPane.setViewportView(jlistTeams);
        jlistTeams.setLayoutOrientation(JList.VERTICAL);
        teamsScrollPane.setBounds(50,50, 125,100);

        viewTeamFrame.add(teamsScrollPane);
        viewTeamFrame.add(selectTeamButton);
        viewTeamFrame.add(viewTeamLabel);
        viewTeamFrame.setSize(250,225);
        viewTeamFrame.setLayout(null);
        viewTeamFrame.setVisible(true);
    }

    public void createViewPlayersFrame() {
        viewPlayersFrame = new JFrame();
        listModelPlayers = new DefaultListModel<>();
        team = teams.getTeams().get(jlistTeams.getSelectedIndex());
        for (Person p : team.getPeople()) {
            listModelPlayers.addElement(p.getName());
        }
        jlistPlayers = new JList<>(listModelPlayers);
        jlistPlayers.setBounds(75,75, 75,75);

        newPlayerButton = new JButton("Add New Player");
        newPlayerButton.setBounds(50,20,120,30);

        editPlayerButton = new JButton("Edit");
        editPlayerButton.setBounds(50,150,120,30);

        playerScrollPane = new JScrollPane();
        playerScrollPane.setViewportView(jlistPlayers);
        jlistPlayers.setLayoutOrientation(JList.VERTICAL);
        playerScrollPane.setBounds(50,50, 125,100);

        viewPlayersFrame.add(playerScrollPane);
        viewPlayersFrame.add(newPlayerButton);
        viewPlayersFrame.add(editPlayerButton);
        viewPlayersFrame.setSize(250,225);
        viewPlayersFrame.setLayout(null);
        viewPlayersFrame.setVisible(true);
    }

    public void addNewPlayer() {
        newPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = JOptionPane.showInputDialog(mainFrame,"Enter Player Name");
                player = new Player(playerName);
                team.addPerson(player);
            }
        });
    }

    public void editPlayer() {
        editPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jlistPlayers.getSelectedIndex() != -1) {
                    Person person = team.getPeople().get(jlistPlayers.getSelectedIndex());
                    player = (Player) person;
                    String playerGoals = JOptionPane.showInputDialog(mainFrame,player.getName()
                            + " has " + player.getGoals()
                            + " goals. How many would you like to add?");
                    player.addGoals(Integer.parseInt(playerGoals));
                    String playerAssists = JOptionPane.showInputDialog(mainFrame,player.getName()
                            + " has " + player.getAssists()
                            + " assists. How many would you like to add?");
                    player.addAssists(Integer.parseInt(playerAssists));
                }
            }
        });
    }

    public void saveAndLoadData() {

        saveDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveTeams();
                JOptionPane.showMessageDialog(mainFrame,"Data Saved.");
            }
        });


        loadDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTeams();
                JOptionPane.showMessageDialog(mainFrame,"Data Loaded.");
            }
        });

    }

    public void addToMainPanel() {
        mainPanel.add(newTeamButton);
        mainPanel.add(viewTeamsButton);
        mainPanel.add(saveDataButton);
        mainPanel.add(loadDataButton);
        mainFrame.add(mainPanel);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);
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
            System.out.println("Loaded teams from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
