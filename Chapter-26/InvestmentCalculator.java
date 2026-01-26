import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class InvestmentCalculator extends Application {

    private TextField tfInvestmentAmount = new TextField();
    private TextField tfInterestRate = new TextField();
    private TextField tfYears = new TextField();
    private TextField tfFutureValue = new TextField();

    @Override
    public void start(Stage stage) {

        Menu menuOperation = new Menu("Operation");
        MenuItem miCalculate = new MenuItem("Calculate");
        MenuItem miExit = new MenuItem("Exit");
        menuOperation.getItems().addAll(miCalculate, miExit);

        MenuBar menuBar = new MenuBar(menuOperation);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        grid.add(new Label("Investment Amount:"), 0, 0);
        grid.add(tfInvestmentAmount, 1, 0);

        grid.add(new Label("Annual Interest Rate (%):"), 0, 1);
        grid.add(tfInterestRate, 1, 1);

        grid.add(new Label("Years:"), 0, 2);
        grid.add(tfYears, 1, 2);

        grid.add(new Label("Future Value:"), 0, 3);
        tfFutureValue.setEditable(false);
        grid.add(tfFutureValue, 1, 3);

        Button btnCalculate = new Button("Calculate");
        grid.add(btnCalculate, 1, 4);

        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(grid);

        btnCalculate.setOnAction(e -> calculateFutureValue());
        miCalculate.setOnAction(e -> calculateFutureValue());
        miExit.setOnAction(e -> stage.close());

        Scene scene = new Scene(root, 420, 280);
        stage.setTitle("Investment Value Calculator");
        stage.setScene(scene);
        stage.show();
    }

    private void calculateFutureValue() {
        try {
            double investmentAmount = Double.parseDouble(tfInvestmentAmount.getText());
            double annualInterestRate = Double.parseDouble(tfInterestRate.getText());
            int years = Integer.parseInt(tfYears.getText());

            double monthlyInterestRate = annualInterestRate / 1200;
            double futureValue = investmentAmount *
                    Math.pow(1 + monthlyInterestRate, years * 12);

            tfFutureValue.setText(String.format("$%.2f", futureValue));
        } catch (NumberFormatException ex) {
            tfFutureValue.setText("Invalid input");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}