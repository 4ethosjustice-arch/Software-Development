import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TabPaneDemo extends Application {

    @Override
    public void start(Stage primaryStage) {

        TabPane tabPane = new TabPane();

        Tab tab1 = new Tab("Line");
        StackPane pane1 = new StackPane();
        pane1.getChildren().add(new Line(10, 10, 80, 80));
        tab1.setContent(pane1);

        Tab tab2 = new Tab("Rectangle");
        tab2.setContent(new Rectangle(10, 10, 200, 200));

        Tab tab3 = new Tab("Circle");
        tab3.setContent(new Circle(50, 50, 20));

        Tab tab4 = new Tab("Ellipse");
        tab4.setContent(new Ellipse(10, 10, 100, 80));

        tabPane.getTabs().addAll(tab1, tab2, tab3, tab4);

        RadioButton rbTop = new RadioButton("Top");
        RadioButton rbBottom = new RadioButton("Bottom");
        RadioButton rbLeft = new RadioButton("Left");
        RadioButton rbRight = new RadioButton("Right");

        ToggleGroup group = new ToggleGroup();
        rbTop.setToggleGroup(group);
        rbBottom.setToggleGroup(group);
        rbLeft.setToggleGroup(group);
        rbRight.setToggleGroup(group);

        rbTop.setSelected(true);

        rbTop.setOnAction(e -> tabPane.setSide(Side.TOP));
        rbBottom.setOnAction(e -> tabPane.setSide(Side.BOTTOM));
        rbLeft.setOnAction(e -> tabPane.setSide(Side.LEFT));
        rbRight.setOnAction(e -> tabPane.setSide(Side.RIGHT));

        VBox radioPane = new VBox(10, rbTop, rbBottom, rbLeft, rbRight);
        radioPane.setPadding(new Insets(10));
        radioPane.setAlignment(Pos.CENTER_LEFT);

        BorderPane root = new BorderPane();
        root.setCenter(tabPane);
        root.setRight(radioPane);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("DisplayFigure");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}