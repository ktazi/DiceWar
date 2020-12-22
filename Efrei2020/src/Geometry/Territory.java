package Geometry;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Territory {
    private ArrayList<HexagonCase> tiles;
    private Color color;
    private int idPlayer;

    public Territory(ArrayList<HexagonCase> tiles, Color color, GraphicsContext graphicsContext){
        this.tiles = tiles;
        for (HexagonCase hc : tiles) {
            hc.setColor(color);
        }
        graphicsContext.drawImage(new Image("Assets/one_yellow64.png"), tiles.get(0).getCenterX()-(HexagonCase.SIZE/2), tiles.get(0).getCenterY()-(HexagonCase.SIZE/2));
    }





}
