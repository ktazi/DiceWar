package Geometry;

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
        this.color = color;
        graphicsContext.drawImage(new Image("Assets/one_yellow64.png"), tiles.get(0).getCenterX()-(HexagonCase.SIZE/2), tiles.get(0).getCenterY()-(HexagonCase.SIZE/2));
    }
    public void enterTerritory(){
        for (HexagonCase tile : tiles){
            tile.setColor(Color.rgb(255,255,255,0.2));
        }
    }
    public void exitTerritory(){
        for(HexagonCase tile : tiles){
            tile.setColor(color);
        }
    }
}
