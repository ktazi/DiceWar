package Gameplay;

import GUI.BattleBar.BattleBar;
import GUI.Utils.Game;
import GUI.logs.MessagePanel;
import GUI.logs.TurnPanel;
import GUI.logs.WinPanel;
import Geometry.HexagonCase;
import Serialization.PlateauClone;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.util.*;

public class PlateauJeu  implements Serializable {
    private ArrayList<ArrayList<HexagonCase>> tiles;
    private ArrayList<Territory> territories;
    private ArrayList<Player> players;
    private Player currentPlayer;
    public final static int NB_HEXAGONS= 25;
    private BattleBar battleBar;

    //constructor for new game
    public PlateauJeu(int nbTerritory, GraphicsContext graphicsContext, BattleBar battleBar, int nbPlayer, AnchorPane spritePane){
        double x0 = 64;
        double y0 = 64;
        //creation of the hexagon tiles
        tiles = new ArrayList<>();
        for (int i = 0; i < NB_HEXAGONS; i++){
            tiles.add(new ArrayList<>());
            for(int j = 0; j <NB_HEXAGONS ; j++){
                tiles.get(i).add(new HexagonCase(x0+(i*Math.sqrt(3)* HexagonCase.SIZE)+(j%2==0?0:HexagonCase.SIZE*Math.sqrt(3)/2) ,
                        y0 + ((j*6* HexagonCase.SIZE)/(double)4),i,j));
            }
        }
        //creation of territories : choosing the root
        ArrayList<ArrayList<HexagonCase>> racines = new ArrayList<>();
        int x,y;
        for (int i = 0; i < nbTerritory; i++){
            racines.add(new ArrayList<>());
            boolean cond;
            do{
                x = (int) (Math.random() * 25);
                y = (int) (Math.random() * 25);
                cond = tiles.get(x).get(y).getTerritoryId() != -1;
                if (!cond){
                    racines.get(i).add(tiles.get(x).get(y));
                    tiles.get(x).get(y).setTerritoryId(i);
                }
            }while (cond);
        }
        //creation of territories : voronoi
        int distanceMin, territory;
        for (int i = 0; i < tiles.size(); i++){
            for (int j = 0; j < tiles.get(i).size(); j++){
                distanceMin = -1;
                territory = -1;
                if (tiles.get(i).get(j).getTerritoryId()==-1){
                    for(int k = 0; k < racines.size(); k++){
                        if(distanceMin == -1){
                            distanceMin = racines.get(k).get(0).distance(tiles.get(i).get(j));
                            territory = k;
                        }
                        else if (racines.get(k).get(0).distance(tiles.get(i).get(j)) < distanceMin){
                            distanceMin = racines.get(k).get(0).distance(tiles.get(i).get(j));
                            territory = k;
                        }
                    }
                    tiles.get(i).get(j).setTerritoryId(territory);
                    racines.get(territory).add(tiles.get(i).get(j));
                }
            }
        }
        //storing all territories in an array
        territories = new ArrayList<>();
        for (int i = 0; i < racines.size(); i++){
            territories.add(new Territory(racines.get(i), battleBar.getSelectionTerritoryPanel(),i, spritePane));
            for (HexagonCase tile : racines.get(i)){
                tile.setTerritory(territories.get(i));
            }
        }
        //computing neighbor territories
        for (Territory territoire : territories){
            territoire.computeNeighbors(tiles);
        }
        //creation of players
        players = Player.createPlayers(nbPlayer, territories);
        //choosing the first that plays
        choosePlayer();
        battleBar.getLogPanel().addPanel(new MessagePanel("Welcome to DiceWars !\nThis game was developed \nin 2020 for\nan academic project."));
        try {
            battleBar.getLogPanel().addPanel(new TurnPanel(getCurrentPlayer().getColor()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        battleBar.getSelectionTerritoryPanel().setPlateauJeu(this);
    }
    //constructeur pour serialisation
    public PlateauJeu(ArrayList<ArrayList<HexagonCase>> tiles,
                      ArrayList<Territory> territories,
                      ArrayList<Player> players,
                      Player currentPlayer,
                      BattleBar battleBar){
        this.tiles = tiles;
        this.players = players;
        this.currentPlayer = currentPlayer;
        this.territories = territories;
        for (Territory territory : this.territories){
            ArrayList<HexagonCase> tab = territory.getTiles();
            for (HexagonCase t : tab){
                this.tiles.get(t.getOffsetx()).set(t.getOffsety(), t);
                t.setTerritory(territory);
                t.setTerritoryId(territory.getIdTerritory());
            }
        }
        battleBar.getLogPanel().addPanel(new MessagePanel("Welcome to DiceWars !\nThis game was developed \nin 2020 for\nan academic project."));
        try {
            battleBar.getLogPanel().addPanel(new TurnPanel(getCurrentPlayer().getColor()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        battleBar.getSelectionTerritoryPanel().setPlateauJeu(this);
    }
    public ArrayList<ArrayList<HexagonCase>> getTiles() {
        return tiles;
    }
    private void choosePlayer(){
        currentPlayer = players.get((int)(Math.random()*players.size()));
    }
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    public Game.COLOR changeTurn(){
        //add force to player
        getCurrentPlayer().addForce(currentPlayer.nbTerritoireContiguMax());
        //remove a loser if there is one
        players.removeIf(player -> player.getTerritories().size() == 0);
        //Victory
        if (players.size() == 1){
            return null;
        }
        //choose new player
        choosePlayer();
        return getCurrentPlayer().getColor();
    }

    public PlateauClone createClone(){
        return new PlateauClone(tiles, territories, players, currentPlayer);
    }


}