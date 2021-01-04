package GUI.Utils;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Game {

    public enum COLOR{
        BLUE(0),
        MAGENTA(1),
        CYAN(2),
        YELLOW(3),
        RED(4),
        GREEN(5);
        private final int value;
        private COLOR(int value) {
            this.value = value;
        }
        public static COLOR getColor(int value){
            if (value == 0)
                return BLUE;
            if (value == 1)
                return MAGENTA;
            if (value == 2)
                return CYAN;
            if (value == 3)
                return YELLOW;
            if (value == 4)
                return RED;
            return GREEN;
        }
        public int getValue(){
            return value;
        }
    }

    public static Image getBanner(Game.COLOR color) throws FileNotFoundException {
        FileInputStream inputstream;
        switch (color) {
            case BLUE:
                inputstream = new FileInputStream("Efrei2020/src/Assets/BannerBlue.png");
                return new Image(inputstream);
            case MAGENTA:
                inputstream = new FileInputStream("Efrei2020/src/Assets/BannerMagenta.png");
                return new Image(inputstream);
            case RED:
                inputstream = new FileInputStream("Efrei2020/src/Assets/BannerRed.png");
                return new Image(inputstream);
            case YELLOW:
                inputstream = new FileInputStream("Efrei2020/src/Assets/BannerYellow.png");
                return new Image(inputstream);
            case GREEN:
                inputstream = new FileInputStream("Efrei2020/src/Assets/BannerGreen.png");
                return new Image(inputstream);
            default:
                inputstream = new FileInputStream("Efrei2020/src/Assets/BannerCyan.png");
                return new Image(inputstream);
        }
    }

    public static Color turnBackground(COLOR color){
        switch (color) {
            case BLUE:
                return Color.rgb(30,30,66);
            case MAGENTA:
                Color.rgb(36,6,36);
            case RED:
                return Color.rgb(36,6,6);
            case YELLOW:
                return Color.rgb(36,36,6);
            case GREEN:
                return Color.rgb(6,36,6);
            default:
                return Color.rgb(6,36,36);
        }
    }



    public static Color colorRgb(COLOR color){
        switch (color) {
            case BLUE:
                return Color.rgb(20,20,255);
            case MAGENTA:
                return Color.rgb(255,20,255);
            case RED:
                return Color.rgb(255,20,20);
            case YELLOW:
                return Color.rgb(255,255,20);
            case GREEN:
                return Color.rgb(20,255,20);
            default:
                return Color.rgb(20,255,255);
        }
    }

    public static Color colorRgbTiles(COLOR color){
        switch (color) {
            case BLUE:
                return Color.rgb(20,20,255, 0.4);
            case MAGENTA:
                return Color.rgb(255,20,255, 0.4);
            case RED:
                return Color.rgb(255,20,20, 0.4);
            case YELLOW:
                return Color.rgb(255,255,20, 0.4);
            case GREEN:
                return Color.rgb(20,255,20, 0.4);
            default:
                return Color.rgb(20,255,255, 0.4);
        }
    }

    public static Color colorRgbTileHighlight(COLOR color){
        switch (color) {
            case BLUE:
                return Color.rgb(6,6,100, 0.4);
            case MAGENTA:
                return Color.rgb(100,6,100, 0.4);
            case RED:
                return Color.rgb(100,6,6, 0.4);
            case YELLOW:
                return Color.rgb(100,100,6, 0.4);
            case GREEN:
                return Color.rgb(6,100,6, 0.4);
            default:
                return Color.rgb(6,100,100, 0.4);
        }
    }

    public static String colorToString(COLOR color){
        switch (color) {
            case BLUE:
                return "blue";
            case MAGENTA:
                return "magenta";
            case RED:
                return "red";
            case YELLOW:
                return "yellow";
            case GREEN:
                return "green";
            default:
                return "cyan";
        }
    }

    public static String forceToString(int force){
        if (force == 1)
            return "one";
        if (force == 2)
            return "two";
        if (force == 3)
            return "three";
        if (force == 4)
            return "four";
        if (force == 5)
            return "five";
        if (force == 6)
            return "six";
        if (force == 7)
            return "seven";
        return "eight";
    }


}
