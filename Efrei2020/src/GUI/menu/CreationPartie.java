package GUI.menu;

import GUI.GamePanel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CreationPartie extends AnchorPane {

    private Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    private double width =  screenBounds.getWidth();
    private double height =  screenBounds.getHeight();
    private TextField choixJ;
    private int nbPlayer;


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


        newStage.setScene(new Scene(mainPane, width-10, height));
        //newStage.setResizable(false);
        newStage.show();

        Stage actuelStage = (Stage) this.getScene().getWindow();
        actuelStage.close();
    }

    public void Retour() throws InterruptedException {
        Menu menu = new Menu();

        Stage newStage  = new Stage();
        newStage.setTitle("Dice War");

        newStage.setScene(new Scene(menu, width-10, height));
        newStage.setResizable(false);
        newStage.show();

        Stage actuelStage = (Stage) this.getScene().getWindow();
        actuelStage.close();
    }

    public CreationPartie(){
        super();

        //Fond
        FondThread fond = new FondThread();
        fond.start();

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

        Text text = new Text("Saisir le nombre de joueurs");
        text.setFill(Color.GREEN);
        text.setFont(Font.font(23));


        //Ajout des éléments dans l'Anchor
        getChildren().add(fond.getFondMap());
        getChildren().add(lancerjeuBut);
        getChildren().add(text);
        getChildren().add(choixJ);
        getChildren().add(retourBut);

        lancerjeuBut.setMinWidth(130.);
        choixJ.setMinWidth(80.);

        //Positionnement
        AnchorPane.setLeftAnchor(lancerjeuBut,(width*0.5)-65);
        AnchorPane.setTopAnchor(lancerjeuBut,(height*0.6));
        AnchorPane.setLeftAnchor(text,(width*0.5)-(text.getLayoutBounds().getWidth()/2));
        AnchorPane.setTopAnchor(text,(height*0.4)-(text.getLayoutBounds().getHeight()/2));
        AnchorPane.setLeftAnchor(choixJ,(width*0.5)-40);
        AnchorPane.setTopAnchor(choixJ,(height*0.5));
        AnchorPane.setLeftAnchor(retourBut,10.);
        AnchorPane.setTopAnchor(retourBut,10.);


        getStylesheets().add(this.getClass().getResource("../Style.css").toExternalForm());


    }
}
