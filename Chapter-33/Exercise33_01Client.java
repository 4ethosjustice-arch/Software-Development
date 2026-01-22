import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Exercise33_01Client extends Application {
    private TextField tfAnnualInterestRate = new TextField();
    private TextField tfNumOfYears = new TextField();
    private TextField tfLoanAmount = new TextField();
    private Button btSubmit = new Button("Submit");
    private TextArea ta = new TextArea();

    @Override
    public void start(Stage primaryStage) {
        ta.setWrapText(true);

        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Annual Interest Rate"), 0, 0);
        gridPane.add(new Label("Number Of Years"), 0, 1);
        gridPane.add(new Label("Loan Amount"), 0, 2);
        gridPane.add(tfAnnualInterestRate, 1, 0);
        gridPane.add(tfNumOfYears, 1, 1);
        gridPane.add(tfLoanAmount, 1, 2);
        gridPane.add(btSubmit, 2, 1);
        tfAnnualInterestRate.setAlignment(Pos.BASELINE_RIGHT);
        tfNumOfYears.setAlignment(Pos.BASELINE_RIGHT);
        tfLoanAmount.setAlignment(Pos.BASELINE_RIGHT);

        BorderPane pane = new BorderPane();
        pane.setTop(gridPane);
        pane.setCenter(new ScrollPane(ta));

        btSubmit.setOnAction(e -> {
            try (Socket socket = new Socket("localhost", 8000);
                 ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream())) {

                double rate = Double.parseDouble(tfAnnualInterestRate.getText());
                int years = Integer.parseInt(tfNumOfYears.getText());
                double amount = Double.parseDouble(tfLoanAmount.getText());

                Loan loan = new Loan(rate, years, amount);
                toServer.writeObject(loan);
                toServer.flush();

                double monthlyPayment = fromServer.readDouble();
                double totalPayment = fromServer.readDouble();

                ta.appendText("Monthly Payment: " + monthlyPayment + "\n");
                ta.appendText("Total Payment: " + totalPayment + "\n");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Scene scene = new Scene(pane, 400, 250);
        primaryStage.setTitle("Loan Client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}