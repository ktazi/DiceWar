package Geometry;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class HexagonCase {
    public static final int SIZE = 10;
    private double[][] polygon;
    private double centerX;
    private double centerY;
    private int r;
    private int g;
    private int b;
    private Polygon shape;
    private static int ID;
    private int selfId;
    private int cubeCoorX;
    private int cubeCoorY;
    private int cubeCoorZ;
    private int territory;
    private Color color;

    public HexagonCase(double x1, double y1, int offsetCoorX, int offsetCoorY){
        territory = -1;
        selfId = ID;
        ID++;
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
        shape = new Polygon();
        shape.setFill(color);
        shape.setStroke(Color.BLACK);
        for (int i = 0; i < polygon[0].length; i++){
            shape.getPoints().add(polygon[0][i]);
            shape.getPoints().add(polygon[1][i]);
        }
        cubeCoorX = offsetCoorX - (offsetCoorY - (offsetCoorY&1)) / 2;
        cubeCoorZ = offsetCoorY;
        cubeCoorY = -cubeCoorX-cubeCoorZ;
        shape.setOnMouseClicked(mouseEvent -> System.out.println(territory));
        shape.setOnMouseEntered(mouseEvent->shape.setFill(Color.RED));
        shape.setOnMouseExited(mouseEvent->shape.setFill(color));

    }
    public void setColor(Color color){
        this.color = color;
        shape.setFill(color);
    }
    public Polygon getShape(){
        return shape;
    }
    public int getTerritory() {
        return territory;
    }
    public void setTerritory(int territory){
        this.territory = territory;
    }
    public int distance(HexagonCase tile){
        return (Math.abs(tile.cubeCoorX - cubeCoorX) +  Math.abs(tile.cubeCoorY - cubeCoorY) +  Math.abs(tile.cubeCoorZ - cubeCoorZ))/2;
    }



}
