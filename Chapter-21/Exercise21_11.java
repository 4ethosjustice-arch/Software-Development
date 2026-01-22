import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Exercise21_11 extends Application {

    private Map<String, Integer>[] mapForBoy = new HashMap[10];
    private Map<String, Integer>[] mapForGirl = new HashMap[10];

    private Button btFindRanking = new Button("Find Ranking");
    private ComboBox<Integer> cboYear = new ComboBox<>();
    private ComboBox<String> cboGender = new ComboBox<>();
    private TextField tfName = new TextField();
    private Label lblResult = new Label();

    @Override
    public void start(Stage primaryStage) {
        // Initialize the maps
        for (int i = 0; i < 10; i++) {
            mapForBoy[i] = new HashMap<>();
            mapForGirl[i] = new HashMap<>();
        }

        // Load data from the online files
        loadData();

        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Select a year:"), 0, 0);
        gridPane.add(new Label("Boy or girl?"), 0, 1);
        gridPane.add(new Label("Enter a name:"), 0, 2);
        gridPane.add(cboYear, 1, 0);
        gridPane.add(cboGender, 1, 1);
        gridPane.add(tfName, 1, 2);
        gridPane.add(btFindRanking, 1, 3);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(gridPane);
        borderPane.setBottom(lblResult);
        BorderPane.setAlignment(lblResult, Pos.CENTER);

        Scene scene = new Scene(borderPane, 400, 180);
        primaryStage.setTitle("Baby Name Popularity Ranking");
        primaryStage.setScene(scene);
        primaryStage.show();

        for (int year = 2001; year <= 2010; year++) {
            cboYear.getItems().add(year);
        }
        cboYear.setValue(2001);

        cboGender.getItems().addAll("Male", "Female");
        cboGender.setValue("Male");

        btFindRanking.setOnAction(e -> findRanking());
    }

    private void loadData() {
        try {
            for (int i = 0; i < 10; i++) {
                int year = 2001 + i;
                String url = "http://liveexample.pearsoncmg.com/data/babynamesranking" + year + ".txt";
                Scanner input = new Scanner(new URL(url).openStream());
                while (input.hasNext()) {
                    int rank = input.nextInt();
                    String boyName = input.next();
                    input.nextInt(); // boy count, ignore
                    String girlName = input.next();
                    input.nextInt(); // girl count, ignore

                    mapForBoy[i].put(boyName, rank);
                    mapForGirl[i].put(girlName, rank);
                }
                input.close();
            }
        } catch (Exception ex) {
            lblResult.setText("Error loading data: " + ex.getMessage());
        }
    }

    private void findRanking() {
        int year = cboYear.getValue();
        int index = year - 2001;
        String gender = cboGender.getValue();
        String name = tfName.getText().trim();

        Integer rank = null;
        if (gender.equals("Male")) {
            rank = mapForBoy[index].get(name);
        } else if (gender.equals("Female")) {
            rank = mapForGirl[index].get(name);
        }

        if (rank != null) {
            lblResult.setText(name + " is ranked #" + rank + " in year " + year);
        } else {
            lblResult.setText(name + " is not found in year " + year);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}