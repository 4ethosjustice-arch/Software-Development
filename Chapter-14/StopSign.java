import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ShowPolygon extends Application {
    @Override
    public void start(Stage primaryStage) {   
        Scene scene = new Scene(new MyPolygon(), 400, 400);
        primaryStage.setTitle("STOP Sign");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class MyPolygon extends Pane {
    private void paint() {
        Polygon polygon = new Polygon();
        polygon.setFill(Color.RED);
        polygon.setStroke(Color.BLACK);
        ObservableList<Double> list = polygon.getPoints();

        double centerX = getWidth() / 2, centerY = getHeight() / 2;
        double radius = Math.min(getWidth(), getHeight()) * 0.4;

        int s = 8;
        for (int i = 0; i < s; i++) {
            list.add(centerX + radius * Math.cos(2 * i * Math.PI / s)); 
            list.add(centerY - radius * Math.sin(2 * i * Math.PI / s));
        }

        Text stopText = new Text("STOP");
        stopText.setFill(Color.WHITE);
        stopText.setFont(Font.font("Arial", FontWeight.BOLD, radius * 0.6));
        stopText.setX(centerX - stopText.getLayoutBounds().getWidth() / 2);
        stopText.setY(centerY + stopText.getLayoutBounds().getHeight() / 4);

        getChildren().clear();
        getChildren().addAll(polygon, stopText);
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        paint();
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        paint();
    }
}