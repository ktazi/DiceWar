package GUI;

import GUI.BattleBar.BattleBar;
import GUI.logs.LogPanel;
import Gameplay.PlateauJeu;
import Geometry.HexagonCase;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;

public class GamePanel extends AnchorPane {
    public GamePanel(int nbJoueurs){
        super();
        BorderPane mainPane = new BorderPane();
        AnchorPane anchorPane = new AnchorPane();
        ScrollPane scrollPane = new ScrollPane();
        Group root = new Group();
        Text label = new Text("Dice war");
        label.setFont(Font.font("Courier New", 30));
        label.setFill(Color.LIGHTGRAY);
        Canvas canvas = new Canvas(3000,3000);
        canvas.getGraphicsContext2D().setFill(Color.rgb(5,20,3));
        canvas.getGraphicsContext2D().fillRect(0,0,3000,3000);
        AnchorPane spritePane = new AnchorPane();
        LogPanel logPanel = null;
        try {
            logPanel = new LogPanel();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BattleBar battleBar = new BattleBar(logPanel);
        PlateauJeu plateauJeu = new PlateauJeu( 24,canvas.getGraphicsContext2D(), battleBar, nbJoueurs, spritePane);
        ArrayList<ArrayList<HexagonCase>> tiles = plateauJeu.getTiles();
        root.getChildren().add(canvas);
        root.getChildren().add(spritePane);
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
        AnchorPane bottom = new AnchorPane();
        bottom.getChildren().add(battleBar);
        mainPane.setBottom(bottom);
        mainPane.setRight(logPanel);
        mainPane.setPadding(new Insets(0));
        Canvas background = new Canvas();
        background.setWidth(1200);
        background.setHeight(700);
        background.getGraphicsContext2D().setFill(Color.BLACK);
        background.getGraphicsContext2D().fillRect(0,0,1200,700);
        getChildren().add(background);
        getChildren().add(mainPane);
    }
}
