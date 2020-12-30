package GUI.BattleBar;

import Gameplay.PlateauJeu;
import Gameplay.Territory;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import java.io.FileNotFoundException;

public class SelectionTerritoryPanel extends HBox {

    public TerritoryInfo infoTerritory1;
    public TerritoryInfo infoTerritory2;
    public Button assault;
    public Button pass;
    public PlateauJeu plateauJeu;
    private Territory territory1Selected;
    private Territory territory2Selected;
    private BattleBar parent;
    private boolean active;

    public SelectionTerritoryPanel(BattleBar battleBar){
        super();
        active = true;
        parent = battleBar;
        territory1Selected = null;
        territory2Selected = null;
        ImageView imageView = new ImageView(new Image("Assets/Territory.png"));
        getChildren().add(imageView);
        infoTerritory1 = new TerritoryInfo();
        infoTerritory2 = new TerritoryInfo();
        getChildren().add(infoTerritory1);
        ImageView imageView2 = new ImageView(new Image("Assets/Versus.png"));
        assault = new Button("Attack !");
        pass = new Button("Pass");
        assault.getStylesheets().add(this.getClass().getResource("../Style.css").toExternalForm());
        assault.setDisable(true);
        pass.setId("pass");
        pass.getStylesheets().add(this.getClass().getResource("../Style.css").toExternalForm());
        assault.setOnMouseClicked(event -> lancerAssault());
        AnchorPane panneau = new AnchorPane();
        panneau.getChildren().add(imageView2);
        panneau.getChildren().add(assault);
        AnchorPane.setTopAnchor(assault, 35.);
        AnchorPane.setLeftAnchor(assault, 60.);
        panneau.getChildren().add(pass);
        AnchorPane.setTopAnchor(pass, 135.);
        AnchorPane.setLeftAnchor(pass, 70.);
        getChildren().add(panneau);
        getChildren().add(infoTerritory2);
        pass.setOnMouseClicked(event -> endOfTurn());
        plateauJeu = null;

    }
    private void lancerAssault(){
        infoTerritory1.setBlank();
        infoTerritory2.setBlank();
        assault.setDisable(true);
        parent.switchMode();
    }

    Territory getTerritory1Selected(){
        return territory1Selected;
    }
    Territory getTerritory2Selected(){
        return territory2Selected;
    }


    public void setPlateauJeu(PlateauJeu plateauJeu) {
        this.plateauJeu = plateauJeu;
    }

    public void updateInfo(Territory territory) throws FileNotFoundException {
        if (active){
            if (territory.getPlayer().getIdPlayer()==plateauJeu.getCurrentPlayer().getIdPlayer()){
                infoTerritory1.updateInfo(territory.getForce(),territory.getPlayer().getColor());
                territory1Selected = territory;
                infoTerritory2.setBlank();
            }
            else{
                if (!infoTerritory1.isBlanck() && territory1Selected.areAdj(territory)){
                    infoTerritory2.updateInfo(territory.getForce(),territory.getPlayer().getColor());
                    territory2Selected = territory;
                }
            }
            if (!infoTerritory1.isBlanck() && !infoTerritory2.isBlanck() && territory1Selected.areAdj(territory2Selected)&&territory1Selected.getForce()!=1){
                assault.setDisable(false);
            }
            else{
                assault.setDisable(true);
            }
        }
    }

    public void endOfTurn(){
        infoTerritory1.setBlank();
        infoTerritory2.setBlank();
        territory1Selected = null;
        territory2Selected = null;
        plateauJeu.changeTurn();
    }


    public void switchActive(){
        active = !active;
    }


}
