package GUI.menu;

import GUI.GamePanel;
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

public class CreationPartie extends AnchorPane {

    public ImageView fondMap;
    public TextField choixJ;
    public int nbPlayer;
    int x,y = -1;


    public void mouvFond() {
        //Stoppper gauche
        if(fondMap.getX() == 0){
            x  =  -1;
        }
        //Stoppper droite
        if(fondMap.getX() == -1541){
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
        if(fondMap.getX() <= -1541){
            fondMap.setX(-1541);
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

    public void lancerJeu() {

        nbPlayer = Integer.parseInt( (choixJ.getCharacters()).toString());
        GamePanel mainPane = new GamePanel(nbPlayer);

        Stage newStage = new Stage();
        newStage.setTitle("Dice War");


        newStage.setScene(new Scene(mainPane, 1200, 700));
        newStage.setResizable(false);
        newStage.show();

        Stage actuelStage = (Stage) this.getScene().getWindow();
        actuelStage.close();
    }

    public void Retour() throws InterruptedException {
        Menu menu = new Menu();

        Stage newStage  = new Stage();
        newStage.setTitle("Dice War");

        newStage.setScene(new Scene(menu, 1200, 700));
        newStage.setResizable(false);
        newStage.show();

        Stage actuelStage = (Stage) this.getScene().getWindow();
        actuelStage.close();
    }

    public CreationPartie(){
        super();

        //Fond
        fondMap = new ImageView(new Image("Assets/DiceWarsMap.png"));

        //selection du nombre de joueurs
        choixJ = new TextField();
        choixJ.setFont(Font.font(23));
        choixJ.setMaxWidth(50.);

        //Button lancement du jeu
        Button lancerjeuBut = new Button("Lancez le jeu");
        lancerjeuBut.setOnMouseClicked(event -> lancerJeu());

        lancerjeuBut.setDisable(true);

        choixJ.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.equals("") || !estUnEntierValide(newValue)){
                    lancerjeuBut.setDisable(true);
                }
                else{
                    lancerjeuBut.setDisable(false);
                }
            }
        });

        //Button : retour menu
        Button retourBut = new Button("Retour");
        retourBut.setOnMouseClicked(event -> {
            try {
                Retour();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

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





        Text text = new Text("Saisir le nombre de joueur");
        text.setFill(Color.GREEN);
        text.setFont(Font.font(23));


        //Ajout des éléments dans l'Anchor
        getChildren().add(fondMap);
        getChildren().add(lancerjeuBut);
        getChildren().add(text);
        getChildren().add(choixJ);
        getChildren().add(retourBut);

        //Positionnement
        AnchorPane.setLeftAnchor(lancerjeuBut,545.);
        AnchorPane.setTopAnchor(lancerjeuBut,390.);
        AnchorPane.setLeftAnchor(text,460.);
        AnchorPane.setTopAnchor(text,270.);
        AnchorPane.setLeftAnchor(choixJ,575.);
        AnchorPane.setTopAnchor(choixJ,320.);
        AnchorPane.setLeftAnchor(retourBut,10.);
        AnchorPane.setTopAnchor(retourBut,10.);


        getStylesheets().add(this.getClass().getResource("../Style.css").toExternalForm());


    }
}
