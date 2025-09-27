import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TimerApp extends Application {

    private int initialValue = 5;
    private Timeline clock;
    private Label display = new Label();
    private IntegerProperty secondsLeft = new SimpleIntegerProperty(initialValue);

    @Override
    public void start(Stage stage) {
        stage.setTitle("Simple Countdown");
        Group group = new Group();
        Scene scene = new Scene(group, 320, 240);

        TextField inputField = new TextField();
        inputField.setAlignment(Pos.CENTER);
        inputField.setPrefWidth(50);

        MediaPlayer player = new MediaPlayer(
                new Media("https://liveexample.pearsoncmg.com/common/audio/anthem/anthem0.mp3")
        );

        inputField.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                initialValue = Integer.parseInt(inputField.getText());
            }
            if (clock != null) {
                clock.stop();
            }
            secondsLeft.set(initialValue);
            clock = new Timeline();
            clock.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(initialValue + 1), new KeyValue(secondsLeft, 0))
            );
            clock.playFromStart();
            clock.setOnFinished(e -> player.play());
        });

        display.textProperty().bind(secondsLeft.asString());
        display.setTextFill(Color.DARKBLUE);
        display.setStyle("-fx-font-size: 3.8em;");

        VBox layout = new VBox(25, inputField, display);
        layout.setAlignment(Pos.CENTER);
        layout.setPrefWidth(scene.getWidth());
        layout.setLayoutY(35);

        group.getChildren().add(layout);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}