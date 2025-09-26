import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.FadeTransition;

public class Anim extends Application {
    @Override
    public void start(Stage s) {
        Pane p = new Pane();

        Rectangle r = new Rectangle(0, 0, 25, 50);
        r.setFill(Color.BLUE);

        FadeTransition f = new FadeTransition(Duration.millis(3000), r);
        f.setFromValue(1.0);
        f.setToValue(0.1);
        f.setCycleCount(Timeline.INDEFINITE);
        f.setAutoReverse(true);
        f.play();

        Polygon g = new Polygon();
        ObservableList<Double> pts = g.getPoints();

        double w = 200, h = 200, cx = w / 2, cy = h / 2, rad = Math.min(w, h) * 0.4;
        for (int i = 0; i < 5; i++) {
            pts.add(cx + rad * Math.cos((2 * i * Math.PI / 5) - (Math.PI / 5)));
            pts.add(cy + rad * Math.sin((2 * i * Math.PI / 5) - (Math.PI / 5)));
        }

        g.setFill(Color.WHITE);
        g.setStroke(Color.BLACK);
        g.setRotate(18);

        p.getChildren().addAll(g, r);

        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(4000));
        pt.setPath(g);
        pt.setNode(r);
        pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pt.setCycleCount(Timeline.INDEFINITE);
        pt.setAutoReverse(true);
        pt.play();

        g.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) pt.play();
            else if (e.getButton() == MouseButton.SECONDARY) pt.pause();
        });

        Scene sc = new Scene(p, 200, 200);
        s.setTitle("Anim");
        s.setScene(sc);
        s.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}