import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Random;

public class TestClock extends Application {
    @Override
    public void start(Stage stage) {
        Random rand = new Random();
        int hour = rand.nextInt(12);
        int minute = rand.nextBoolean() ? 0 : 30;

        ClockPane clock = new ClockPane(hour, minute);
        clock.setSecondHandVisible(false);

        Scene scene = new Scene(clock, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Random Clock");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class ClockPane extends Pane {
    private int hour;
    private int minute;
    private int second;

    private BooleanProperty hourHandVisible = new SimpleBooleanProperty(true);
    private BooleanProperty minuteHandVisible = new SimpleBooleanProperty(true);
    private BooleanProperty secondHandVisible = new SimpleBooleanProperty(true);

    public ClockPane(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
        paintClock();
    }

    public boolean isHourHandVisible() { return hourHandVisible.get(); }
    public void setHourHandVisible(boolean visible) { this.hourHandVisible.set(visible); paintClock(); }

    public boolean isMinuteHandVisible() { return minuteHandVisible.get(); }
    public void setMinuteHandVisible(boolean visible) { this.minuteHandVisible.set(visible); paintClock(); }

    public boolean isSecondHandVisible() { return secondHandVisible.get(); }
    public void setSecondHandVisible(boolean visible) { this.secondHandVisible.set(visible); paintClock(); }

    private void paintClock() {
        double radius = Math.min(getWidth(), getHeight()) * 0.4;
        double centerX = getWidth() / 2;
        double centerY = getHeight() / 2;

        getChildren().clear();

        javafx.scene.shape.Circle circle = new javafx.scene.shape.Circle(centerX, centerY, radius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);

        Text t12 = new Text(centerX - 5, centerY - radius + 12, "12");
        Text t3 = new Text(centerX + radius - 10, centerY + 3, "3");
        Text t6 = new Text(centerX - 3, centerY + radius - 3, "6");
        Text t9 = new Text(centerX - radius + 3, centerY + 5, "9");

        if (isHourHandVisible()) {
            double hLength = radius * 0.5;
            double hourX = centerX + hLength * Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
            double hourY = centerY - hLength * Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
            getChildren().add(new Line(centerX, centerY, hourX, hourY));
        }

        if (isMinuteHandVisible()) {
            double mLength = radius * 0.65;
            double minuteX = centerX + mLength * Math.sin(minute * (2 * Math.PI / 60));
            double minuteY = centerY - mLength * Math.cos(minute * (2 * Math.PI / 60));
            getChildren().add(new Line(centerX, centerY, minuteX, minuteY));
        }

        if (isSecondHandVisible()) {
            double sLength = radius * 0.8;
            double secondX = centerX + sLength * Math.sin(second * (2 * Math.PI / 60));
            double secondY = centerY - sLength * Math.cos(second * (2 * Math.PI / 60));
            Line sLine = new Line(centerX, centerY, secondX, secondY);
            sLine.setStroke(Color.RED);
            getChildren().add(sLine);
        }

        getChildren().addAll(circle, t12, t3, t6, t9);
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        paintClock();
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        paintClock();
    }
}