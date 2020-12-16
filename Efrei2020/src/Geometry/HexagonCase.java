package Geometry;

public class HexagonCase {
    public static final int SIZE = 10;
    private double[][] polygon;
    private double centerX;
    private double centerY;
    private int r;
    private int g;
    private int b;

    public HexagonCase(double x1, double y1){
        polygon = new double[2][6];
        centerX=x1;
        centerY=y1;
        r=0;
        g=0;
        b=0;
        polygon[0][0]=x1 + ((SIZE*Math.sqrt(3))/2);
        polygon[1][0]=y1 + (SIZE/(double)2);
        polygon[0][1]=x1;
        polygon[1][1]=y1 + SIZE;
        polygon[0][2]=x1 - ((SIZE*Math.sqrt(3))/2);
        polygon[1][2]=y1 + (SIZE/(double)2);
        polygon[0][3]=x1 - ((SIZE*Math.sqrt(3))/2);
        polygon[1][3]=y1 - (SIZE/(double)2);
        polygon[0][4]=x1;
        polygon[1][4]=y1 - SIZE;
        polygon[0][5]=x1 + ((SIZE*Math.sqrt(3))/2);
        polygon[1][5]=y1 - (SIZE/(double)2);
    }

    public double[][] getPolygon() {
        return polygon;
    }

}
