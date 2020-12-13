package Geometry;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;

public class Paving2D {
    public static LinkedList<Triangle> DelaunayTriangulation(ArrayList<Point> points){
        if (points.size() < 3)
            return null;
        LinkedList<Triangle> trianglesList = new LinkedList<>();//final list of triangles
        TreeSet<Triangle> allTriangles = new TreeSet<>();//set containing all triangles
        for (int i = 0; i < points.size(); i++){
            for (int j = 0; j < points.size(); j++){
                for (int k = 0; k < points.size(); k++){
                    if (i != j && j != k && k != i){
                        allTriangles.add(new Triangle(points.get(i),points.get(j),points.get(k)));

                    }
                }
            }
        }
        for (Triangle triangle : allTriangles){
            boolean enters = true;
            for (Point p : points){
                if (!triangle.isInTriangle(p)){
                    if (Distance.euclidianDistance(p, triangle.getCenter())<=triangle.getRadius()){
                        enters = false;
                        break;
                    }
                }
            }
            if (enters){
                trianglesList.add(triangle);
            }
        }
        return trianglesList;
    }
}
