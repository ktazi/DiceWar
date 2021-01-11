package GUI;

import GUI.BattleBar.BattleBar;
import GUI.logs.LogPanel;
import Gameplay.PlateauJeu;
import Geometry.HexagonCase;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class GamePanel extends AnchorPane {

    public Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    public double width =  screenBounds.getWidth();
    public double height =  screenBounds.getHeight();
    public PlateauJeu plateauJeu;

    public GamePanel(String path){
        super();
        BorderPane mainPane = new BorderPane();
        AnchorPane anchorPane = new AnchorPane();
        ScrollPane scrollPane = new ScrollPane();
        Group root = new Group();
        Button label = new Button("Save game");
        label.setOnMouseClicked(event -> saveGame());
        label.getStylesheets().add(this.getClass().getResource("Style.css").toExternalForm());
        Canvas canvas = new Canvas(2000,2000);
        canvas.getGraphicsContext2D().setFill(Color.rgb(5,20,3));
        canvas.getGraphicsContext2D().fillRect(0,0,2000,2000);
        AnchorPane spritePane = new AnchorPane();
        LogPanel logPanel = null;
        try {
            logPanel = new LogPanel();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BattleBar battleBar = new BattleBar(logPanel);
        plateauJeu = null;
        try {
            plateauJeu = PlateauJeu.deserialize(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        scrollPane.setMaxSize((10*width)/12,(5*height)/7);
        scrollPane.getStylesheets().add(this.getClass().getResource("Style.css").toExternalForm());
        AnchorPane.setTopAnchor(label,(0.4*height)/7);
        AnchorPane.setLeftAnchor(label,(0.4*width)/12);
        mainPane.setCenter(anchorPane);
        AnchorPane bottom = new AnchorPane();
        bottom.getChildren().add(battleBar);
        mainPane.setBottom(bottom);
        mainPane.setRight(logPanel);
        mainPane.setPadding(new Insets(0));
        Canvas background = new Canvas();
        background.setWidth(width);
        background.setHeight(height);
        background.getGraphicsContext2D().setFill(Color.BLACK);
        background.getGraphicsContext2D().fillRect(0,0,width,height);
        getChildren().add(background);
        getChildren().add(mainPane);
    }
    public GamePanel(int nbJoueurs){
        super();
        BorderPane mainPane = new BorderPane();
        AnchorPane anchorPane = new AnchorPane();
        ScrollPane scrollPane = new ScrollPane();
        Group root = new Group();
        Button label = new Button("Save game");
        label.setOnMouseClicked(event -> saveGame());
        label.getStylesheets().add(this.getClass().getResource("Style.css").toExternalForm());
        Canvas canvas = new Canvas(2000,2000);
        canvas.getGraphicsContext2D().setFill(Color.rgb(5,20,3));
        canvas.getGraphicsContext2D().fillRect(0,0,2000,2000);
        AnchorPane spritePane = new AnchorPane();
        LogPanel logPanel = null;
        try {
            logPanel = new LogPanel();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BattleBar battleBar = new BattleBar(logPanel);
        plateauJeu = new PlateauJeu( nbJoueurs*5, canvas.getGraphicsContext2D(), battleBar, nbJoueurs, spritePane);
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
        scrollPane.setMaxSize((10*width)/12,(5*height)/7);
        scrollPane.getStylesheets().add(this.getClass().getResource("Style.css").toExternalForm());
        AnchorPane.setTopAnchor(label,(4.3*height)/7);
        AnchorPane.setLeftAnchor(label,(8.8*width)/12);
        mainPane.setCenter(anchorPane);
        AnchorPane bottom = new AnchorPane();
        bottom.getChildren().add(battleBar);
        mainPane.setBottom(bottom);
        mainPane.setRight(logPanel);
        mainPane.setPadding(new Insets(0));
        Canvas background = new Canvas();
        background.setWidth(width);
        background.setHeight(height);
        background.getGraphicsContext2D().setFill(Color.BLACK);
        background.getGraphicsContext2D().fillRect(0,0,width,height);
        getChildren().add(background);
        getChildren().add(mainPane);
    }

    private void saveGame() {
        File fichier = new File("game_"+new Date().toString()+".sav");
        int i = 1;
        while (fichier.exists()){
            fichier = new File("game_"+new Date().toString()+"("+i+")"+".sav");
        }
        try {
            fichier.createNewFile();
            plateauJeu.serializePlateau(fichier);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
