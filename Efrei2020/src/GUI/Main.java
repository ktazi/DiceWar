package GUI;

import GUI.BattleBar.BattleBar;
import GUI.BattleBar.DuelPanel;
import GUI.BattleBar.SelectionTerritoryPanel;
import GUI.BattleBar.TerritoryInfo;
import GUI.Utils.Game;
import GUI.logs.BattlePanel;
import GUI.logs.LogPanel;
import GUI.logs.TurnPanel;
import Gameplay.PlateauJeu;
import Geometry.HexagonCase;
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
        GamePanel mainPane = new GamePanel();
        primaryStage.setScene(new Scene(mainPane, 1200, 700));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
