package GUI;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlateauJeu{

    private ArrayList<Point> points;
    HashMap<Point, ArrayList<Integer>> map;

    private JPanel panel;
    private GraphicsContext graphicsContext;



    public PlateauJeu(int nbPoints, int dimx, int dimy, GraphicsContext gc){
        points = new ArrayList<>();
        map = new HashMap<>();
        while (points.size() < nbPoints){
            points.add(new Point((int)(Math.random()*dimx),(int)(Math.random()*dimy)));
        }
        graphicsContext = gc;

        for(int i = 0; i < dimx; i++){
            for (int j = 0; j < dimy; j++){
                double mindistance = dimx*dimy;
                ArrayList<Integer> point = new ArrayList<>();
                for (int k = 0; k < points.size(); k++){
                    if(points.get(k).dist(i,j)<mindistance){
                        mindistance = points.get(k).dist(i,j);
                        point = new ArrayList<>();
                        point.add(k);
                    }
                    else if (points.get(k).dist(i,j)==mindistance){
                        point.add(k);
                    }
                }
                map.put(new Point(i,j), point);
            }
        }
    }

    public void drawthings(){
        for (Map.Entry<Point, ArrayList<Integer>> entry : map.entrySet()){
            graphicsContext.setLineWidth(3);
            if (entry.getValue().size() == 1)
                graphicsContext.setFill(Color.rgb((entry.getValue().get(0)*10)%255,(entry.getValue().get(0)*49)%255,(entry.getValue().get(0)*17)%255));
            else
                graphicsContext.setFill(Color.BLACK);
            graphicsContext.fillRect(entry.getKey().getX(), entry.getKey().getY(), 1, 1);
        }
        for (Point p : points){
            graphicsContext.setFill(Color.RED);
            graphicsContext.fillRect(p.getX(), p.getY(), 1, 1);
        }
    }



    private class Point implements Comparable{
        private int x;
        private int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
        private double dist(int x2, int y2){
            return Math.abs(x - x2) +  Math.abs(y - y2);
        }

        @Override
        public int compareTo(Object o) {
            if (o instanceof Point){
                if (x != ((Point) o).x)
                    return Integer.compare(x,((Point) o).x);
                return Integer.compare(y,((Point) o).y);

            }
            throw new IllegalArgumentException("Uniquement comparable a un etudiant");
        }
    }

}