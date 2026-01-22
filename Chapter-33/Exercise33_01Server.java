import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Exercise33_01Server extends Application {
    private TextArea ta = new TextArea();

    @Override
    public void start(Stage primaryStage) {
        ta.setWrapText(true);
        Scene scene = new Scene(new ScrollPane(ta), 400, 200);
        primaryStage.setTitle("Loan Server");
        primaryStage.setScene(scene);
        primaryStage.show();

        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(8000)) {
                ta.appendText("Server started at port 8000\n");
                while (true) {
                    Socket socket = serverSocket.accept();
                    ObjectInputStream inputFromClient = new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream outputToClient = new ObjectOutputStream(socket.getOutputStream());

                    Loan loan = (Loan) inputFromClient.readObject();
                    double monthlyPayment = loan.getMonthlyPayment();
                    double totalPayment = loan.getTotalPayment();

                    outputToClient.writeDouble(monthlyPayment);
                    outputToClient.writeDouble(totalPayment);
                    outputToClient.flush();

                    ta.appendText("Processed loan: " + loan.getLoanAmount() + "\n");
                    socket.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}