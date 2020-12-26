package GUI;

import Geometry.HexagonCase;
import Geometry.Territory;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.*;

public class PlateauJeu{
    private ArrayList<ArrayList<HexagonCase>> tiles;
    private ArrayList<Territory> territories;

    public PlateauJeu(int nbTerritory, GraphicsContext graphicsContext){
        double x0 = 64;
        double y0 = 64;
        tiles = new ArrayList<>();
        for (int i = 0; i < 25; i++){
            tiles.add(new ArrayList<>());
            for(int j = 0; j <25 ; j++){
                tiles.get(i).add(new HexagonCase(x0+(i*Math.sqrt(3)* HexagonCase.SIZE)+(j%2==0?0:HexagonCase.SIZE*Math.sqrt(3)/2) ,
                        y0 + ((j*6* HexagonCase.SIZE)/(double)4),i,j));
            }
        }
        ArrayList<ArrayList<HexagonCase>> racines = new ArrayList<>();
        int x,y;
        for (int i = 0; i < nbTerritory; i++){
            racines.add(new ArrayList<>());
            boolean cond = true;
            do{
                x = (int) (Math.random() * 25);
                y = (int) (Math.random() * 25);
                cond = tiles.get(x).get(y).getTerritoryId() != -1;
                if (!cond){
                    racines.get(i).add(tiles.get(x).get(y));
                    tiles.get(x).get(y).setTerritoryId(i);
                }
            }while (cond);
        }
        int distanceMin, territory;
        for (int i = 0; i < tiles.size(); i++){
            for (int j = 0; j < tiles.get(i).size(); j++){
                distanceMin = -1;
                territory = -1;
                if (tiles.get(i).get(j).getTerritoryId()==-1){
                    for(int k = 0; k < racines.size(); k++){
                        if(distanceMin == -1){
                            distanceMin = racines.get(k).get(0).distance(tiles.get(i).get(j));
                            territory = k;
                        }
                        else if (racines.get(k).get(0).distance(tiles.get(i).get(j)) < distanceMin){
                            distanceMin = racines.get(k).get(0).distance(tiles.get(i).get(j));
                            territory = k;
                        }
                    }
                    tiles.get(i).get(j).setTerritoryId(territory);
                    racines.get(territory).add(tiles.get(i).get(j));
                }
            }
        }
        territories = new ArrayList<>();
        for (int i = 0; i < racines.size(); i++){
            territories.add(new Territory(racines.get(i), Color.rgb((i*13)%255,(i*214)%255,(i*233)%255,0.4 ), graphicsContext));
            for (HexagonCase tile : racines.get(i)){
                tile.setTerritory(territories.get(i));
            }
        }
    }
    public ArrayList<ArrayList<HexagonCase>> getTiles() {
        return tiles;
    }

    public ArrayList<Territory> getTerritories() {
        return territories;
    }
}