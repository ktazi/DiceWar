package GUI.logs;

import GUI.Utils.Game;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class MessagePanel extends AnchorPane implements Logs {
    public MessagePanel(String text){
        Canvas canvas = new Canvas();
        canvas.setHeight(250);
        canvas.setWidth(185);
        canvas.getGraphicsContext2D().setFill(Color.BLACK);
        canvas.getGraphicsContext2D().fillRect(0,0,185,250);
        Text text1 = new Text(text);
        text1.setFont(Font.font("Courier New",12));
        text1.setFill(Color.WHITE);
        text1.setTextAlignment(TextAlignment.CENTER);
        getChildren().add(canvas);
        getChildren().add(text1);
        AnchorPane.setTopAnchor(text1,50.);
        AnchorPane.setLeftAnchor(text1,10.);
    }
}
