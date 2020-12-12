package Geometry;

public class Distance {
    //calcule la distance euclidienne (classique)
    public static double euclidianDistance(Point a, Point b){
        return Math.sqrt(Math.pow(a.X()-b.X(),2) +Math.pow(a.Y()-b.Y(),2));
    }


}
