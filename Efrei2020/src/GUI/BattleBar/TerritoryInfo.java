package GUI.BattleBar;

import GUI.Utils.Game;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TerritoryInfo extends HBox {

    private Game.COLOR color;
    private int force;
    private TextFlow textFlow;
    private Canvas canvas;
    private ImageView imageView2;
    private boolean blanck;

    public TerritoryInfo() {
        blanck = true;
        color = Game.COLOR.GREEN;
        force = 1;
        AnchorPane anchorPane = new AnchorPane();
        canvas = new Canvas();
        canvas.setHeight(200);
        canvas.setWidth(400);
        canvas.getGraphicsContext2D().setFill(Color.BLACK);
        canvas.getGraphicsContext2D().fillRect(0,0,400,200);
        anchorPane.getChildren().add(canvas);
        VBox vBox = new VBox();
        textFlow = new TextFlow();
        Text colortext = new Text("-"+"\n\n");
        colortext.setFill(Color.WHITE);
        colortext.setFont(Font.font("Courier New",15));
        Text forcetext = new Text("-"+"\n\n");
        forcetext.setFill(Color.WHITE);
        forcetext.setFont(Font.font("Courier New",15));
        Text colortext1 = new Text("Team : ");
        colortext1.setFill(Color.WHITE);
        colortext1.setFont(Font.font("Courier New",15));
        Text forcetext1 = new Text("Force : ");
        forcetext1.setFill(Color.WHITE);
        forcetext1.setFont(Font.font("Courier New",15));
        textFlow.getChildren().addAll(colortext1,colortext,forcetext1,forcetext);
        vBox.getChildren().add(textFlow);
        anchorPane.getChildren().add(textFlow);
        AnchorPane.setTopAnchor(textFlow,10.);
        AnchorPane.setLeftAnchor(textFlow,10.);
        getChildren().add(anchorPane);
        imageView2 = new ImageView(new Image("Assets/blank.png"));
        anchorPane.getChildren().add(imageView2);
        AnchorPane.setLeftAnchor(imageView2,200.);
        AnchorPane.setTopAnchor(imageView2,30.);
    }

    public void updateInfo(int force, Game.COLOR color) throws FileNotFoundException {
        blanck = false;
        this.color = color;
        this.force = force;
        canvas.getGraphicsContext2D().setFill(Game.turnBackground(color));
        canvas.getGraphicsContext2D().fillRect(0,0,400,200);
        Text colortext = new Text(Game.colorToString(color)+"\n\n");
        colortext.setFill(Game.colorRgb(color));
        colortext.setFont(Font.font("Courier New",15));
        Text forcetext = new Text(Integer.toString(force)+"\n\n");
        forcetext.setFill(Game.colorRgb(color));
        forcetext.setFont(Font.font("Courier New",15));
        Text colortext1 = new Text("Team : ");
        colortext1.setFill(Color.WHITE);
        colortext1.setFont(Font.font("Courier New",15));
        Text forcetext1 = new Text("Force : ");
        forcetext1.setFill(Color.WHITE);
        forcetext1.setFont(Font.font("Courier New",15));
        textFlow.getChildren().clear();
        textFlow.getChildren().addAll(colortext1,colortext,forcetext1,forcetext);
        FileInputStream inputstream2 = new FileInputStream("Efrei2020/src/Assets/"+Game.forceToString(force)+"_"+Game.colorToString(color)+".png");
        imageView2.setImage(new Image(inputstream2));
    }

    public void setBlank(){
        blanck = true;
        canvas.getGraphicsContext2D().setFill(Color.BLACK);
        canvas.getGraphicsContext2D().fillRect(0,0,400,200);
        Text colortext = new Text("-\n\n");
        colortext.setFill(Color.WHITE);
        colortext.setFont(Font.font("Courier New",15));
        Text forcetext = new Text("-\n\n");
        forcetext.setFill(Color.WHITE);
        forcetext.setFont(Font.font("Courier New",15));
        Text colortext1 = new Text("Team : ");
        colortext1.setFill(Color.WHITE);
        colortext1.setFont(Font.font("Courier New",15));
        Text forcetext1 = new Text("Force : ");
        forcetext1.setFill(Color.WHITE);
        forcetext1.setFont(Font.font("Courier New",15));
        textFlow.getChildren().clear();
        textFlow.getChildren().addAll(colortext1,colortext,forcetext1,forcetext);
        imageView2.setImage(new Image("Assets/Blank.png"));
    }

    public boolean isBlanck() {
        return blanck;
    }
}