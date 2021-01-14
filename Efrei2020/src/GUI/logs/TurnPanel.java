package GUI.logs;

import GUI.Utils.Game;
import GUI.logs.Logs;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;

import java.io.FileNotFoundException;

public class TurnPanel extends AnchorPane implements Logs {

    private Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    private double width =  screenBounds.getWidth();
    private double height =  screenBounds.getHeight();

    public TurnPanel(Game.COLOR color) throws FileNotFoundException {
        super();
        ImageView banner = new ImageView(Game.getBanner(color));
        Canvas canvas = new Canvas();
        canvas.setHeight((2.5*height)/7);
        canvas.setWidth((1.9*width)/12);
        canvas.getGraphicsContext2D().setFill(Game.turnBackground(color));
        canvas.getGraphicsContext2D().fillRect(0,0,(1.9*width)/12,(2.5*height)/7);
        Text text = new Text(Game.colorToString(color)+"'s\n Turn !");
        text.setFont(Font.font("Courier New",20));
        text.setFill(Color.WHITE);
        VBox vBox = new VBox();
        vBox.resize((1.9*width)/12,(2.5*height)/7);
        vBox.getChildren().add(banner);
        banner.setFitHeight(height/7);
        banner.setFitWidth(width/12);
        vBox.getChildren().add(text);
        vBox.setSpacing((0.5*height)/7);
        getChildren().add(canvas);
        getChildren().add(vBox);
        text.setTextAlignment(TextAlignment.CENTER);
        AnchorPane.setLeftAnchor(vBox, (0.46*width)/12);

    }

}
