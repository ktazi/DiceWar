package GUI;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class DuelPanel extends AnchorPane {


    public DuelPanel() throws IOException {
        super();
        Canvas canvas = new Canvas();
        canvas.getGraphicsContext2D().setFill(Color.BLACK);
        canvas.setHeight(200);
        canvas.setWidth(600);
        canvas.getGraphicsContext2D().fillRect(0,0,600,200);
        FileInputStream inputstream2 = new FileInputStream("Efrei2020/src/Assets/battle.png");
        ImageView battleImage = new ImageView(new Image(inputstream2));
        inputstream2.close();
        getChildren().add(canvas);
        getChildren().add(battleImage);
        AnchorPane.setLeftAnchor(battleImage,150.);
        ArrayList<ImageView> dices = new ArrayList<>();
        for (int i = 0; i < 2; i++){
            for (int j = 0; j< 4; j++){
                inputstream2 = new FileInputStream("Efrei2020/src/Assets/dice2.png");
                dices.add(new ImageView(new Image(inputstream2)));
                inputstream2.close();
                getChildren().add(dices.get((i*4) + j));
                AnchorPane.setLeftAnchor(dices.get((i*4) + j),30. + (40.*j));
                AnchorPane.setTopAnchor(dices.get((i*4) + j),60. + (40.*i));
            }
        }
        for (int i = 0; i < 2; i++){
            for (int j = 0; j< 4; j++){
                inputstream2 = new FileInputStream("Efrei2020/src/Assets/dice4.png");
                dices.add(new ImageView(new Image(inputstream2)));
                inputstream2.close();
                getChildren().add(dices.get(8 + (i*4) + j));
                AnchorPane.setLeftAnchor(dices.get(8 +(i*4) + j),420. + (40.*j));
                AnchorPane.setTopAnchor(dices.get(8 + (i*4) + j),60. + (40.*i));
            }
        }

    }




}
