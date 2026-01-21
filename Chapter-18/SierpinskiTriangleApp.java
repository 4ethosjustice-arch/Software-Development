import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class SierpinskiTriangleApp extends Application {

    private SierpinskiPane trianglePane = new SierpinskiPane();

    @Override
    public void start(Stage stage) {
        Button btIncrease = new Button("+");
        Button btDecrease = new Button("-");

        btIncrease.setOnAction(e -> trianglePane.increaseOrder());
        btDecrease.setOnAction(e -> trianglePane.decreaseOrder());

        HBox controls = new HBox(10, btDecrease, btIncrease);
        controls.setAlignment(Pos.CENTER);

        BorderPane pane = new BorderPane();
        pane.setCenter(trianglePane);
        pane.setBottom(controls);

        Scene scene = new Scene(pane, 500, 500);
        stage.setTitle("Sierpinski Triangle");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class SierpinskiPane extends Pane {

    private int order = 0;

    public SierpinskiPane() {
        draw();
    }

    public void increaseOrder() {
        order++;
        draw();
    }

    public void decreaseOrder() {
        if (order > 0) {
            order--;
            draw();
        }
    }

    private void draw() {
        getChildren().clear();

        double width = getWidth();
        double height = getHeight();

        if (width == 0 || height == 0) {
            return;
        }

        drawTriangle(
            order,
            new double[] {
                width / 2, 10,
                10, height - 10,
                width - 10, height - 10
            }
        );
    }

    private void drawTriangle(int order, double[] points) {
        if (order == 0) {
            Polygon triangle = new Polygon(points);
            triangle.setStroke(Color.BLACK);
            triangle.setFill(Color.TRANSPARENT);
            getChildren().add(triangle);
        } else {
            double x1 = points[0];
            double y1 = points[1];
            double x2 = points[2];
            double y2 = points[3];
            double x3 = points[4];
            double y3 = points[5];

            double mx12 = (x1 + x2) / 2;
            double my12 = (y1 + y2) / 2;
            double mx23 = (x2 + x3) / 2;
            double my23 = (y2 + y3) / 2;
            double mx31 = (x3 + x1) / 2;
            double my31 = (y3 + y1) / 2;

            drawTriangle(order - 1, new double[] {
                x1, y1,
                mx12, my12,
                mx31, my31
            });

            drawTriangle(order - 1, new double[] {
                mx12, my12,
                x2, y2,
                mx23, my23
            });

            drawTriangle(order - 1, new double[] {
                mx31, my31,
                mx23, my23,
                x3, y3
            });
        }
    }

    @Override
    protected void setWidth(double value) {
        super.setWidth(value);
        draw();
    }

    @Override
    protected void setHeight(double value) {
        super.setHeight(value);
        draw();
    }
}