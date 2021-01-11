package GUI.menu;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;


public class testMenu extends Application {

    public Rectangle2D screenBounds = Screen.getPrimary().getBounds();


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Dice War");

        Menu mainPane = new Menu();


        primaryStage.setScene(new Scene(mainPane, screenBounds.getWidth()-10, screenBounds.getHeight()));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
