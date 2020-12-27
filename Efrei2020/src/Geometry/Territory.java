package Geometry;

import GUI.Game;
import GUI.TerritoryInfo;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Territory {
    private ArrayList<HexagonCase> tiles;
    private Color color;
    private int idPlayer;
    private int force;
    private TerritoryInfo info;

    public Territory(ArrayList<HexagonCase> tiles, Color color, GraphicsContext graphicsContext, TerritoryInfo territoryInfo){
        force = 6;
        idPlayer = 1;
        this.tiles = tiles;
        for (HexagonCase hc : tiles) {
            hc.setColor(color);
        }
        info = territoryInfo;
        this.color = color;
        graphicsContext.drawImage(new Image("Assets/one_yellow64.png"), tiles.get(0).getCenterX()-(HexagonCase.SIZE/2), tiles.get(0).getCenterY()-(HexagonCase.SIZE/2));
    }
    public void enterTerritory() throws FileNotFoundException {
        for (HexagonCase tile : tiles){
            tile.setColor(Color.rgb(255,255,255,0.2));
            info.updateInfo(force, Game.COLOR.RED);
        }
    }
    public void exitTerritory(){
        for(HexagonCase tile : tiles){
            tile.setColor(color);
        }
    }
}
