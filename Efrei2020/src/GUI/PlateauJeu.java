package GUI;

import Geometry.HexagonCase;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javax.swing.*;
import java.util.*;

public class PlateauJeu{
    private GraphicsContext graphicsContext;
    public PlateauJeu(int dimx, int dimy, GraphicsContext gc){
        graphicsContext = gc;
    }

    public void drawthings(){
        double x0 = 10;
        double y0 = 10;
        ArrayList<HexagonCase> tiles = new ArrayList<>();
        for (int i = 0; i < 100; i++){
            for(int j = 0; j <100; j++){
                tiles.add(new HexagonCase(x0+(i*Math.sqrt(3)* HexagonCase.SIZE)+(j%2==0?0:HexagonCase.SIZE*Math.sqrt(3)/2) , y0 + ((j*6* HexagonCase.SIZE)/(double)4)));
            }
        }
        graphicsContext.setStroke(Color.BLUE);
        for (HexagonCase tile : tiles){
            graphicsContext.strokePolygon(tile.getPolygon()[0], tile.getPolygon()[1],  6);
        }

    }

}