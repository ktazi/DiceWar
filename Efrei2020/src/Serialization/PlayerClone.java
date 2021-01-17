package Serialization;

import GUI.BattleBar.SelectionTerritoryPanel;
import GUI.Utils.Game;
import Gameplay.Player;
import Gameplay.Territory;
import Geometry.HexagonCase;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayerClone implements Serializable {
    private int colorId;
    private ArrayList<TerritoryClone> territoryClones;
    private int idPlayer;

    public PlayerClone(Game.COLOR color, ArrayList<Territory> territories, int idPlayer){
        territoryClones = new ArrayList<>();
        for (Territory territory : territories) {
            ArrayList<HexagonCaseClones> tileClones = new ArrayList<>();
            for (HexagonCase hexagonCase : territory.getTiles())
                tileClones.add(new HexagonCaseClones(hexagonCase.getCenterX(),hexagonCase.getCenterY(), hexagonCase.getOffsetx(),hexagonCase.getOffsety()));
            territoryClones.add(new TerritoryClone(territory.getIdAdjTerritories(), territory.getIdTerritory(), tileClones, idPlayer, territory.getForce()));
        }
        colorId = color.getValue();
        this.idPlayer = idPlayer;
    }

    public Player convert (SelectionTerritoryPanel territoryInfo, AnchorPane spritePane){
        ArrayList<Territory> territories = new ArrayList<>();
        for (TerritoryClone territory : territoryClones){
            territories.add(territory.convert(territoryInfo, spritePane));
        }
        return new Player(Game.COLOR.getColor(colorId), territories, idPlayer);
    }

}
