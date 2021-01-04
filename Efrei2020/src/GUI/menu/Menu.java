package GUI.menu;


import GUI.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;


public class Menu extends AnchorPane {

    public ImageView fondMap;
    int x,y = -1;


    public void mouvFond() {
        //Stoppper gauche
        if(fondMap.getX() == 0){
            x  =  -1;
        }
        //Stoppper droite
        if(fondMap.getX() == -1534){
            x  =  1;
        }
        //Stoppper haut
        if(fondMap.getY() == 0){
            y  =  -1;
        }
        //Stoppper bas
        if(fondMap.getY() == -1650){
            y  =  1;
        }

        fondMap.setX(fondMap.getX()+x);
        fondMap.setY(fondMap.getY()+y);

        //Dépassement
        if(fondMap.getX() <= -1541){
            fondMap.setX(-1541);
        }
        if(fondMap.getY() <= -1662){
            fondMap.setY(-1662);
        }
    }

    public void chargerPartie(){

    }

    public void nouvellePartie() {
        CreationPartie nouvellepartie = new CreationPartie();

        Stage newStage  = new Stage();
        newStage.setTitle("Dice War");

        newStage.setScene(new Scene(nouvellepartie, 1200, 700));
        newStage.setResizable(false);
        newStage.show();

        Stage actuelStage = (Stage) this.getScene().getWindow();
        actuelStage.close();
    }


    public Menu()throws InterruptedException{
        super();

        //Fond
        fondMap = new ImageView(new Image("Assets/DiceWarsMap.png"));



        //Button lancement du jeu
        Button nouvellepartieBut = new Button("Nouvelle partie");
        Button chargerpartieBut = new Button("Charger une partie");

        nouvellepartieBut.setOnMouseClicked(event -> nouvellePartie());
        chargerpartieBut.setOnMouseClicked(event -> chargerPartie());

        //mouvement du fond
        Timeline fiveSecondsWonder = new Timeline(
                new KeyFrame(Duration.seconds(0.04),
                        new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {

                                mouvFond();

                            }
                        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();




        Text text = new Text("Bienvenue dans Dice Wars");
        text.setFill(Color.GREEN);
        text.setFont(Font.font(23));


        //Ajout des éléments dans l'Anchor
        getChildren().add(fondMap);
        getChildren().add(nouvellepartieBut);
        getChildren().add(chargerpartieBut);
        getChildren().add(text);

        nouvellepartieBut.setMinWidth(150.);
        chargerpartieBut.setMinWidth(150.);

        //Positionnement
        AnchorPane.setLeftAnchor(nouvellepartieBut,530.);
        AnchorPane.setTopAnchor(nouvellepartieBut,270.);
        AnchorPane.setLeftAnchor(chargerpartieBut,530.);
        AnchorPane.setTopAnchor(chargerpartieBut,380.);
        AnchorPane.setLeftAnchor(text,470.);
        AnchorPane.setTopAnchor(text,100.);
        getStylesheets().add(this.getClass().getResource("../Style.css").toExternalForm());


    }

}
