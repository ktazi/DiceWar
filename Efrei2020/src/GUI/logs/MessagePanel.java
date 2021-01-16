package GUI.logs;

import GUI.Utils.Game;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;

public class MessagePanel extends AnchorPane implements Logs {

    private Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    private double width =  screenBounds.getWidth();
    private double height =  screenBounds.getHeight();

    public MessagePanel(String text){
        Canvas canvas = new Canvas();
        canvas.setHeight((2.5*height)/7);
        canvas.setWidth((1.9*width)/12);
        canvas.getGraphicsContext2D().setFill(Color.BLACK);
        canvas.getGraphicsContext2D().fillRect(0,0,(1.9*width)/12,(2.5*height)/7);
        Text text1 = new Text(text);
        text1.setFont(Font.font("Courier New",12));
        text1.setFill(Color.WHITE);
        text1.setTextAlignment(TextAlignment.CENTER);
        getChildren().add(canvas);
        getChildren().add(text1);
        AnchorPane.setTopAnchor(text1,(0.5*height)/7);
        AnchorPane.setLeftAnchor(text1,((1.9*width)/24)-(text1.getLayoutBounds().getWidth()/2));
    }
}
