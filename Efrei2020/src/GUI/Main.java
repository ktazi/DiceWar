package GUI;

import Geometry.HexagonCase;
import Geometry.Territory;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Dice War");
        BorderPane mainPane = new BorderPane();
        AnchorPane anchorPane = new AnchorPane();
        ScrollPane scrollPane = new ScrollPane();
        Group root = new Group();
        Text label = new Text("Dice war");
        label.setFont(Font.font("Courier New", 30));
        label.setFill(Color.LIGHTGRAY);
        Canvas canvas = new Canvas(3000,3000);
        canvas.getGraphicsContext2D().setFill(Color.BLACK);
        canvas.getGraphicsContext2D().fillRect(0,0,3000,3000);
        TerritoryInfo territoryInfo = new TerritoryInfo();
        PlateauJeu plateauJeu = new PlateauJeu( 24,canvas.getGraphicsContext2D(), territoryInfo);
        ArrayList<ArrayList<HexagonCase>> tiles = plateauJeu.getTiles();
        root.getChildren().add(canvas);
        for(ArrayList<HexagonCase> Tiles : tiles)
            for (HexagonCase tile : Tiles)
                root.getChildren().add(tile.getShape());
        scrollPane.setContent(root);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        anchorPane.getChildren().add(scrollPane);
        anchorPane.getChildren().add(label);
        scrollPane.setMaxSize(1000,500);
        scrollPane.getStylesheets().add(this.getClass().getResource("Style.css").toExternalForm());
        AnchorPane.setTopAnchor(label,40.);
        AnchorPane.setLeftAnchor(label,40.);
        mainPane.setCenter(anchorPane);
        LogPanel logPanel = new LogPanel();
        logPanel.addPanel(new TurnPanel(Game.COLOR.GREEN));
        logPanel.addPanel(new BattlePanel(Game.COLOR.GREEN, Game.COLOR.CYAN, 100,133,false));
        logPanel.addPanel(new TurnPanel(Game.COLOR.YELLOW));
        logPanel.addPanel(new BattlePanel(Game.COLOR.YELLOW, Game.COLOR.MAGENTA,23,8,true));
        logPanel.addPanel(new TurnPanel(Game.COLOR.CYAN));
        mainPane.setBottom(territoryInfo);
        mainPane.setRight(logPanel);
        mainPane.setPadding(new Insets(0));
        primaryStage.setScene(new Scene(mainPane, 1200, 700));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
