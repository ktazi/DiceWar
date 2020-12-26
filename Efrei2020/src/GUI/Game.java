package GUI;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Game {

    public enum COLOR{
        BLUE,
        MAGENTA,
        CYAN,
        YELLOW,
        RED,
        GREEN
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
                return Color.rgb(6,6,36);
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



    public static String colorToString(COLOR color){
        switch (color) {
            case BLUE:
                return "Blue";
            case MAGENTA:
                return "Magenta";
            case RED:
                return "Red";
            case YELLOW:
                return "Yellow";
            case GREEN:
                return "Green";
            default:
                return "Cyan";
        }
    }




}
