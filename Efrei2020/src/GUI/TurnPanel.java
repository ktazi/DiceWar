package GUI;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.FileNotFoundException;

public class TurnPanel extends AnchorPane implements Logs {

    public TurnPanel(Game.COLOR color) throws FileNotFoundException {
        super();
        ImageView banner = new ImageView(Game.getBanner(color));
        Canvas canvas = new Canvas();
        canvas.setHeight(250);
        canvas.setWidth(185);
        canvas.getGraphicsContext2D().setFill(Game.turnBackground(color));
        canvas.getGraphicsContext2D().fillRect(0,0,185,250);
        Text text = new Text(Game.colorToString(color)+"'s\n Turn !");
        text.setFont(Font.font("Courier New",20));
        text.setFill(Color.WHITE);
        VBox vBox = new VBox();
        vBox.resize(185,250);
        vBox.getChildren().add(banner);
        vBox.getChildren().add(text);
        vBox.setSpacing(50);
        getChildren().add(canvas);
        getChildren().add(vBox);
        text.setTextAlignment(TextAlignment.CENTER);
        AnchorPane.setLeftAnchor(vBox, 46.);

    }

}
