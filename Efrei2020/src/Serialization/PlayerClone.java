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
    private int idPlayer;

    public PlayerClone(Game.COLOR color, ArrayList<Territory> territories, int idPlayer){

        colorId = color.getValue();
        this.idPlayer = idPlayer;
    }

    public Player convert (SelectionTerritoryPanel territoryInfo, AnchorPane spritePane){
        return new Player(Game.COLOR.getColor(colorId), new ArrayList<>(), idPlayer);
    }

}
