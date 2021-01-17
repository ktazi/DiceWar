package Serialization;

import GUI.BattleBar.SelectionTerritoryPanel;
import Gameplay.Player;
import Gameplay.Territory;
import Geometry.HexagonCase;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeSet;

public class TerritoryClone implements Serializable {
    private TreeSet<Integer> idAdjTerritories;
    private int idTerritory;
    private ArrayList<HexagonCaseClones> tiles;
    private int playerId;
    private int force;

    public TerritoryClone(TreeSet<Integer> adj, int id, ArrayList<HexagonCaseClones> tile, int playerId, int force){
        idAdjTerritories = adj;
        idTerritory = id;
        tiles = tile;
        this.playerId = playerId;
        this.force = force;
    }

    public Territory convert(SelectionTerritoryPanel territoryInfo, AnchorPane spritePane){
        ArrayList<HexagonCase> cases = new ArrayList<>();
        for (HexagonCaseClones tile : tiles){
            cases.add(tile.convert());
        }
        Territory territory =  new Territory(cases, territoryInfo, idTerritory, spritePane, idAdjTerritories);
        territory.setTempidplayer(playerId);
        territory.setForce(force);
        return territory;
    }

}
