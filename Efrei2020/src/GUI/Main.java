package GUI;

import Geometry.HexagonCase;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Dice War");
        Group root = new Group();
        PlateauJeu plateauJeu = new PlateauJeu( 500, 500, 25);

        ArrayList<ArrayList<HexagonCase>> tiles = plateauJeu.getTiles();
        for(ArrayList<HexagonCase> Tiles : tiles)
            for (HexagonCase tile : Tiles)
                root.getChildren().add(tile.getShape());
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
