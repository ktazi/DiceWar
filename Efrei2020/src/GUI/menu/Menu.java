package GUI.menu;


import GUI.GamePanel;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.File;



public class Menu extends AnchorPane {

    private Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    private double width =  screenBounds.getWidth();
    private double height =  screenBounds.getHeight();



    public void chargerPartie(){

        GamePanel mainPane = new GamePanel();

        Stage newStage = new Stage();
        newStage.setTitle("Dice War");

        newStage.setScene(new Scene(mainPane, width-10, height));
        newStage.setResizable(false);
        newStage.show();

        closeGame();
    }

    public void nouvellePartie() {
        CreationPartie nouvellepartie = new CreationPartie();

        Stage newStage  = new Stage();
        newStage.setTitle("Dice War");

        newStage.setScene(new Scene(nouvellepartie,  screenBounds.getWidth()-10, screenBounds.getHeight()));
        newStage.setResizable(false);
        newStage.show();

        closeGame();
    }

    private void closeGame(){
        Stage actuelStage = (Stage) this.getScene().getWindow();
        actuelStage.close();
    }


    public Menu()throws InterruptedException{
        super();

        //Fond
        FondThread fond = new FondThread();
        fond.start();

        Button close = new Button("Close game");
        close.setOnMouseClicked(event -> closeGame());

        //Button lancement du jeu
        Button nouvellepartieBut = new Button("Nouvelle partie");
        Button chargerpartieBut = new Button("Charger une partie");

        nouvellepartieBut.setOnMouseClicked(event -> nouvellePartie());
        chargerpartieBut.setOnMouseClicked(event -> chargerPartie());
        //si il n'y a pas de sauvegardes
        File sauv = new File("./carte.sav");
        if (!sauv.exists())
            chargerpartieBut.setDisable(true);

        Text text = new Text("Bienvenue dans Dice Wars");
        text.setFill(Color.GREEN);
        text.setFont(Font.font(23));


        //Ajout des éléments dans l'Anchor
        getChildren().add(fond.getFondMap());
        getChildren().add(nouvellepartieBut);
        getChildren().add(chargerpartieBut);
        getChildren().add(close);
        getChildren().add(text);

        nouvellepartieBut.setMinWidth(150.);
        chargerpartieBut.setMinWidth(150.);

        //Positionnement
        AnchorPane.setLeftAnchor(nouvellepartieBut, (width*0.5)-75);
        AnchorPane.setTopAnchor(nouvellepartieBut,(height*0.5));
        AnchorPane.setLeftAnchor(chargerpartieBut,(width*0.5)-75);
        AnchorPane.setTopAnchor(chargerpartieBut,(height*0.6));
        AnchorPane.setLeftAnchor(text,(width*0.5)-(text.getLayoutBounds().getWidth()/2));
        AnchorPane.setTopAnchor(text,(height*0.35)-(text.getLayoutBounds().getHeight()/2));
        AnchorPane.setLeftAnchor(close,10.);
        AnchorPane.setTopAnchor(close,10.);
        getStylesheets().add(this.getClass().getResource("../Style.css").toExternalForm());


    }

}
