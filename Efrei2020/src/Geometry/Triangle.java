package Geometry;

import java.util.LinkedList;

public class Triangle implements Comparable{

    private Point p1;
    private Point p2;
    private Point p3;
    private Point center;
    private double radius;

    public Triangle(Point p1, Point p2, Point p3){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.center = centerCircumbscribedCircle();
        this.radius = radiusCircle();
    }
    //calcule le centre du cercle circonscrit
    private Point centerCircumbscribedCircle(){

        double a1 = 2*(p2.X() - p1.X());
        double a2 = 2*(p2.X() - p3.X());
        double b1 = 2*(p2.Y() - p1.Y());
        double b2 = 2*(p2.Y() - p3.Y());
        double c1 = Math.pow(p1.X(),2) + Math.pow(p1.Y(),2) - Math.pow(p2.X(),2) - Math.pow(p2.Y(),2);
        double c2 = Math.pow(p3.X(),2) + Math.pow(p3.Y(),2) - Math.pow(p2.X(),2) - Math.pow(p2.Y(),2);
        /*System.out.println("p1" + p1);
        System.out.println("p2" +p2);
        System.out.println("p3" +p3);
        System.out.println(((a1 * b2) - (b1*a2)));
        System.out.println(((a1*b2) -(b1*a2)));*/
        return new Point(((b2 * (-c1)) - (b1*(-c2)))/((a1 * b2) - (b1*a2)), ((a1 * (-c2))-(a2 * (-c1)))/((a1*b2) -(b1*a2)));
    }
    //calcule le rayon du cercle
    private double radiusCircle(){
        return Distance.euclidianDistance(p1, center);
    }
    public double getRadius() {
        return radius;
    }
    public Point getCenter() {
        return center;
    }
    public boolean isInTriangle(Point p){
        return p.equals(p1) || p.equals(p2) || p.equals(p3);
    }
    @Override
    public String toString(){
        return "p1 : (" + p1.X() + ", "+p1.Y()+")\n"+"p2 : (" + p2.X() + ", "+p2.Y()+")\n"+"p3 : (" + p3.X() + ", "+p3.Y()+")\n";
    }
    @Override
    public int compareTo(Object o) {
        if (o instanceof Triangle){
            return ((Triangle) o).center == center && ((Triangle) o).radius == radius ? 0 : -1;
        }
        throw new ClassCastException("Not a triangle");
    }

    public double[][] toPolygon(){
        double[] x = new double[3];
        x[0] = p1.X();
        x[1] = p2.X();
        x[2] = p3.X();
        double[] y = new double[3];
        y[0] = p1.Y();
        y[1] = p2.Y();
        y[2] = p3.Y();
        return new double[][]{x,y};
    }


}