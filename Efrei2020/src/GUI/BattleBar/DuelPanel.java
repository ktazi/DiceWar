package GUI.BattleBar;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;


public class DuelPanel extends AnchorPane {
    public DuelPanel() {
        super();
        Canvas canvas = new Canvas();
        canvas.getGraphicsContext2D().setFill(Color.BLACK);
        canvas.setHeight(200);
        canvas.setWidth(1200);
        canvas.getGraphicsContext2D().fillRect(0,0,1200,200);
        ImageView battleImage = new ImageView(new Image("Assets/battle.png"));
        getChildren().add(canvas);
        getChildren().add(battleImage);
        AnchorPane.setLeftAnchor(battleImage,450.);
        ArrayList<ImageView> dices = new ArrayList<>();
        for (int h = 0; h<2;h++){
            for (int i = 0; i < 2; i++){
                for (int j = 0; j< 4; j++){
                    dices.add(new ImageView(new Image("Assets/dice0.png")));
                    getChildren().add(dices.get((h*8)+(i*4) + j));
                    AnchorPane.setLeftAnchor(dices.get((h*8)+(i*4) + j),30. + (40.*j) + (h*1000));
                    AnchorPane.setTopAnchor(dices.get((h*8)+(i*4) + j),60. + (40.*i));
                }
            }
        }
    }

    public boolean duel(){
        return true;
    }


}
