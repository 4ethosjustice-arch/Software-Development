import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class BallMover extends Application {
    @Override
    public void start(Stage stage) {
        Circle ball = new Circle(150, 150, 30);
        ball.setStroke(Color.BLACK);
        ball.setFill(Color.WHITE);

        Pane pane = new Pane();
        pane.getChildren().add(ball);

        HBox controls = new HBox(10);
        controls.setAlignment(Pos.BOTTOM_CENTER);
        Button btnLeft = new Button("Left");
        Button btnRight = new Button("Right");
        Button btnUp = new Button("Up");
        Button btnDown = new Button("Down");

        btnLeft.setOnAction(e -> {
            if (ball.getCenterX() > 30)
                ball.setCenterX(ball.getCenterX() - 20);
        });

        btnRight.setOnAction(e -> {
            if (ball.getCenterX() < 270)
                ball.setCenterX(ball.getCenterX() + 20);
        });

        btnUp.setOnAction(e -> {
            if (ball.getCenterY() > 30)
                ball.setCenterY(ball.getCenterY() - 20);
        });

        btnDown.setOnAction(e -> {
            if (ball.getCenterY() < 270)
                ball.setCenterY(ball.getCenterY() + 20);
        });

        controls.getChildren().addAll(btnLeft, btnRight, btnUp, btnDown);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(controls);

        Scene scene = new Scene(borderPane, 300, 300);
        stage.setTitle("Ball Mover");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}