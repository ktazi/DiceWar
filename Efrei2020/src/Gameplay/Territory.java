package Gameplay;

import GUI.Utils.Game;
import GUI.BattleBar.SelectionTerritoryPanel;
import Geometry.HexagonCase;
import Serialization.HexagonCaseClones;
import Serialization.TerritoryClone;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeSet;

public class Territory implements Serializable {
    private ArrayList<HexagonCase> tiles;
    private Player player;
    private int force;
    private SelectionTerritoryPanel info;
    private int idTerritory;
    private Text sprite;
    private TreeSet<Integer> idAdjTerritories;
    private int tempidplayer;

    public Territory(ArrayList<HexagonCase> tiles, SelectionTerritoryPanel territoryInfo, int idTerritory, AnchorPane spritePane){
        player = null;
        this.idTerritory = idTerritory;
        force = 1;
        this.tiles = tiles;
        info = territoryInfo;
        sprite = new Text("");
        sprite.setFill(Color.WHITE);
        spritePane.getChildren().add(sprite);
        AnchorPane.setTopAnchor(sprite,tiles.get(0).getCenterY()-(3*HexagonCase.SIZE/4));
        AnchorPane.setLeftAnchor(sprite,tiles.get(0).getCenterX()-(3*HexagonCase.SIZE/4));
        idAdjTerritories = new TreeSet<>();
    }
    public Territory(ArrayList<HexagonCase> tiles, SelectionTerritoryPanel territoryInfo, int idTerritory, AnchorPane spritePane, TreeSet<Integer>idAdjTerritories){
        player = null;
        this.idTerritory = idTerritory;
        force = 1;
        this.tiles = tiles;
        info = territoryInfo;
        sprite = new Text("");
        sprite.setFill(Color.WHITE);
        spritePane.getChildren().add(sprite);
        AnchorPane.setTopAnchor(sprite,tiles.get(0).getCenterY()-(3*HexagonCase.SIZE/4));
        AnchorPane.setLeftAnchor(sprite,tiles.get(0).getCenterX()-(3*HexagonCase.SIZE/4));
        this.idAdjTerritories = idAdjTerritories;
    }

    public TreeSet<Integer> getIdAdjTerritories() {
        return idAdjTerritories;
    }

    public int getIdTerritory() {
        return idTerritory;
    }

    public ArrayList<HexagonCase> getTiles() {
        return tiles;
    }

    public void clickTerritory() throws FileNotFoundException{
        info.updateInfo(this);
    }

    public void enterTerritory(){
        for (HexagonCase tile : tiles){
            tile.setColor(Game.colorRgbTiles(player.getColor()));
        }
    }
    public void exitTerritory(){
        for(HexagonCase tile : tiles){
            tile.setColor(Game.colorRgbTileHighlight(player.getColor()));
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
        exitTerritory();
        setForceImage();
    }

    private void setForceImage(){
        sprite.setText(Integer.toString(force));
    }
    public void setForce(int force){
        this.force = force;
        if (player != null)
            setForceImage();
    }
    public int getForce(){
        return force;
    }
    public Player getPlayer(){
        return player;
    }
    public void computeNeighbors(ArrayList<ArrayList<HexagonCase>> allTiles){
        for (HexagonCase tile : tiles){
            int offx = tile.getOffsetx();
            int offy = tile.getOffsety();
            if (offx != 0){
                if (allTiles.get(offx-1).get(offy).getTerritoryId() != idTerritory){
                    idAdjTerritories.add(allTiles.get(offx-1).get(offy).getTerritoryId());
                }
            }
            if (offx != PlateauJeu.NB_HEXAGONS - 1){
                if (allTiles.get(offx+1).get(offy).getTerritoryId() != idTerritory){
                    idAdjTerritories.add(allTiles.get(offx+1).get(offy).getTerritoryId());
                }
                if (offy !=0){
                    if (allTiles.get(offx+1).get(offy-1).getTerritoryId() != idTerritory){
                        idAdjTerritories.add(allTiles.get(offx+1).get(offy-1).getTerritoryId());
                    }
                }
                if (offy != PlateauJeu.NB_HEXAGONS - 1){
                    if (allTiles.get(offx+1).get(offy+1).getTerritoryId() != idTerritory){
                        idAdjTerritories.add(allTiles.get(offx+1).get(offy+1).getTerritoryId());
                    }
                }
            }
            if (offy !=0){
                if (allTiles.get(offx).get(offy-1).getTerritoryId() != idTerritory){
                    idAdjTerritories.add(allTiles.get(offx).get(offy-1).getTerritoryId());
                }
            }
            if (offy != PlateauJeu.NB_HEXAGONS - 1){
                if (allTiles.get(offx).get(offy+1).getTerritoryId() != idTerritory){
                    idAdjTerritories.add(allTiles.get(offx).get(offy+1).getTerritoryId());
                }
            }
        }
    }

    public TerritoryClone prepareSerialization(){
        ArrayList<HexagonCaseClones> caseClones = new ArrayList<>();
        for (HexagonCase hexagonCase : tiles) {
            caseClones.add(hexagonCase.prepareSerialisation());
        }
        return new TerritoryClone(getIdAdjTerritories(), idTerritory, caseClones, player.getIdPlayer(), force);
    }

    public void setTempidplayer(int tempidplayer){
        this.tempidplayer = tempidplayer;
    }

    public int getTempidplayer(){
        return tempidplayer;
    }

    public boolean areAdj(Territory territory) {
        return idAdjTerritories.contains(territory.idTerritory);
    }


    @Override
    public String toString(){
        String ok = "Force = "+force;
        return  ok +" Color = "+Game.colorToString(player.getColor());

    }
}
