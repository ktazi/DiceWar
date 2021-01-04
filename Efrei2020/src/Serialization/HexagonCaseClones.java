package Serialization;

import Geometry.HexagonCase;

import java.io.Serializable;

public class HexagonCaseClones implements Serializable{
    private double centerX;
    private double centerY;
    private int offsetx;
    private int offsety;
    public HexagonCaseClones(double centerX, double centerY, int offsetx, int offsety){
        this.centerX = centerX;
        this.centerY = centerY;
        this.offsetx = offsetx;
        this.offsety = offsety;
    }
    public HexagonCase convert(){
        return new HexagonCase(centerX, centerY, offsetx, offsety);
    }

}
