package ui;

import model.ListOfTeams;
import model.Person;
import model.Player;
import model.Team;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

// This class is the user interface of the application
public class Screen {

    private Player player;
    private Team team;
    private ListOfTeams teams;
    private static final String JSON_STORE = "./data/team.json";
    private String sportsImage = "./data/sports.png";
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);

    private JLabel teamTrackerLabel;
    private JFrame mainFrame;
    private JPanel mainPanel;
    private JButton newTeamButton;
    private JButton viewTeamsButton;
    private JButton saveDataButton;
    private JButton loadDataButton;
    private JFrame viewTeamFrame;
    private JLabel viewTeamLabel;
    private JButton selectTeamButton;
    private DefaultListModel<String> listModelTeams;
    private JList<String> jlistTeams;
    private JScrollPane teamsScrollPane;
    private JFrame viewPlayersFrame;
    private DefaultListModel<String> listModelPlayers;
    private JList<String> jlistPlayers;
    private JButton newPlayerButton;
    private JButton editPlayerButton;
    private JScrollPane playerScrollPane;
    private BufferedImage bufferedImage;
    private ImageIcon displayedImage;
    private JLabel imageLabel;
    private File imageFile;
    private JButton imageButton;
    private JFrame imageFrame;

    public Screen() throws IOException {

        teams = new ListOfTeams();
        createTeamTracker();

    }

    // EFFECTS: creates the Team Tracker user interface
    public void createTeamTracker() throws IOException {
        createMainFrame();
        createMainPanel();
        createButtons();
        createLabel();

        addNewTeam();
        viewTeams();

        saveData();
        loadData();

        displayImage();

        addToMainFrame();
    }

    // EFFECTS: creates Team Tracker main frame
    private void createMainFrame() {
        mainFrame = new JFrame("Team Tracker");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setLayout(new FlowLayout());
        mainFrame.setResizable(true);
        mainFrame.setVisible(true);
        mainFrame.setSize(400, 400);
    }

    // EFFECTS: creates Team Tracker main panel
    private void createMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setBounds(80,80,200,175);
        mainPanel.setBackground(Color.gray);
    }

    // EFFECTS: creates buttons in the main screen
    private void createButtons() {

        newTeamButton = new JButton("Add New Team");
        newTeamButton.setBackground(Color.yellow);

        viewTeamsButton = new JButton("View All Teams");
        viewTeamsButton.setBackground(Color.green);

        saveDataButton = new JButton("Save Data");
        saveDataButton.setBackground(Color.green);

        loadDataButton = new JButton("Load Data");
        loadDataButton.setBackground(Color.green);

        imageButton = new JButton("Display Image");

    }

    private void createLabel() {
        teamTrackerLabel = new JLabel("Team Tracker");
        teamTrackerLabel.setBounds(140, 20, 200, 40);
    }

    // MODIFIES: teams
    // EFFECTS: allows user to add a new team when new team button is clicked
    private void addNewTeam() {
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

    // EFFECTS: allows user to view all teams when view teams button is clicked
    private void viewTeams() {
        viewTeamsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                createViewTeamFrame();
                selectTeam();

            }
        });
    }

    // EFFECTS: allows user to select a team from the view teams screen
    private void selectTeam() {
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

    // EFFECTS: creates view team frame with list of teams
    private void createViewTeamFrame() {
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

    // EFFECTS: creates view players frame with list of players in team
    private void createViewPlayersFrame() {
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

    // MODIFIES: team
    // EFFECTS: allows user to add a new player to a team when button is clicked
    private void addNewPlayer() {
        newPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = JOptionPane.showInputDialog(mainFrame,"Enter Player Name");
                player = new Player(playerName);
                team.addPerson(player);
            }
        });
    }

    // MODIFIES: goals and assists
    // EFFECTS: allows user to add goals or assists to a player
    private void editPlayer() {
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

    private void displayImage() {
        imageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imageFrame = new JFrame();
                imageFile = new File(sportsImage);
                try {
                    bufferedImage = ImageIO.read(imageFile);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                displayedImage = new ImageIcon(bufferedImage);
                imageLabel = new JLabel(displayedImage);
                imageFrame.add(imageLabel);
                imageFrame.setLayout(new FlowLayout());
                imageFrame.setVisible(true);
                imageFrame.setSize(250,225);
            }
        });
    }

    // EFFECTS: saves teams to a file when button is pressed
    private void saveData() {

        saveDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveTeams();
                JOptionPane.showMessageDialog(mainFrame,"Data Saved.");
            }
        });

    }

    // MODIFIES: this
    // EFFECTS: loads teams from a file when button is pressed
    private void loadData() {
        loadDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTeams();
                JOptionPane.showMessageDialog(mainFrame,"Data Loaded.");
            }
        });
    }

    // EFFECTS: adds buttons to main panel
    private void addToMainFrame() {
        mainPanel.add(newTeamButton);
        mainPanel.add(viewTeamsButton);
        mainPanel.add(saveDataButton);
        mainPanel.add(loadDataButton);
        mainPanel.add(imageButton);
        mainFrame.add(teamTrackerLabel);
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
