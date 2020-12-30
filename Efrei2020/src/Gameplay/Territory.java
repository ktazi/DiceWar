package Gameplay;

import GUI.Game;
import GUI.SelectionTerritoryPanel;
import GUI.TerritoryInfo;
import Geometry.HexagonCase;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class Territory {
    private ArrayList<HexagonCase> tiles;
    private Color color;
    private Color highlight;
    private Player player;
    private int force;
    private SelectionTerritoryPanel info;
    private int idTerritory;
    private ImageView sprite;
    private TreeSet<Integer> idAdjTerritories;

    public Territory(ArrayList<HexagonCase> tiles, GraphicsContext graphicsContext, SelectionTerritoryPanel territoryInfo, int idTerritory, AnchorPane spritePane){
        player = null;
        this.idTerritory = idTerritory;
        force = 1;
        this.tiles = tiles;
        info = territoryInfo;
        this.color = Color.BLACK;
        this.highlight = Color.BLACK;
        sprite = new ImageView(new Image("Assets/three_blue64.png"));
        spritePane.getChildren().add(sprite);
        AnchorPane.setTopAnchor(sprite,tiles.get(0).getCenterY()-(3*HexagonCase.SIZE/4));
        AnchorPane.setLeftAnchor(sprite,tiles.get(0).getCenterX()-(3*HexagonCase.SIZE/4));
        idAdjTerritories = new TreeSet<>();
    }
    public void clickTerritory() throws FileNotFoundException{
        info.updateInfo(this);
    }

    public void enterTerritory(){
        for (HexagonCase tile : tiles){
            tile.setColor(color);
        }
    }
    public void exitTerritory(){
        for(HexagonCase tile : tiles){
            tile.setColor(highlight);
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
        color = Game.colorRgbTiles(player.getColor());
        highlight = Game.colorRgbTileHighlight(player.getColor());
        for (HexagonCase hc : tiles) {
            hc.setColor(highlight);
        }
        setForceImage();
    }

    private void setForceImage(){
        sprite.setImage(new Image("Assets/"+Game.forceToString(force)+"_"+Game.colorToString(player.getColor())+"64.png"));
    }
    public void setForce(int force){
        this.force = force;
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

    public boolean areAdj(Territory territory) {
        return idAdjTerritories.contains(territory.idTerritory);
    }



}
