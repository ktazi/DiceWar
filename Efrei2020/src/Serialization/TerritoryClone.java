package Serialization;

import GUI.BattleBar.SelectionTerritoryPanel;
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

    public TerritoryClone(TreeSet<Integer> adj, int id, ArrayList<HexagonCaseClones> tile){
        idAdjTerritories = adj;
        idTerritory = id;
        tiles = tile;
    }

    public Territory convert(SelectionTerritoryPanel territoryInfo, AnchorPane spritePane){
        ArrayList<HexagonCase> cases = new ArrayList<>();
        for (HexagonCaseClones tile : tiles){
            cases.add(tile.convert());
        }
        return new Territory(cases, territoryInfo, idTerritory, spritePane, idAdjTerritories);
    }

}
