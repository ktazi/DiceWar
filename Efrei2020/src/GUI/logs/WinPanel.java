package GUI.logs;

import GUI.Utils.Game;
import GUI.menu.Menu;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class WinPanel extends AnchorPane implements Logs {

    private Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    private double width =  screenBounds.getWidth();
    private double height =  screenBounds.getHeight();

    public WinPanel(Game.COLOR color) throws FileNotFoundException {
        super();
        ImageView banner = new ImageView(Game.getBanner(color));
        Canvas canvas = new Canvas();
        canvas.setHeight((2.5*height)/7);
        canvas.setWidth((1.9*width)/12);
        canvas.getGraphicsContext2D().setFill(Game.turnBackground(color));
        canvas.getGraphicsContext2D().fillRect(0,0,(1.9*width)/12,(2.5*height)/7);
        Text text = new Text(Game.colorToString(color)+"\nhas won !");
        Button toMenu = new Button("Back to Menu");
        toMenu.setOnMouseClicked(event -> {

            Stage newStage  = new Stage();
            newStage.setTitle("Dice War");
            Menu mainPane = null;
            try {
                mainPane = new Menu();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            newStage.setScene(new Scene(mainPane,  screenBounds.getWidth()-10, screenBounds.getHeight()));
            newStage.setResizable(false);
            newStage.show();

            Stage actuelStage = (Stage) this.getScene().getWindow();
            actuelStage.close();
        });
        text.setFont(Font.font("Courier New",20));
        text.setFill(Color.WHITE);
        VBox vBox = new VBox();
        vBox.resize((1.9*width)/12,(2.5*height)/7);
        vBox.getChildren().add(banner);
        banner.setFitHeight(height/7);
        banner.setFitWidth(width/12);
        vBox.getChildren().add(text);
        vBox.getChildren().add(toMenu);
        vBox.setSpacing((0.5*height)/10);
        getChildren().add(canvas);
        getChildren().add(vBox);
        text.setTextAlignment(TextAlignment.CENTER);
        AnchorPane.setLeftAnchor(vBox, (0.46*width)/12);
        vBox.setAlignment(Pos.CENTER);
    }
}
