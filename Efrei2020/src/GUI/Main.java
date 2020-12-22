package GUI;

import Geometry.HexagonCase;
import Geometry.Territory;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Dice War");
        AnchorPane anchorPane = new AnchorPane();
        ScrollPane scrollPane = new ScrollPane();
        Group root = new Group();
        Text label = new Text("Dice war");
        label.setFont(Font.font("Courier New", 30));
        label.setFill(Color.LIGHTGRAY);
        Text textPlayer = new Text("Turn : Yellow");
        textPlayer.setFill(Color.LIGHTGRAY);
        textPlayer.setFont(Font.font("Courier New", 20));
        Canvas canvas = new Canvas(3000,3000);
        canvas.getGraphicsContext2D().setFill(Color.BLACK);
        canvas.getGraphicsContext2D().fillRect(0,0,3000,3000);
        PlateauJeu plateauJeu = new PlateauJeu( 24,canvas.getGraphicsContext2D());
        ArrayList<ArrayList<HexagonCase>> tiles = plateauJeu.getTiles();
        ArrayList<Territory> territories = plateauJeu.getTerritories();
        root.getChildren().add(canvas);
        for(ArrayList<HexagonCase> Tiles : tiles)
            for (HexagonCase tile : Tiles)
                root.getChildren().add(tile.getShape());
        scrollPane.setContent(root);
        anchorPane.getChildren().add(scrollPane);
        anchorPane.getChildren().add(label);
        scrollPane.setMaxSize(1000,500);
        AnchorPane.setTopAnchor(label,40.);
        AnchorPane.setLeftAnchor(label,40.);
        anchorPane.getChildren().add(textPlayer);
        AnchorPane.setTopAnchor(textPlayer,100.);
        AnchorPane.setLeftAnchor(textPlayer,40.);
        primaryStage.setScene(new Scene(anchorPane, 1000, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
