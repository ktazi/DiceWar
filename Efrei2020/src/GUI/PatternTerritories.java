package GUI;

import javafx.scene.image.Image;

public class PatternTerritories {
    public static enum Color{
        BLUE,
        MAGENTA,
        GREEN,
        RED,
        YELLOW,
        CYAN
    }
    private static final Image groundBlue  = new Image("Assets/Ground_blue.png");
    private static final  Image groundMagenta = new Image("Assets/Ground_magenta.png");
    private static final Image groundRed = new Image("Assets/Ground_red.png");
    private static final Image groundCyan = new Image("Assets/Ground_cyan.png");
    private static final Image groundYellow = new Image("Assets/Ground_yellow.png");
    private static final Image groundGreen  = new Image("Assets/Ground_green.png");

    public static Image getImage(PatternTerritories.Color color){
        switch (color){
            case RED:
                return groundRed;
            case BLUE:
                return groundBlue;
            case CYAN:
                return groundCyan;
            case MAGENTA:
                return groundMagenta;
            case YELLOW:
                return groundYellow;
            default:
                return groundGreen;
        }
    }
}
