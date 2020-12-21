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

    public PlateauJeu(int dimx, int dimy, int nbTerritory){

        double x0 = 10;
        double y0 = 10;
        tiles = new ArrayList<>();
        for (int i = 0; i < 25; i++){
            tiles.add(new ArrayList<>());
            for(int j = 0; j <25 ; j++){
                tiles.get(i).add(new HexagonCase(x0+(i*Math.sqrt(3)* HexagonCase.SIZE)+(j%2==0?0:HexagonCase.SIZE*Math.sqrt(3)/2) , y0 + ((j*6* HexagonCase.SIZE)/(double)4),i,j));
            }
        }
        ArrayList<ArrayList<HexagonCase>> racines = new ArrayList<>();
        int x,y;
        for (int i = 0; i < nbTerritory; i++){
            racines.add(new ArrayList<>());
            boolean cond = true;
            do{
                x = (int) (Math.random() * 20);
                y = (int) (Math.random() * 20);
                cond = tiles.get(x).get(y).getTerritory() != -1;
                if (!cond){
                    racines.get(i).add(tiles.get(x).get(y));
                    tiles.get(x).get(y).setTerritory(i);
                    System.out.println("Territory " + i + " : "+x+" , "+y);
                }
            }while (cond);
        }
        int distanceMin, territory;
        for (int i = 0; i < tiles.size(); i++){
            for (int j = 0; j < tiles.get(i).size(); j++){
                distanceMin = -1;
                territory = -1;
                if (tiles.get(i).get(j).getTerritory()==-1){
                    for(int k = 0; k < racines.size(); k++){
                        if(distanceMin == -1){
                            System.out.println("yes");
                            distanceMin = racines.get(k).get(0).distance(tiles.get(i).get(j));
                            territory = k;
                        }
                        else if (racines.get(k).get(0).distance(tiles.get(i).get(j)) < distanceMin){
                            distanceMin = racines.get(k).get(0).distance(tiles.get(i).get(j));
                            territory = k;
                        }
                    }
                    tiles.get(i).get(j).setTerritory(territory);
                    racines.get(territory).add(tiles.get(i).get(j));
                }
            }
        }
        territories = new ArrayList<>();
        for (int i = 0; i < racines.size(); i++){
            territories.add(new Territory(racines.get(i), Color.rgb((i*13)%255,(i*214)%255,(i*233)%255 )));
        }
    }

    public ArrayList<ArrayList<HexagonCase>> getTiles() {
        return tiles;
    }
}