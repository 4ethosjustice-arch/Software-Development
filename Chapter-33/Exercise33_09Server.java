import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;
import java.net.*;

public class Exercise33_09Server extends Application {
    private TextArea taHistory = new TextArea();
    private TextArea taInput = new TextArea();

    @Override
    public void start(Stage primaryStage) {
        taHistory.setWrapText(true);
        taHistory.setEditable(false);
        taInput.setWrapText(true);

        BorderPane pane1 = new BorderPane();
        pane1.setTop(new Label("History"));
        pane1.setCenter(new ScrollPane(taHistory));
        BorderPane pane2 = new BorderPane();
        pane2.setTop(new Label("New Message"));
        pane2.setCenter(new ScrollPane(taInput));

        VBox vBox = new VBox(5);
        vBox.getChildren().addAll(pane1, pane2);

        Scene scene = new Scene(vBox, 300, 300);
        primaryStage.setTitle("Chat Server");
        primaryStage.setScene(scene);
        primaryStage.show();

        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(8000);
                 Socket socket = serverSocket.accept();
                 BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter toClient = new PrintWriter(socket.getOutputStream(), true)) {

                taHistory.appendText("Client connected\n");

                taInput.setOnKeyPressed(event -> {
                    switch (event.getCode()) {
                        case ENTER:
                            String message = taInput.getText().trim();
                            if (!message.isEmpty()) {
                                toClient.println(message);
                                taHistory.appendText("Server: " + message + "\n");
                                taInput.clear();
                            }
                            event.consume();
                            break;
                    }
                });

                String message;
                while ((message = fromClient.readLine()) != null) {
                    String msg = "Client: " + message + "\n";
                    Platform.runLater(() -> taHistory.appendText(msg));
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