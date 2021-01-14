package GUI.menu;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.util.Duration;


public class FondThread extends Thread{

    private final Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    private final double width =  screenBounds.getWidth();
    private final double height =  screenBounds.getHeight();

    private int x,y = -1;
    private ImageView fondMap;

    public FondThread(){
        super();
        fondMap = new ImageView(new Image("Assets/DiceWarsMap.png"));
    }

    public void mouv(){

        if(fondMap.getX() == 0){
            x  =  -1;
        }
        //Stoppper droite
        if(fondMap.getX() == -(2734-width)){
            x  =  1;
        }
        //Stoppper haut
        if(fondMap.getY() == 0){
            y  =  -1;
        }
        //Stoppper bas
        if(fondMap.getY() == -(2350-height)){
            y  =  1;
        }

        fondMap.setX(fondMap.getX()+x);
        fondMap.setY(fondMap.getY()+y);

        //Dépassement
        if(fondMap.getX() <= -(2734-width)){
            fondMap.setX(-(2734-width));
        }
        if(fondMap.getY() <= -(2350-height)){
            fondMap.setY(-(2350-height));
        }
    }

    public void run(){

        Timeline fiveSecondsWonder = new Timeline(
                new KeyFrame(Duration.seconds(0.04),
                        new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {

                                mouv();

                            }
                        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();



    }

    public ImageView getFondMap() {
        return fondMap;
    }
}
