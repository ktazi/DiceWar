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
    public TextField choixJ;
    public int nbPlayer;
    int x,y = -1;


    public void mouvFond() throws InterruptedException {
        //Stoppper gauche
        if(fondMap.getX() == 0){
            x  =  -1;
        }
        //Stoppper droite
        if(fondMap.getX() == -1542){
            x  =  1;
        }
        //Stoppper haut
        if(fondMap.getY() == 0){
            y  =  -1;
        }
        //Stoppper bas
        if(fondMap.getY() == -1662){
            y  =  1;
        }

        fondMap.setX(fondMap.getX()+x);
        fondMap.setY(fondMap.getY()+y);

        //Dépassement
        if(fondMap.getX() <= -1542){
            fondMap.setX(-1542);
        }
        if(fondMap.getY() <= -1662){
            fondMap.setY(-1662);
        }
    }

    public boolean estUnEntierValide(String chaine) {
        try {
            Integer.parseInt(chaine);
        } catch (NumberFormatException e){
            return false;
        }
        return Integer.parseInt(chaine)<=6 && Integer.parseInt(chaine)>=2;
    }

    public void lancerJeu() throws Exception {
        System.out.println("le jeu se lance");
        nbPlayer = Integer.parseInt( (choixJ.getCharacters()).toString());
        System.out.println(nbPlayer);
        /*
        Main main = new Main();
        Stage primaryStage = new Stage();
        main.start(primaryStage,5);

         */
    }

    public Menu()throws InterruptedException{
        super();

        //Fond
        fondMap = new ImageView(new Image("Assets/DiceWarsMap.png"));

        //selection du nombre de joueurs
        choixJ = new TextField();
        choixJ.setFont(Font.font(23));
        choixJ.setMaxWidth(50.);

        //Button lancement du jeu
        Button b1 = new Button("Lancez le jeu");
        b1.setOnMouseClicked(event -> {
            try {
                lancerJeu();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        b1.setDisable(true);

        choixJ.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.equals("") || !estUnEntierValide(newValue)){
                    b1.setDisable(true);
                }
                else{
                    b1.setDisable(false);
                }
            }
        });

        Timeline fiveSecondsWonder = new Timeline(
                new KeyFrame(Duration.seconds(0.04),
                        new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {

                                try {
                                    mouvFond();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }
                        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();



        Text text = new Text("Saisir le nombre de joueur");
        text.setFill(Color.GREEN);
        text.setFont(Font.font(23));


        //Ajout des éléments dans l'Anchor
        getChildren().add(fondMap);
        getChildren().add(b1);
        getChildren().add(text);
        getChildren().add(choixJ);

        //Positionnement
        AnchorPane.setLeftAnchor(b1,545.);
        AnchorPane.setTopAnchor(b1,390.);
        AnchorPane.setLeftAnchor(text,460.);
        AnchorPane.setTopAnchor(text,270.);
        AnchorPane.setLeftAnchor(choixJ,575.);
        AnchorPane.setTopAnchor(choixJ,320.);

        getStylesheets().add(this.getClass().getResource("../Style.css").toExternalForm());


    }

}
