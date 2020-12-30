package GUI.logs;

import GUI.Utils.Game;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class BattlePanel extends AnchorPane implements Logs {

    public BattlePanel(Game.COLOR player1, Game.COLOR player2, int points1, int points2, boolean victory){
        super();
        Canvas canvas = new Canvas();
        canvas.setHeight(250);
        canvas.setWidth(185);
        canvas.getGraphicsContext2D().setFill(Color.BLACK);
        canvas.getGraphicsContext2D().fillRect(0,0,185,250);
        TextFlow textFlow = new TextFlow();
        Text text = new Text(Game.colorToString(player1));
        text.setFont(Font.font("Courier New",15));
        text.setFill(Game.colorRgb(player1));
        Text text9 = new Text(Game.colorToString(player1));
        text9.setFont(Font.font("Courier New",15));
        text9.setFill(Game.colorRgb(player1));
        Text text2 = new Text("\nbattles against \n");
        text2.setFont(Font.font("Courier New",15));
        text2.setFill(Color.WHITE);
        Text text3 = new Text(Game.colorToString(player2)+"\n\n");
        text3.setFont(Font.font("Courier New",15));
        text3.setFill(Game.colorRgb(player2));
        textFlow.getChildren().addAll(text,text2,text3);

        TextFlow textFlow2 = new TextFlow();
        Text text4 = new Text(victory?" won\n\n" : " lost\n\n");
        text4.setFont(Font.font("Courier New",15));
        text4.setFill(Color.WHITE);
        Text text5 = new Text(Integer.toString(points1));
        text5.setFont(Font.font("Courier New",15));
        text5.setFill(Game.colorRgb(player1));
        Text text6 = new Text(" vs ");
        text6.setFont(Font.font("Courier New",15));
        text6.setFill(Color.WHITE);
        Text text7 = new Text(Integer.toString(points2));
        text7.setFont(Font.font("Courier New",15));
        text7.setFill(Game.colorRgb(player2));
        textFlow2.getChildren().addAll(text9,text4,text5,text6,text7);

        VBox vBox = new VBox();
        vBox.resize(185,250);
        vBox.getChildren().add(textFlow);
        vBox.getChildren().add(textFlow2);
        getChildren().add(canvas);
        getChildren().add(vBox);
        textFlow.setTextAlignment(TextAlignment.CENTER);
        textFlow2.setTextAlignment(TextAlignment.CENTER);
        AnchorPane.setLeftAnchor(vBox, 20.);
        AnchorPane.setTopAnchor(vBox, 40.);
    }






}
