import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FlagRisingAnimationThread extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        ImageView imageView = new ImageView("image/us.gif");
        imageView.setX(100);
        imageView.setY(200);
        pane.getChildren().add(imageView);

        Thread animationThread = new Thread(() -> {
            try {
                for (int i = 0; i < 200; i++) {
                    int delta = i;
                    Platform.runLater(() -> imageView.setY(200 - delta));
                    Thread.sleep(50);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        animationThread.setDaemon(true);
        animationThread.start();

        Scene scene = new Scene(pane, 250, 250);
        primaryStage.setTitle("FlagRisingAnimationThread");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}