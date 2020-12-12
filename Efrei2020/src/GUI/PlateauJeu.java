package GUI;

import Geometry.Paving2D;
import Geometry.Point;
import Geometry.Triangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javax.swing.*;
import java.util.*;

public class PlateauJeu{

    private ArrayList<Point> points;
    HashMap<Point, ArrayList<Integer>> map;

    private JPanel panel;
    private GraphicsContext graphicsContext;

    final int intervale = 100;


    public PlateauJeu(int dimx, int dimy, GraphicsContext gc){
        points = new ArrayList<>();
        map = new HashMap<>();
        for (int i = 0; i < dimx/intervale; i++){
            for (int j = 0; j < dimy/intervale; j++){
                points.add(new Point((i*intervale) + (Math.random()*intervale), (j*intervale) + (Math.random()*intervale)));
            }
        }
        graphicsContext = gc;
    }

    public void drawthings(){
        LinkedList<Triangle> list = Paving2D.DelaunayTriangulation(points);
        for (Triangle t : list){
            graphicsContext.setFill(Color.rgb((int)(255* Math.random()),(int)(255* Math.random()),(int)(255* Math.random())));
            double[][] polygon = t.toPolygon();
            graphicsContext.fillPolygon(polygon[0], polygon[1], 3);
        }
    }

}