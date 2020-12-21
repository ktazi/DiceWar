package Geometry;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Territory {
    private ArrayList<HexagonCase> tiles;
    private Color color;

    public Territory(ArrayList<HexagonCase> tiles, Color color){
        this.tiles = tiles;
        for (HexagonCase hc : tiles)
        {
            hc.setColor(color);
        }
    }

}
