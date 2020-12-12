package Geometry;

public class Point{
    private double x;
    private double y;
    public Point (double x, double y){
        this.x = x;
        this.y = y;
    }
    public double X(){
        return x;
    }
    public double Y(){
        return y;
    }

    public boolean equals(Point i){
        return (i.x == x) && i.y == y;
    }


}
