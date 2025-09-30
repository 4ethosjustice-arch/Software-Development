import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.*;

class Suspect {
    private final String name;
    public Suspect(String name) { this.name = name; }
    public String getName() { return name; }
}

class Weapon {
    private final String name;
    public Weapon(String name) { this.name = name; }
    public String getName() { return name; }
}

class Room {
    private final String name;
    public Room(String name) { this.name = name; }
    public String getName() { return name; }
}

class Solution {
    private final Suspect suspect;
    private final Weapon weapon;
    private final Room room;

    public Solution(Suspect suspect, Weapon weapon, Room room) {
        this.suspect = suspect;
        this.weapon = weapon;
        this.room = room;
    }

    public boolean matches(Suspect s, Weapon w, Room r) {
        return suspect.getName().equalsIgnoreCase(s.getName()) &&
               weapon.getName().equalsIgnoreCase(w.getName()) &&
               room.getName().equalsIgnoreCase(r.getName());
    }

    public Suspect getSuspect() { return suspect; }
    public Weapon getWeapon() { return weapon; }
    public Room getRoom() { return room; }
}

public class MiniClueGUI extends Application {
    private final Random rand = new Random();
    private Solution solution;

    private ComboBox<String> suspectBox, weaponBox, roomBox;
    private TextArea gameLog = new TextArea();

    private int playerAttempts = 3;
    private int computerAttempts = 0;

    private final String[] suspects = {"Miss Scarlet", "Colonel Mustard", "Professor Plum"};
    private final String[] weapons = {"Candlestick", "Revolver", "Knife"};
    private final String[] rooms = {"Kitchen", "Library", "Ballroom"};

    @Override
    public void start(Stage primaryStage) {
        Suspect s = new Suspect(suspects[rand.nextInt(suspects.length)]);
        Weapon w = new Weapon(weapons[rand.nextInt(weapons.length)]);
        Room r = new Room(rooms[rand.nextInt(rooms.length)]);
        solution = new Solution(s, w, r);

        suspectBox = new ComboBox<>();
        suspectBox.getItems().addAll(suspects);

        weaponBox = new ComboBox<>();
        weaponBox.getItems().addAll(weapons);

        roomBox = new ComboBox<>();
        roomBox.getItems().addAll(rooms);

        Button guessBtn = new Button("Submit Guess");
        guessBtn.setOnAction(e -> handlePlayerGuess());

        VBox root = new VBox(10,
            new Label("Choose Suspect:"), suspectBox,
            new Label("Choose Weapon:"), weaponBox,
            new Label("Choose Room:"), roomBox,
            guessBtn,
            new Label("Game Log:"), gameLog
        );

        gameLog.setEditable(false);
        gameLog.setWrapText(true);
        gameLog.appendText("=== Welcome to Mini Clue ===\n");
        gameLog.appendText("You have 3 chances to solve the case before the computer does!\n");

        Scene scene = new Scene(root, 450, 500);
        primaryStage.setTitle("Mini Clue");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handlePlayerGuess() {
        if (playerAttempts <= 0) {
            gameLog.appendText("\nNo attempts left! Computer wins.");
            return;
        }

        String suspect = suspectBox.getValue();
        String weapon = weaponBox.getValue();
        String room = roomBox.getValue();

        if (suspect == null || weapon == null || room == null) {
            gameLog.appendText("Please select all three options!\n");
            return;
        }

        playerAttempts--;

        if (solution.matches(new Suspect(suspect), new Weapon(weapon), new Room(room))) {
            gameLog.appendText("\nYou solved it! " + solution.getSuspect().getName() +
                " with the " + solution.getWeapon().getName() +
                " in the " + solution.getRoom().getName() + ".\nYOU WIN!");
            return;
        } else {
            gameLog.appendText("\nWrong guess! Attempts left: " + playerAttempts + "\n");
            if (suspect.equalsIgnoreCase(solution.getSuspect().getName())) {
                gameLog.appendText("Correct suspect!\n");
            }
            if (weapon.equalsIgnoreCase(solution.getWeapon().getName())) {
                gameLog.appendText("Correct weapon!\n");
            }
            if (room.equalsIgnoreCase(solution.getRoom().getName())) {
                gameLog.appendText("Correct room!\n");
            }
        }

        if (playerAttempts == 0) {
            gameLog.appendText("\nYou're out of attempts! Computer's turn...\n");
        }

        handleComputerTurn();
    }

    private void handleComputerTurn() {
        computerAttempts++;
        if (computerAttempts < 3) {
            String compSuspect = suspects[rand.nextInt(suspects.length)];
            String compWeapon = weapons[rand.nextInt(weapons.length)];
            String compRoom = rooms[rand.nextInt(rooms.length)];

            if (compSuspect.equals(solution.getSuspect().getName()) &&
                compWeapon.equals(solution.getWeapon().getName()) &&
                compRoom.equals(solution.getRoom().getName())) {
                int forceWrong = rand.nextInt(3);
                if (forceWrong == 0) {
                    do { compSuspect = suspects[rand.nextInt(suspects.length)]; }
                    while (compSuspect.equals(solution.getSuspect().getName()));
                } else if (forceWrong == 1) {
                    do { compWeapon = weapons[rand.nextInt(weapons.length)]; }
                    while (compWeapon.equals(solution.getWeapon().getName()));
                } else {
                    do { compRoom = rooms[rand.nextInt(rooms.length)]; }
                    while (compRoom.equals(solution.getRoom().getName()));
                }
            }

            int correct = 0;
            if (compSuspect.equals(solution.getSuspect().getName())) correct++;
            if (compWeapon.equals(solution.getWeapon().getName())) correct++;
            if (compRoom.equals(solution.getRoom().getName())) correct++;

            gameLog.appendText("\n--- Computer Turn (" + computerAttempts + ") ---\n");
            gameLog.appendText("Computer guessed: " + compSuspect +
                " with the " + compWeapon + " in the " + compRoom + ".\n");
            gameLog.appendText("Computer got " + correct + " correct.\n");

        } else {
            gameLog.appendText("\n--- Computer Turn (" + computerAttempts + ") ---\n");
            gameLog.appendText("Computer guessed: " + solution.getSuspect().getName() +
                " with the " + solution.getWeapon().getName() +
                " in the " + solution.getRoom().getName() + ".\n");
            gameLog.appendText("The computer solved the case!\nCOMPUTER WINS.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}